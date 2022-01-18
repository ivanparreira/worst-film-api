package com.icp.worstfilmapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
public class StudioDTO {

    private Long id;

    @NotEmpty
    @Size(min = 13, max = 14)
    private String name;

}
