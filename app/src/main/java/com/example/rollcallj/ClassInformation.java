package com.example.rollcallj;

public class ClassInformation {
    private String EndTime;
    private String Name;
    private String RegistrationKey;
    private String StartTime;

    public ClassInformation() {
    }

    public ClassInformation(String endTime, String name, String registrationKey, String startTime) {
        EndTime = endTime;
        Name = name;
        RegistrationKey = registrationKey;
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRegistrationKey() {
        return RegistrationKey;
    }

    public void setRegistrationKey(String registrationKey) {
        RegistrationKey = registrationKey;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }
}
