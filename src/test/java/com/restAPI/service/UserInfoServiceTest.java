package com.restAPI.service;

import com.restAPI.domain.UserInfoDomainService;
import com.restAPI.domain.UserInfoDomainServiceTest;
import com.restAPI.domain.model.UserInfo;
import com.restAPI.dto.UserInfoDTO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class UserInfoServiceTest {

    @Mock
    private UserInfoDomainService userInfoTestDomainService;


    //public UserInfoDTO selectUserInfo(String name){
    //        UserInfo userInfo = userInfoDomainService.getUserInfo(name);
    //
    //        UserInfoDTO userInfoDTO = UserInfoDTO.builder(userInfo.getId(), userInfo.getName(), userInfo.getAge())
    //                .build();
    //
    //        return userInfoDTO;
    //    }

    @Test
    public void selectUserInfo() {
        //given
        UserInfo userInfo = UserInfo.builder(UserInfoDTO.builder(1, "park", 24).build()).build();
        String name = "park";
        given(userInfoTestDomainService.getUserInfo(name)).willReturn(userInfo);

        //when
        UserInfo testInfo = userInfoTestDomainService.getUserInfo(name);
        UserInfoDTO testDTO = UserInfoDTO.builder(testInfo.getId(), testInfo.getName(), testInfo.getAge()).build();

        //then
        Assertions.assertEquals(1, testDTO.getId());
    }

    @Test
    public void createUserInfo() {
    }

    @Test
    public void updateUserInfo() {
    }

    @Test
    public void deleteUserInfo() {
    }
}