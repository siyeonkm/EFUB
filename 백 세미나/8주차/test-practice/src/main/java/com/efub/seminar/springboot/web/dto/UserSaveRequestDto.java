package com.efub.seminar.springboot.web.dto;

import com.efub.seminar.springboot.domain.User;
import com.efub.seminar.springboot.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Builder
    public UserSaveRequestDto(String name, UserType type){
        this.name = name;
        this.type = type;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .type(type)
                .build();
    }
}
