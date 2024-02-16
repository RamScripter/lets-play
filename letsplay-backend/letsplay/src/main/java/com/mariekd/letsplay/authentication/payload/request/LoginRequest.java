package com.mariekd.letsplay.authentication.payload.request;

//import jakarta.validation.constraints.NotBlank; //TODO : update mvn repository...


public record LoginRequest (String username, String password) {
}
