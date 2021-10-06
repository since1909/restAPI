package com.restAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

//UserInfoDto 클래스 생성 : 서비스와 컨트롤러 계층 데이터 전달
//@Data : lombok을 사용하여 getter/setter 자동 생성

@Data
@Builder(builderMethodName = "UserInfoDTOBuilder")
@JsonDeserialize(builder = UserInfoDTO.UserInfoDTOBuilder.class)
public class UserInfoDTO{
    @JsonProperty("id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("age")
    private int age;

    public static UserInfoDTOBuilder builder(long id, String name, int age){
        return UserInfoDTOBuilder().id(id)
                .name(name)
                .age(age);
    }
}
