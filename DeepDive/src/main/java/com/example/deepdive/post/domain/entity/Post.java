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
