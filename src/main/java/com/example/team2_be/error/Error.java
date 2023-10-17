package com.example.team2_be.error;

import com.example.team2_be.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Error extends BaseEntity {

    @Column(length = 1024, nullable = false)
    private String message;

    @Column(length = 4096, nullable = false)
    private String stackTrace;

    @Builder
    public Error(String message, String stackTrace) {
        this.message = message;
        this.stackTrace = stackTrace;
    }
}
