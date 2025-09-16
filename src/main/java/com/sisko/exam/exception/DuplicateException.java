package com.sisko.exam.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}