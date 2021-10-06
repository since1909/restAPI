package com.restAPI.domain.model;

import com.restAPI.dto.UserInfoDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder(builderMethodName = "UserInfoBuilder")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public static UserInfoBuilder builder(UserInfoDTO userInfoDTO){
        return UserInfoBuilder().id(userInfoDTO.getId())
                .name(userInfoDTO.getName())
                .age(userInfoDTO.getAge());
    }
}
