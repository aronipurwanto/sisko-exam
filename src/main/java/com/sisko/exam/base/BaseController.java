package com.sisko.exam.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class BaseController<T> {
    public ResponseEntity<Response> getResponse(List<T> result) {
        return ResponseEntity.ok(
                Response.builder()
                        .status(HttpStatus.OK.value())
                        .message(HttpStatus.OK.name())
                        .data(result)
                        .build()
        );
    }

    public ResponseEntity<Response> getResponse(Optional<T> result) {
        if (result.isPresent()) {
            return ResponseEntity.ok(
                    Response.builder()
                            .status(HttpStatus.OK.value())
                            .message(HttpStatus.OK.name())
                            .data(result)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message(HttpStatus.BAD_REQUEST.name())
                            .data(null)
                            .build()
            );
        }
    }

    public ResponseEntity<Response> getResponse(T result) {
        if (result != null) {
            return ResponseEntity.ok(
                    Response.builder()
                            .status(HttpStatus.OK.value())
                            .message(HttpStatus.OK.name())
                            .data(result)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .status(HttpStatus.BAD_REQUEST.value())
                            .message(HttpStatus.BAD_REQUEST.name())
                            .data(null)
                            .build()
            );
        }
    }
}

