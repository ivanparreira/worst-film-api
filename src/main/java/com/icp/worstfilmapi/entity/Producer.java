package com.icp.worstfilmapi.entity;

import lombok.*;
import org.hibernate.annotations.SortComparator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "producers")
    private List<Film> filmsList;

}
