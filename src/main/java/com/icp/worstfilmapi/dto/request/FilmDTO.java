package com.icp.worstfilmapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class FilmDTO {

    private Long id;

    @NotEmpty
    private int year;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String title;

    private Boolean winner;

    @Valid
    @NotEmpty
    private List<ProducerDTO> producers;

    @Valid
    @NotEmpty
    private List<StudioDTO> studios;

}
