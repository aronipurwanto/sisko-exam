package com.sisko.exam.base;

import lombok.*;

@Builder
public record Response<T>(int status, String message, T data) {}
