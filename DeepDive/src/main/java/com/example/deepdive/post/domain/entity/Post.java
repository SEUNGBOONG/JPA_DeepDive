package com.example.deepdive.post.domain.entity;

import com.example.deepdive.member.exception.exceptions.NotContainSpecialChars;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Post {

    private final String specialChars = "!@#$%^&*()";

    @Id
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column
    private String title;

    @Column
    private String comments;

    @Column
    private String password;

    public Post(final Long memberId, final String title, final String comments, final String password) {
        this.memberId = memberId;
        this.title = title;
        this.comments = validateComment(comments);
        this.password = validatePassword(password);
    }

    public Post() {

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
