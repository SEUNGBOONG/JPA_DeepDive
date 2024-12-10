package com.example.deepdive.post.domain.entity;

import com.example.deepdive.member.exception.NotSamePasswordChangeException;
import com.example.deepdive.member.exception.exceptions.NotContainSpecialChars;
import com.example.deepdive.post.controller.dto.PostRequestDTO;
import com.example.deepdive.post.controller.dto.PostUpdatePasswordResponse;
import com.example.deepdive.post.util.Timestamped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
public class Post extends Timestamped {

    private final String specialChars = "[!@#$%^&*()_+=|<>?{}\\[\\]~-]";

    @Id
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private String password;

    public Post(final Long memberId, final String title, final String comments, final String password) {
        this.memberId = memberId;
        this.title = title;
        this.comments = validateComment(comments);
        this.password = validatePassword(password);
    }

    public void updateTitleOrComments(final PostRequestDTO postRequestDTO) {
        this.title = postRequestDTO.getTitle();
        this.comments = postRequestDTO.getContent();
    }

    public void updatePassword (String postRequestDTO){
        this.password= String.valueOf(validateUpdatePassword(postRequestDTO));
    }

    public Post() {

    }

    private PostUpdatePasswordResponse validateUpdatePassword(String newPassword) {
        if (Objects.equals(newPassword, password)) {
            throw new NotSamePasswordChangeException();
        }
        return new PostUpdatePasswordResponse(newPassword);
    }

    private String validateComment(final String comments) {
        if (comments.contains("시")) {
            throw new RuntimeException("욕설이 들어가 있습니다.");
        }
        return comments;
    }

    private String validatePassword(final String password) {
        boolean hasSpecialChar = false;
        for (char c : specialChars.toCharArray()) {
            if (password.contains(String.valueOf(c))) {
                hasSpecialChar = true;
                break;
            }
        }

        if (!hasSpecialChar) {
            throw new NotContainSpecialChars();
        }
        return password;
    }
}
//Post 엔티티는 게시물의 필드와 관련된 유효성 검사 및 비즈니스 로직을 처리합니다.
//
//특수문자 검증: 게시물의 비밀번호에 특수문자가 포함되어 있는지 확인하는 기능을 제공합니다. 포함되지 않으면 NotContainSpecialChars 예외를 던집니다.
//댓글 검증: 댓글에 "시"라는 단어가 포함되어 있으면 예외를 던집니다.
//비밀번호 업데이트 검증: 비밀번호를 변경하려 할 때, 기존 비밀번호와 동일하면 NotSamePasswordChangeException 예외를 던집니다.


