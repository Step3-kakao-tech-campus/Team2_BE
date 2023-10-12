package com.example.team2_be.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Error {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4096, nullable = false)
    private String message;

    @Column(nullable = false)
    private int status;

    @Column(length = 1024, nullable = false)
    private String stackTrace;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Builder
    public Error(String message, int status, String stackTrace, LocalDateTime createAt) {
        this.message = message;
        this.status = status;
        this.stackTrace = stackTrace;
        this.createAt = createAt;
    }
}
