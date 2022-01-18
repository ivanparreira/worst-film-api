package com.icp.worstfilmapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class Film implements Comparable<Film> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean winner;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Producer> producers;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Studio> studios;

    public int compareTo(Film film) {
        if(this.year < film.year) return -1;
        else return 1;
    }
}
