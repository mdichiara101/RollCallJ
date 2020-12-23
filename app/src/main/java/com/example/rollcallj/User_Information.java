package com.example.rollcallj;

public class User_Information {
    private String email;
    private String password;
    private String account_type;
    private String First_Name;
    private String Last_Name;
    private String RNumber;

    public User_Information() {
    }

    public User_Information(String email, String password, String account_type, String First_Name, String Last_Name, String RNumber) {
        this.email = email;
        this.password = password;
        this.account_type = account_type;
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.RNumber = RNumber;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getRNumber() {
        return RNumber;
    }

    public void setRNumber(String RNumber) {
        this.RNumber = RNumber;
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

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
