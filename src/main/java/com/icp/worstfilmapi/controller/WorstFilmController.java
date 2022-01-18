package com.icp.worstfilmapi.controller;

import com.icp.worstfilmapi.dto.response.MessageResponseDTO;
import com.icp.worstfilmapi.service.WorstFilmService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/worstfilm")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WorstFilmController {

    private final WorstFilmService worstFilmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO upload(@RequestParam @Valid MultipartFile file) throws IOException {
        return worstFilmService.uploadFile(file);
    }

    @GetMapping
    public Map<String, Object> prizeRange(){
        return worstFilmService.prizeRange();

    }
}
