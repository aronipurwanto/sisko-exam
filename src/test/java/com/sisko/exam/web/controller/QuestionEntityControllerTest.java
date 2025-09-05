package com.sisko.exam.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisko.exam.model.entity.QuestionEntity;
import com.sisko.exam.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = QuestionController.class)
class QuestionEntityControllerTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;


    @MockBean
    QuestionService questionService;


    @Test
    void createQuestion_validation_ok() throws Exception {
        var req = new com.sisko.exam.web.dto.QuestionDTOs.CreateQuestionReq(
                QuestionEntity.Type.MCQ, QuestionEntity.AnswerPolicy.MULTI_ALL, "stem", 1.0,
                List.of(new com.sisko.exam.web.dto.QuestionDTOs.OptionReq("A","opt", true))
        );
        Mockito.when(questionService.createQuestion(Mockito.any(), Mockito.anyList()))
                .thenReturn(QuestionEntity.builder().id(1L).qtype(QuestionEntity.Type.MCQ).answerPolicy(QuestionEntity.AnswerPolicy.MULTI_ALL).stem("stem").pointsDefault(1.0).build());
        mvc.perform(post("/api/questions").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(req)))
                .andExpect(status().isOk());
    }
}