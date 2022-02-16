package com.sparta.found.domain.service;

import com.sparta.found.domain.repository.UserRepository;
import com.sparta.found.error.ErrorCode;
import com.sparta.found.error.exception.CustomFieldException;
import com.sparta.found.security.util.SecurityUtil;
import com.sparta.found.web.dto.user.SignupRequestDto;
import com.sparta.found.web.dto.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignupRequestDto dto){

        idDuplicateCheck(dto.getUsername());

        String password = dto.getPassword();
        String passwordCheck = dto.getPasswordCheck();

        if(!password.equals(passwordCheck)){
            throw new CustomFieldException("password"
                    ,"비밀번호와 비밀번호 확인이 일치하지 않습니다"
            ,ErrorCode.INVALID_INPUT_ERROR);
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(dto.createUser());
    }


    public void idDuplicateCheck(String username){

        if(userRepository.existsByUsername(username)){
            throw new CustomFieldException("username","이미 존재하는 이메일입니다",ErrorCode.DUPLICATED_INPUT_ERROR);
        }
    }

    public UserInfo getUserInfo(){

        String username = SecurityUtil.getCurrentLoginUserId();


        return userRepository.findByUsername(username).get().toUserInfo();
    }
}
