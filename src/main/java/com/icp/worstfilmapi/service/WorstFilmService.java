package com.icp.worstfilmapi.service;

import com.icp.worstfilmapi.dto.request.FilmDTO;
import com.icp.worstfilmapi.dto.request.ProducerDTO;
import com.icp.worstfilmapi.dto.request.StudioDTO;
import com.icp.worstfilmapi.dto.response.MessageResponseDTO;
import com.icp.worstfilmapi.entity.Film;
import com.icp.worstfilmapi.entity.Producer;
import com.icp.worstfilmapi.entity.Studio;
import com.icp.worstfilmapi.mapper.WorstFilmMapper;
import com.icp.worstfilmapi.repository.WorstFilmRepository;
import com.icp.worstfilmapi.repository.WorstProducerRepository;
import com.icp.worstfilmapi.repository.WorstStudioRepository;
import com.icp.worstfilmapi.vo.IntervalAward;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WorstFilmService {

    private final WorstFilmRepository worstFilmRepository;
    private final WorstProducerRepository worstProducerRepository;
    private final WorstStudioRepository worstStudioRepository;

    private final WorstFilmMapper worstFilmMapper = WorstFilmMapper.INSTANCE;

    public MessageResponseDTO uploadFile(MultipartFile file) throws IOException {
        if ( file.getSize() == 0 ) return getMessageResponseDTO("Selecione um arquivo!");
        InputStream inputStream = file.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .skip(1)
                .forEach(this::handleLine);
        return getMessageResponseDTO("Dados importados com sucesso!");
    }

    private void handleLine(String line) {
        line = line.replaceAll(" and ", ",");
        String[] film = line.split(";");

        FilmDTO filmDTO = filmDTO(film);

        Film filmToSave = worstFilmMapper.toModel(filmDTO);
        Film saveFilm = worstFilmRepository.save(filmToSave);
    }

    private FilmDTO filmDTO(String[] film){
        Boolean winner = (film.length == 5 && film[4].trim().equals("yes")) ;
        return new FilmDTO(
                null,
                Integer.parseInt(film[0].trim()),
                film[1].trim(),
                winner,
                listProductorsDTO(film[3]),
                listStudiosDTO(film[2]));
    }

    private List<ProducerDTO> listProductorsDTO(String strProductors){
        List<ProducerDTO> listProducer = new ArrayList<>();
        String[] listStrProductor = strProductors.split(",");
        for (String s : listStrProductor) {
            listProducer.add(worstFilmMapper.toDTO(findCreateProducer(s.trim())));
        }
        return listProducer;
    }

    private Producer findCreateProducer(String strProductor){
        Producer findProducer = worstProducerRepository.findByName(strProductor);
        if (findProducer == null)
            findProducer = worstProducerRepository.save(new Producer(null, strProductor,null));
        return findProducer;
    }

    private List<StudioDTO> listStudiosDTO(String studios){
        List<StudioDTO> listStudiosDTO = new ArrayList<>();
        String[] studio = studios.split(",");
        for (String s : studio) {
            listStudiosDTO.add(worstFilmMapper.toDTO(findCreateStudio(s.trim())));
        }
        return listStudiosDTO;
    }

    private Studio findCreateStudio(String strStudio){
        Studio findStudio = worstStudioRepository.findByName(strStudio);
        if (findStudio == null)
            findStudio = worstStudioRepository.save( new Studio(null, strStudio) );
        return findStudio;
    }

    public Map<String, Object > prizeRange() {
        List<IntervalAward> listIntervalAward = new ArrayList<>();
        List<Producer> listProducer = worstProducerRepository.findAll();
        Integer minInterval = 0;
        Integer maxInterval = 0;

        for(Producer producer : listProducer) {
            IntervalAward intervalAward = new IntervalAward();
            Collections.sort(producer.getFilmsList());

            for (Film film : producer.getFilmsList()){

                if (intervalAward.getPreviousWin() == null){
                    intervalAward.setPreviousWin(film.getYear());
                    intervalAward.setProducer(producer.getName());
                    intervalAward.setInterval(0);
                    intervalAward.setPreviousWin(film.getYear());
                    intervalAward.setFollowingWin(film.getYear());
                } else {
                    if (intervalAward.getFollowingWin() < film.getYear()){
                        intervalAward.setFollowingWin(film.getYear());
                        intervalAward.setInterval( intervalAward.getFollowingWin() - intervalAward.getPreviousWin());
                        minInterval = ( ( minInterval > intervalAward.getInterval() || minInterval == 0) ? intervalAward.getInterval(): minInterval);
                        maxInterval = ( maxInterval < intervalAward.getInterval() ? intervalAward.getInterval() : maxInterval );
                    }
                }
            }
            listIntervalAward.add(intervalAward);
        }

        Integer finalMinInterval = minInterval;
        List<IntervalAward> minIntervalAwards = listIntervalAward.stream()
                .filter(p -> p.getInterval().equals(finalMinInterval) ).collect(Collectors.toList());

        Integer finalMaxInterval = maxInterval;
        List<IntervalAward> maxIntervalAwards = listIntervalAward.stream()
                .filter(p -> p.getInterval().equals(finalMaxInterval)).collect(Collectors.toList());

        List<Film> allFilms = worstFilmRepository.findAll();
        HashMap<String, Object> map = new HashMap<>();
        map.put("min", minIntervalAwards);
        map.put("max", maxIntervalAwards);
        return map;
    }

    private MessageResponseDTO getMessageResponseDTO(String messege) {
        return MessageResponseDTO
                .builder()
                .message(messege)
                .build();
    }
}
