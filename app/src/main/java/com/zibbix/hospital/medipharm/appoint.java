package com.zibbix.hospital.medipharm;


class appoint {
    private String date, doctor, session;

    appoint(String date, String doctor, String session) {
        this.date = date;
        this.doctor = doctor;
        this.session = session;

    }

    String getDate() {
        return date;
    }

    String getDoctor() {
        return doctor;
    }

    String getSession() {
        return session;
    }



}
