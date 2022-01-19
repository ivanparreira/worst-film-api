package com.icp.worstfilmapi.service;

import com.icp.worstfilmapi.repository.WorstProducerRepository;
import com.icp.worstfilmapi.dto.response.MessageResponseDTO;
import com.icp.worstfilmapi.repository.WorstFilmRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;


import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class WorstFilmServiceTest {

    @Mock
    private WorstFilmRepository worstFilmRepository;

    @Mock
    private WorstProducerRepository worstProducerRepository;

    @InjectMocks
    private WorstFilmService worstFilmService;

    @Test
    void testGivenUploadFileThemReturnSameMessege() throws IOException {

        MockMultipartFile firstFile = new MockMultipartFile("data", "movieListTest.csv", "text/plain", "some xml".getBytes());

        MessageResponseDTO expectedSuccessMessage = MessageResponseDTO
                .builder()
                .message("Dados importados com sucesso!")
                .build();
        MessageResponseDTO succesMessage = worstFilmService.uploadFile(firstFile);

        Assertions.assertEquals(expectedSuccessMessage, succesMessage);
    }

    @Test
    void testGivenPrizeRangeThemReturnSameMessege() {

        String response = "{min=[], max=[]}";
        String succesMessage = worstFilmService.prizeRange().toString();

        Assertions.assertEquals(response, succesMessage);
    }

}
