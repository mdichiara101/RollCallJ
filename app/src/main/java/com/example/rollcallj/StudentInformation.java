package com.example.rollcallj;

public class StudentInformation {
    private String First_Name;
    private String Last_Name;
    private String RNumber;
    private String Email;

    public StudentInformation() {
    }

    public StudentInformation(String first_Name, String last_Name, String RNumber, String email) {
        First_Name = first_Name;
        Last_Name = last_Name;
        this.RNumber = RNumber;
        Email = email;
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
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

