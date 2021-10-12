package com.restAPI.domain;

import com.restAPI.domain.model.UserInfo;
import com.restAPI.domain.repository.UserInfoRepository;
import com.restAPI.dto.UserInfoDTO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class) //Junit4
public class UserInfoDomainServiceTest {

    @Mock
    private UserInfoRepository userInfoTestRepository;

    @Test
    @DisplayName("get info test")
    public void getUserInfo() {
        //given
        UserInfo userInfo = UserInfo.builder(UserInfoDTO.builder(1, "park", 24 ).build()).build();
        String name = "park";

        //!!!@Mock으로 주입했기 때문에 .willReturn 달아줘야함!!!
        given(userInfoTestRepository.findByName(name)).willReturn(Optional.ofNullable(userInfo));

        //when
        Optional<UserInfo> testUserInfo = userInfoTestRepository.findByName(name);

        //then
        Assertions.assertEquals(1, testUserInfo.get().getId());
        Assertions.assertEquals(name, testUserInfo.get().getName());

    }


    @Test
    public void createUserInfo() {
        //given
        UserInfo userInfo = UserInfo.builder(UserInfoDTO.builder(1, "park", 24 ).build()).build();
        given(userInfoTestRepository.save(userInfo)).willReturn(userInfo);

        //when
        UserInfo testInfo = userInfoTestRepository.save(userInfo);

        //then
        Assertions.assertEquals(1, testInfo.getId());

    }


    @Test
    public void updateUserInfo() {
        //given
        UserInfo updateInfo = UserInfo.builder(UserInfoDTO.builder(1, "lee", 42 ).build()).build();
        UserInfo findInfo = UserInfo.builder(UserInfoDTO.builder(1, "park", 24 ).build()).build();
        given(userInfoTestRepository.findById((long)1)).willReturn(Optional.of(findInfo));

        //when
        Optional<UserInfo> userInfo = userInfoTestRepository.findById((long)1);
        userInfo.get().setId(updateInfo.getId());
        userInfo.get().setName(updateInfo.getName());
        userInfo.get().setAge(updateInfo.getAge());

        //then
        Assertions.assertEquals(1, userInfo.get().getId());
        Assertions.assertEquals("lee", userInfo.get().getName());
        Assertions.assertEquals(42, userInfo.get().getAge());
    }


    @Test
    public void deleteUserInfo() {
        //void return 이라 데스트를 어떻게 할지 모르겠,,,
    }
}