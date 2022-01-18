package com.icp.worstfilmapi.service;

import com.icp.worstfilmapi.repository.WorstProducerRepository;
import org.junit.Assert;
import com.icp.worstfilmapi.dto.request.FilmDTO;
import com.icp.worstfilmapi.dto.response.MessageResponseDTO;
import com.icp.worstfilmapi.entity.Film;
import com.icp.worstfilmapi.repository.WorstFilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.icp.worstfilmapi.utils.PersonUtils;
import lombok.Value;
import org.springframework.core.io.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class WorstFilmServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private WorstFilmRepository worstFilmRepository;

    @Mock
    private WorstProducerRepository worstProducerRepository;

    @InjectMocks
    private WorstFilmService worstFilmService;

   /* @MockBean
    @Value("classpath:movielistTest.csv")
    MultipartFile movieList;*/


    @Test
    @ConfigurationProperties(prefix = "test")
    void testGetWinners() throws Exception {

        String response = "{\"min\":[{\"producer\":\"ALLAN CARR\",\"interval\":1,\"previousWin\":2020,\"followingWin\":2021},{\"producer\":\"JOEL SILVER\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991},{\"producer\":\"BO DEREK\",\"interval\":6,\"previousWin\":1984,\"followingWin\":1990},{\"producer\":\"BUZZ FEITSHANS\",\"interval\":9,\"previousWin\":1985,\"followingWin\":1994},{\"producer\":\"MATTHEW VAUGHN\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}],max:[{\"producer\":\"ALLAN CARR\",\"interval\":40,\"previousWin\":1980,\"followingWin\":2020},{\"producer\":\"MATTHEW VAUGHN\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015},{\"producer\":\"BUZZ FEITSHANS\",\"interval\":9,\"previousWin\":1985,\"followingWin\":1994},{\"producer\":\"BO DEREK\",\"interval\":6,\"previousWin\":1984,\"followingWin\":1990},{\"producer\":\"JOEL SILVER\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}]}";

        var result = mockMvc.perform(get("/api/v1/worstfilm")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals("A resposta deve ser igual a resposta esperada!", response,result);
    }

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
    void testGivenPrizeRangeThemReturnSameMessege() throws IOException {

      //  FilmDTO filmDTO = createFakeDTO();
      //  Film expectedSavedFilm = createFakeEntity();

        String response = "{min:[{\"producer\":\"ALLAN CARR\",\"interval\":1,\"previousWin\":2020,\"followingWin\":2021},{\"producer\":\"JOEL SILVER\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991},{\"producer\":\"BO DEREK\",\"interval\":6,\"previousWin\":1984,\"followingWin\":1990},{\"producer\":\"BUZZ FEITSHANS\",\"interval\":9,\"previousWin\":1985,\"followingWin\":1994},{\"producer\":\"MATTHEW VAUGHN\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}],\"max\":[{\"producer\":\"ALLAN CARR\",\"interval\":40,\"previousWin\":1980,\"followingWin\":2020},{\"producer\":\"MATTHEW VAUGHN\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015},{\"producer\":\"BUZZ FEITSHANS\",\"interval\":9,\"previousWin\":1985,\"followingWin\":1994},{\"producer\":\"BO DEREK\",\"interval\":6,\"previousWin\":1984,\"followingWin\":1990},{\"producer\":\"JOEL SILVER\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}]}";

      //  when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

      /*  MessageResponseDTO expectedSuccessMessage = MessageResponseDTO
                .builder()
                .message("Dados importados com sucesso!")
                .build();*/
        String succesMessage = worstFilmService.prizeRange().toString();

        System.out.println(response);
        System.out.println(succesMessage);

        Assertions.assertEquals(response, succesMessage);
    }

}
