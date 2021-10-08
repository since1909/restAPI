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
        //given
        UserInfoDTO userInfoDTO = UserInfoDTO.builder(1, "park", 24).build();
        UserInfo userInfo = UserInfo.builder(userInfoDTO).build();
        given(userInfoTestDomainService.createUserInfo(userInfo)).willReturn(userInfo);

        //when
        UserInfo testInfo = userInfoTestDomainService.createUserInfo(userInfo);

        //then
        Assertions.assertEquals(userInfo.getName(), testInfo.getName());
        Assertions.assertEquals(userInfo.getAge(), testInfo.getAge());

    }


//    public UserInfoDTO updateUserInfo(String name, UserInfoDTO userInfoDTO){
//        UserInfo userInfo = UserInfo.builder(userInfoDTO).build();
//
//        log.debug("userInfo created, id: " + userInfoDTO.getId());
//        userInfoDomainService.updateUserInfo(userInfo);
//        return userInfoDTO;
//    }
    @Test
    public void updateUserInfo() {
        //given
        UserInfoDTO userInfoDTO = UserInfoDTO.builder(1, "park", 24).build();
        UserInfo userInfo = UserInfo.builder(userInfoDTO).build();

        //when
        userInfoTestDomainService.updateUserInfo(userInfo);

        //then
        Assertions.assertEquals("park", userInfoDTO.getName());

    }

    @Test
    public void deleteUserInfo() {
        //void..
    }
}