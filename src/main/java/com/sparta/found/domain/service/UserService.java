package com.sparta.found.domain.service;

import com.sparta.found.domain.entity.User;
import com.sparta.found.domain.repository.UserRepository;
import com.sparta.found.error.ErrorCode;
import com.sparta.found.error.exception.CustomFieldException;
import com.sparta.found.file.S3Uploader;
import com.sparta.found.security.util.SecurityUtil;
import com.sparta.found.web.dto.user.SignupRequestDto;
import com.sparta.found.web.dto.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Uploader s3Uploader;

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

    @Transactional
    public String updateProfileImage(MultipartFile multipartFile) throws IOException {

        User user = userRepository.findByUsername(SecurityUtil.getCurrentLoginUserId()).get();

        String currentUserProfileImage = user.getProfileImageUrl();

        if(!currentUserProfileImage.equals("https://mini-project.s3.ap-northeast-2.amazonaws.com/static/fc11d9b3-7a4d-469e-8671-a037cb3979acIMG_5663.jpeg")){
            s3Uploader.deleteFile(user.getProfileImageUrl());
        }

        String profileImageUrl = s3Uploader.upload(multipartFile, "static");
        user.changeProfileImageUrl(profileImageUrl);

        userRepository.save(user);

        return profileImageUrl;
    }

}
