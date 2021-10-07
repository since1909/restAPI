package com.restAPI.controller;

import com.restAPI.domain.model.UserInfo;
import com.restAPI.dto.UserInfoDTO;
import com.restAPI.service.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {
    final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    @PostMapping("/userinfo")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoDTO createUserInfo(@RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.createUserInfo(userInfoDTO);
    }

    @GetMapping("/userinfo/{name}")
    public UserInfoDTO selectUserInfo(@PathVariable String name){
        return userInfoService.selectUserInfo(name);
    }

    @PutMapping("/userinfo/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //put은 보통 200, 204(no content)로 상태 표현
    //no contetn 어노테이션 주니까 return 안 받음
    public UserInfoDTO updateUserInfo(@PathVariable String name, @RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.updateUserInfo(name, userInfoDTO);
    }

    @DeleteMapping("/userinfo/{name}")
    public String deleteUserInfo(@PathVariable String name){
        userInfoService.deleteUserInfo(name);
        return "deleted " + name;
    }
}
