package com.example.potion_smith_backend.dtos;

public class UserDTO {

    private String username;
    private String email;
    private String age;
    private String password;

    public UserDTO(String username, String email, String age, String password) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
