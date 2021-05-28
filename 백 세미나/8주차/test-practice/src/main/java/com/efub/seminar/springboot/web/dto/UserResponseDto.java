package com.efub.seminar.springboot.web.dto;

import com.efub.seminar.springboot.domain.User;
import com.efub.seminar.springboot.domain.UserType;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class UserResponseDto {
    private String name;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public UserResponseDto(User entity){
        this.name = entity.getName();
        this.type = entity.getType();
    }
}
