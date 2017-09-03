package com.launchcode.cheesemvc;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {


    private @NotNull @Size(min=5, max=15) String username;

    private @NotEmpty @Email(message = "Please provide valid email") String email;

    private @NotNull @Size(min=6, max=15) String password;

    private @NotEmpty String verify;

    // constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // no args constructor
    public User()
        {}

    // getters & setters
    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify() { return verify; }

    public void setVerify(String verify) {this.verify = verify; }

}
