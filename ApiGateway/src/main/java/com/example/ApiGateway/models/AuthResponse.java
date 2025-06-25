package com.example.ApiGateway.models;

import java.util.Collection;

public class AuthResponse {

    //this AuthResponse data will come from OKTA objects
    //  @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
    //  @AuthenticationPrincipal OidcUser user

    private String userId;
    private String accessToken;
    private String refreshToken;

    private long expireAt;
    private Collection<String> authorities;
















    public AuthResponse() {
        // Default constructor
    }
    public AuthResponse(String userId, String accessToken, String refreshToken, long expireAt, Collection<String> authorities) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireAt = expireAt;
        this.authorities = authorities;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(long expireAt) {
        this.expireAt = expireAt;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }
}
