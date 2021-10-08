package com.restAPI.domain;

import com.restAPI.domain.model.UserInfo;
import com.restAPI.domain.repository.UserInfoRepository;
import com.restAPI.dto.UserInfoDTO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class) //Junit4
public class UserInfoDomainServiceTest {

    @Mock
    private UserInfoRepository userInfoTestRepository;
    //https://lemontia.tistory.com/915

    @Test
    @DisplayName("get info test")
    public void getUserInfo() {
        //given
        UserInfo userInfo = UserInfo.builder(UserInfoDTO.builder(1, "park", 24 ).build()).build();
        String name = "park";
        given(userInfoTestRepository.findByName(name)).willReturn(Optional.ofNullable(userInfo));

        //when
        Optional<UserInfo> testUserInfo = userInfoTestRepository.findByName(name);
        System.out.println(testUserInfo.get().getId());

        //then
        Assertions.assertEquals(1, testUserInfo.get().getId());
        Assertions.assertEquals(name, testUserInfo.get().getName());

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