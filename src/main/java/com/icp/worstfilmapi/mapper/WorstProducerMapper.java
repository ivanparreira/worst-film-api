package com.icp.worstfilmapi.mapper;

import com.icp.worstfilmapi.dto.request.FilmDTO;
import com.icp.worstfilmapi.dto.request.ProducerDTO;
import com.icp.worstfilmapi.dto.request.StudioDTO;
import com.icp.worstfilmapi.entity.Film;
import com.icp.worstfilmapi.entity.Producer;
import com.icp.worstfilmapi.entity.Studio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WorstProducerMapper {

    WorstProducerMapper INSTANCE = Mappers.getMapper(WorstProducerMapper.class);

    Producer toModel(ProducerDTO producerDTO);
    ProducerDTO toDTO(Producer producer);

}
