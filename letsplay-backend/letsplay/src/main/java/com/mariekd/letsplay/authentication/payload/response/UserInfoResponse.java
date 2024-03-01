package com.mariekd.letsplay.authentication.payload.response;

import com.mariekd.letsplay.app.entities.Role;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record UserInfoResponse  (UUID id, String username, List<String> roles) {
}
