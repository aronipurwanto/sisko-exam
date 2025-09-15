package com.sisko.exam.base;

import lombok.*;

import java.time.Instant;

@Builder
public record ResponseError(Instant timestamp, int status, String message, Object error) {

    public ResponseError(int status, String message, Object error) {
        this(Instant.now(), status, message, error);
    }
}
