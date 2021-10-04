package com.restAPI.dto;

import lombok.Data;

//UserInfoDto 클래스 생성 : 서비스와 컨트롤러 계층 데이터 전달
//@Data : lombok을 사용하여 getter/setter 자동 생성

@Data
public class UserInfoDTO{
    private long id;
    private String name;
    private int age;
}
