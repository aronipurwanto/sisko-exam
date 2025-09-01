package com.sisko.exam.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDTOs {
    public record LoginReq(@Email String email, @NotBlank String password) {}
    public record LoginResp(String token) {}
}
