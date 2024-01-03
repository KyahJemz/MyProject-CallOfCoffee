package com.example.stephen_project;

public class Accounts{
    private int user_Id;
    private String firstName, lastName, age, mobileNumber, email, username, password;

    public Accounts (int user_Id, String firstName, String lastName, String age, String mobileNumber, String email, String username, String password)
    {
        this.user_Id = user_Id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
