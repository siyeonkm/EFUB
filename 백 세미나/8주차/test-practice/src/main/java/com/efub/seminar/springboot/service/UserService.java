package com.efub.seminar.springboot.service;

import com.efub.seminar.springboot.domain.User;
import com.efub.seminar.springboot.domain.UserRepository;
import com.efub.seminar.springboot.web.dto.UserResponseDto;
import com.efub.seminar.springboot.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto findById(Long id){
//        User entity = userRepository.findById(id).orElseThrow(() -> new
//                IllegalArgumentException("해당 사용자가 없습니다. id = "+id));
        User entity = userRepository.getOne(id);
        return new UserResponseDto(entity);
    }

    public User save(UserSaveRequestDto requestDto){
        return userRepository.save(requestDto.toEntity());
    }
}
