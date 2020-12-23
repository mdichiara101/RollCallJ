package com.example.rollcallj;

class ClassAttendanceKey {
    private String KeyCode;
    private String endTime;
    private String startTime;

    public ClassAttendanceKey() {
    }

    public ClassAttendanceKey(String keyCode, String endtime, String starttime) {
        KeyCode = keyCode;
        endTime = endtime;
        startTime = starttime;
    }

    public String getKeyCode() {
        return KeyCode;
    }

    public void setKeyCode(String keyCode) {
        KeyCode = keyCode;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endtime) {
        endTime = endtime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String starttime) {
        startTime = starttime;
    }
}
