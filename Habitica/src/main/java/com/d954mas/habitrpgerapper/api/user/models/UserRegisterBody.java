package com.d954mas.habitrpgerapper.api.user.models;


public class UserRegisterBody {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterBody(String username, String password, String confirmPassword, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }
}
