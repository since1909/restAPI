package com.restAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restAPI.dto.UserInfoDTO;
import com.restAPI.service.UserInfoService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserInfoController.class)
@RunWith(SpringRunner.class) //Junit4
public class UserInfoControllerTest {

    @Autowired
    MockMvc mvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private UserInfoService userInfoTestService;

    @Test
    @DisplayName("CREATE TEST")
    public void createUserInfo() throws Exception {
        //given
        UserInfoDTO userinfoTestDTO = UserInfoDTO.builder(1,"park", 24).build();

        given(userInfoTestService.createUserInfo(userinfoTestDTO)).willReturn(userinfoTestDTO);
        String content = objectMapper.writeValueAsString(userinfoTestDTO);

        //when
        final ResultActions actions = mvc.perform(post("/userinfo")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("park")))
                .andExpect(jsonPath("$.age", is(24)))
                .andDo(print());
    }

    @Test
    @DisplayName("SELECT BY NAME TEST")
    public void selectUserInfo() throws Exception {
        //given
        UserInfoDTO userInfoTestDTO = UserInfoDTO.builder(1, "park", 24).build();

        given(userInfoTestService.selectUserInfo("park")).willReturn(userInfoTestDTO);

        //when
        final ResultActions actions = mvc.perform(get("/userinfo/{name}", userInfoTestDTO.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());
        //then
        actions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("park")))
                .andDo(print());

    }

    @Test
    @DisplayName("UPDATE TEST")
    public void updateUserInfo() throws Exception{

    }


    @Test
    @DisplayName("DELETE BY NAME TEST")
    public void deleteUserInfo() throws Exception{
        //given
        String deleteName = "park";
        userInfoTestService.deleteUserInfo(deleteName);

        //when
        final ResultActions actions = mvc.perform(delete("/userinfo/{name}", deleteName)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print());
        //then
        actions.andExpect(status().isOk())
                .andDo(print());
    }
}