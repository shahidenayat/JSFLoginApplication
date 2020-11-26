package com.sparta.shahid.entities;

public class User {

    private String userName;
    private String password;
    private boolean isAdmin;
    private String name;

    public static User createUser (String userName, String password, boolean isAdmin, String name) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setAdmin(isAdmin);
        user.setName(name);
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


