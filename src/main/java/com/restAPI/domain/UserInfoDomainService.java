package com.restAPI.domain;

import com.restAPI.domain.model.UserInfo;
import com.restAPI.domain.repository.UserInfoRepository;

import com.restAPI.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

//@Service : 서비스 클래스임을 알리며,  컴포넌트 스캔을 통해 빈으로 등록하게 함
//@RequiredArgsConstructor :
// Lombok을 통해 생성자를 자동생성 시 final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어준다.
// 여기서는 final로 선언된 userInfoRepository 입력받는 생성자,
// 즉 생성자주입방식으로 의존성을 주입( DI)하기 위해 사용하였다.
//@Slf4j : 로깅 인터페이스를 제공


@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoDomainService {
    private final UserInfoRepository userInfoRepository;
    //@Transactional : 데이터베이스 상태를 변경하는 작업임으로
    // 다른 작업의 영향을 받지 않기 위해 사용 + commit, rollback 처리
    @Transactional
    public UserInfo getUserInfo(String name){
        Optional<UserInfo> userInfo = userInfoRepository.findByName(name);
        if(!userInfo.isPresent())
            throw new RuntimeException("findByName, not found: " + name);
        return userInfo.get();
    }

    @Transactional
    public UserInfo createUserInfo(UserInfo userInfo){
        userInfo = userInfoRepository.save(userInfo);
        return userInfo;
    }

    @Transactional
    public void updateUserInfo(UserInfo userInfo){
        log.debug("userinfo.getID() : " + userInfo.getId());
        UserInfo userInfo1 = userInfoRepository.findById(userInfo.getId()).orElseThrow(() -> new RuntimeException("not found"));

        log.debug("userinfo1.getID() : " + userInfo1.getId());
        log.debug("userinfo1.getName() : " + userInfo1.getName());
        log.debug("userinfo1.getAge() : " + userInfo1.getAge());

        userInfo1.setId(userInfo.getId());
        userInfo1.setName(userInfo.getName());
        userInfo1.setAge(userInfo.getAge());

        userInfoRepository.save(userInfo1);
    }

    @Transactional
    public void deleteUserInfo(String name){
        userInfoRepository.deleteByName(name);
    }
}
