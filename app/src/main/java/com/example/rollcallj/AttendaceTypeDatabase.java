package com.example.rollcallj;

public class AttendaceTypeDatabase {
    private String keyCode;
    private String GPS;
    private String Facial;

    public AttendaceTypeDatabase() {
    }

    public AttendaceTypeDatabase(String keyCode, String GPS, String facial) {
        this.keyCode = keyCode;
        this.GPS = GPS;
        Facial = facial;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getGPS() {
        return GPS;
    }

    public void setGPS(String GPS) {
        this.GPS = GPS;
    }

    public String getFacial() {
        return Facial;
    }

    public void setFacial(String facial) {
        Facial = facial;
    }
}
