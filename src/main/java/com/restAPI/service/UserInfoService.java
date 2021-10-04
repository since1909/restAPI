package com.restAPI.service;

import com.restAPI.domain.UserInfoDomainService;
import com.restAPI.domain.model.UserInfo;
import com.restAPI.dto.UserInfoDTO;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

//비지니스 로직을 구현.
//컨트롤러 요청에 대한 동작 수행.
//도메인 서비스에 원하는 동작을 요청

@Service
@Slf4j
public class UserInfoService {
    final UserInfoDomainService userInfoDomainService;

    public UserInfoService(UserInfoDomainService userInfoDomainService){
        this.userInfoDomainService = userInfoDomainService;
    }

    public UserInfoDTO selectUserInfo(String name){
        UserInfo userInfo = userInfoDomainService.getUserInfo(name);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userInfo.getId());
        userInfoDTO.setName(userInfo.getName());
        userInfoDTO.setAge(userInfo.getAge());

        return userInfoDTO;
    }

    public UserInfoDTO createUserInfo(UserInfoDTO userInfoDTO){
        UserInfo userInfo = new UserInfo();
        log.debug("userInfoDTO.getID(): " + userInfoDTO.getId());

        userInfo.setId(userInfoDTO.getId());
        userInfo.setName(userInfoDTO.getName());
        userInfo.setAge(userInfoDTO.getAge());

        userInfo = userInfoDomainService.createUserInfo(userInfo);

        userInfoDTO.setId(userInfo.getId());
        userInfoDTO.setName(userInfo.getName());
        userInfoDTO.setAge(userInfo.getAge());
        return userInfoDTO;
    }

    public UserInfoDTO updateUserInfo(String name, UserInfoDTO userInfoDTO){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userInfoDTO.getId());
        userInfo.setName(userInfoDTO.getName());
        userInfo.setAge(userInfoDTO.getAge());

        log.debug("userInfo created, id: " + userInfoDTO.getId());
        userInfoDomainService.updateUserInfo(userInfo);
        return userInfoDTO;
    }

    public void deleteUserInfo(String name){
        userInfoDomainService.deleteUserInfo(name);
    }
}
