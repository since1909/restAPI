package com.restAPI.controller;

import com.restAPI.domain.model.UserInfo;
import com.restAPI.dto.UserInfoDTO;
import com.restAPI.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserInfoController {
    final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService){
        this.userInfoService = userInfoService;
    }

    @PostMapping("/userinfo")
    public UserInfoDTO createUserInfo(@RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.createUserInfo(userInfoDTO);
    }

    @GetMapping("/userinfo/{name}")
    public UserInfoDTO selectUserInfo(@PathVariable String name){
        return userInfoService.selectUserInfo(name);
    }

    @PutMapping("/userinfo/{name}")
    public UserInfoDTO updateUserInfo(@PathVariable String name, @RequestBody UserInfoDTO userInfoDTO){
        return userInfoService.updateUserInfo(name, userInfoDTO);
    }

    @DeleteMapping("/userinfo/{name}")
    public String deleteUserInfo(@PathVariable String name){
        userInfoService.deleteUserInfo(name);
        return "deleted " + name;
    }
}
