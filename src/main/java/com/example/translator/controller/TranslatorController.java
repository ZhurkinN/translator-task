package com.example.translator.controller;

import com.example.translator.dao.impl.TranslationDAOImpl;
import com.example.translator.dto.RequestDTO;
import com.example.translator.dto.ResponseDTO;
import com.example.translator.dto.TranslationResultsDTO;
import com.example.translator.factory.impl.H2DBDAOFactory;
import com.example.translator.service.TranslationService;
import com.example.translator.util.IdRandomizer;
import com.example.translator.util.TextTranslator;
import com.example.translator.util.TranslationRuleHandler;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Timestamp;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TranslatorController {

    @Autowired
    private TranslationService service;
    @PostMapping(value = "/translate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getTranslation(@RequestBody RequestDTO dto, HttpServletRequest request)
            throws SQLException {

        return ResponseEntity.ok(new ResponseDTO(service.translateText(dto, request)));
    }

}
