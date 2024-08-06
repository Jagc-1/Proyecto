package com.jpa.proyecto.domain.dto;

public class AuthResponse {
    private String token;

    private AuthResponse(String token) {
        this.token = token;
    }

    public static Builder token(String token) {
        return new Builder(token);
    }

    public String getToken() {
        return token;
    }

    public static class Builder {
        private String token;

        private Builder(String token) {
            this.token = token;
        }

        public AuthResponse build() {
            return new AuthResponse(this.token);
        }
    }
}
