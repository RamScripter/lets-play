package com.mariekd.letsplay.app.request;

import jakarta.validation.constraints.NotBlank;

public record AppRequest(@NotBlank String title, @NotBlank String musicianType, @NotBlank String image,
                         @NotBlank String style, @NotBlank String location, @NotBlank String description) {
}
