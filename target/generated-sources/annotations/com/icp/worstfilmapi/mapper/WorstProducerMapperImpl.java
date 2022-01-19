package com.icp.worstfilmapi.mapper;

import com.icp.worstfilmapi.dto.request.FilmDTO;
import com.icp.worstfilmapi.dto.request.FilmDTO.FilmDTOBuilder;
import com.icp.worstfilmapi.dto.request.ProducerDTO;
import com.icp.worstfilmapi.dto.request.ProducerDTO.ProducerDTOBuilder;
import com.icp.worstfilmapi.dto.request.StudioDTO;
import com.icp.worstfilmapi.dto.request.StudioDTO.StudioDTOBuilder;
import com.icp.worstfilmapi.entity.Film;
import com.icp.worstfilmapi.entity.Film.FilmBuilder;
import com.icp.worstfilmapi.entity.Producer;
import com.icp.worstfilmapi.entity.Producer.ProducerBuilder;
import com.icp.worstfilmapi.entity.Studio;
import com.icp.worstfilmapi.entity.Studio.StudioBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-19T13:42:40-0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.13 (Ubuntu)"
)
@Component
public class WorstProducerMapperImpl implements WorstProducerMapper {

    @Override
    public Producer toModel(ProducerDTO producerDTO) {
        if ( producerDTO == null ) {
            return null;
        }

        ProducerBuilder producer = Producer.builder();

        producer.id( producerDTO.getId() );
        producer.name( producerDTO.getName() );
        producer.filmsList( filmDTOListToFilmList( producerDTO.getFilmsList() ) );

        return producer.build();
    }

    @Override
    public ProducerDTO toDTO(Producer producer) {
        if ( producer == null ) {
            return null;
        }

        ProducerDTOBuilder producerDTO = ProducerDTO.builder();

        producerDTO.id( producer.getId() );
        producerDTO.name( producer.getName() );
        producerDTO.filmsList( filmListToFilmDTOList( producer.getFilmsList() ) );

        return producerDTO.build();
    }

    protected List<Producer> producerDTOListToProducerList(List<ProducerDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Producer> list1 = new ArrayList<Producer>( list.size() );
        for ( ProducerDTO producerDTO : list ) {
            list1.add( toModel( producerDTO ) );
        }

        return list1;
    }

    protected Studio studioDTOToStudio(StudioDTO studioDTO) {
        if ( studioDTO == null ) {
            return null;
        }

        StudioBuilder studio = Studio.builder();

        studio.id( studioDTO.getId() );
        studio.name( studioDTO.getName() );

        return studio.build();
    }

    protected List<Studio> studioDTOListToStudioList(List<StudioDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Studio> list1 = new ArrayList<Studio>( list.size() );
        for ( StudioDTO studioDTO : list ) {
            list1.add( studioDTOToStudio( studioDTO ) );
        }

        return list1;
    }

    protected Film filmDTOToFilm(FilmDTO filmDTO) {
        if ( filmDTO == null ) {
            return null;
        }

        FilmBuilder film = Film.builder();

        film.id( filmDTO.getId() );
        film.year( filmDTO.getYear() );
        film.title( filmDTO.getTitle() );
        film.winner( filmDTO.getWinner() );
        film.producers( producerDTOListToProducerList( filmDTO.getProducers() ) );
        film.studios( studioDTOListToStudioList( filmDTO.getStudios() ) );

        return film.build();
    }

    protected List<Film> filmDTOListToFilmList(List<FilmDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Film> list1 = new ArrayList<Film>( list.size() );
        for ( FilmDTO filmDTO : list ) {
            list1.add( filmDTOToFilm( filmDTO ) );
        }

        return list1;
    }

    protected List<ProducerDTO> producerListToProducerDTOList(List<Producer> list) {
        if ( list == null ) {
            return null;
        }

        List<ProducerDTO> list1 = new ArrayList<ProducerDTO>( list.size() );
        for ( Producer producer : list ) {
            list1.add( toDTO( producer ) );
        }

        return list1;
    }

    protected StudioDTO studioToStudioDTO(Studio studio) {
        if ( studio == null ) {
            return null;
        }

        StudioDTOBuilder studioDTO = StudioDTO.builder();

        studioDTO.id( studio.getId() );
        studioDTO.name( studio.getName() );

        return studioDTO.build();
    }

    protected List<StudioDTO> studioListToStudioDTOList(List<Studio> list) {
        if ( list == null ) {
            return null;
        }

        List<StudioDTO> list1 = new ArrayList<StudioDTO>( list.size() );
        for ( Studio studio : list ) {
            list1.add( studioToStudioDTO( studio ) );
        }

        return list1;
    }

    protected FilmDTO filmToFilmDTO(Film film) {
        if ( film == null ) {
            return null;
        }

        FilmDTOBuilder filmDTO = FilmDTO.builder();

        filmDTO.id( film.getId() );
        if ( film.getYear() != null ) {
            filmDTO.year( film.getYear() );
        }
        filmDTO.title( film.getTitle() );
        filmDTO.winner( film.getWinner() );
        filmDTO.producers( producerListToProducerDTOList( film.getProducers() ) );
        filmDTO.studios( studioListToStudioDTOList( film.getStudios() ) );

        return filmDTO.build();
    }

    protected List<FilmDTO> filmListToFilmDTOList(List<Film> list) {
        if ( list == null ) {
            return null;
        }

        List<FilmDTO> list1 = new ArrayList<FilmDTO>( list.size() );
        for ( Film film : list ) {
            list1.add( filmToFilmDTO( film ) );
        }

        return list1;
    }
}
