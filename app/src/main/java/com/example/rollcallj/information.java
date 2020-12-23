package com.example.rollcallj;

public class information {

    private String Email;
    private String fName;

    public information() {
    }

    public information(String email, String name) {
        this.Email = email;
        this.fName = name;
    }

    public String getEmail() {
        return Email;
    }

    public String getfName() {
        return fName;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}
