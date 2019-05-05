package ndk.ccetv_group8.shc.models;

import java.sql.Time;

public class Doctor {
    private int id;
    private String name, address, designation, workingHospital, certificateID, workingClinic;
    private Time availableTimeStart, availableTimeEnd;
    private Double consultationFee;

    public Doctor(int id, String name, String address, String designation, String workingHospital, String certificateID, String workingClinic, Time availableTimeStart, Time availableTimeEnd, Double consultationFee) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.designation = designation;
        this.workingHospital = workingHospital;
        this.certificateID = certificateID;
        this.workingClinic = workingClinic;
        this.availableTimeStart = availableTimeStart;
        this.availableTimeEnd = availableTimeEnd;
        this.consultationFee = consultationFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getWorkingHospital() {
        return workingHospital;
    }

    public void setWorkingHospital(String workingHospital) {
        this.workingHospital = workingHospital;
    }

    public String getCertificateID() {
        return certificateID;
    }

    public void setCertificateID(String certificateID) {
        this.certificateID = certificateID;
    }

    public String getWorkingClinic() {
        return workingClinic;
    }

    public void setWorkingClinic(String workingClinic) {
        this.workingClinic = workingClinic;
    }

    public Time getAvailableTimeStart() {
        return availableTimeStart;
    }

    public void setAvailableTimeStart(Time availableTimeStart) {
        this.availableTimeStart = availableTimeStart;
    }

    public Time getAvailableTimeEnd() {
        return availableTimeEnd;
    }

    public void setAvailableTimeEnd(Time availableTimeEnd) {
        this.availableTimeEnd = availableTimeEnd;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }
}
