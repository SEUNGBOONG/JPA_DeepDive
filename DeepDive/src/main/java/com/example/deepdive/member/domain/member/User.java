package com.example.deepdive.member.domain.member;

import com.example.deepdive.member.exception.exceptions.NotContainSpecialChars;
import com.example.deepdive.member.exception.exceptions.auth.NotSamePasswordException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "User")
public class User {

    private final String specialChars = "!@#$%^&*()";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberPassword;

    @Column(nullable = false)
    private String memberNickName;

    public User(final String memberEmail, final String memberName, final String memberPassword, final String memberNickName) {
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberNickName = memberNickName;
    }

    public void checkPassword(String requestPassword) {
        if (!Objects.equals(memberPassword, requestPassword)) {
            throw new NotSamePasswordException();
        }

        boolean hasSpecialChar = false;
        for (char c : specialChars.toCharArray()) {
            if (requestPassword.contains(String.valueOf(c))) {
                hasSpecialChar = true;
                break;
            }
        }

        if (!hasSpecialChar) {
            throw new NotContainSpecialChars();
        }
    }
}


