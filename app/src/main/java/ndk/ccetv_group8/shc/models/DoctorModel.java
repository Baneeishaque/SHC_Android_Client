package ndk.ccetv_group8.shc.models;

public class DoctorModel {
    private int id;
    private String name, address, designation, workingHospital, certificateID, workingClinic;
    private String availableTimeStart, availableTimeEnd;
    private Double consultationFee;

    public DoctorModel(int id, String name, String address, String designation, String workingHospital, String certificateID, String workingClinic, String availableTimeStart, String availableTimeEnd, Double consultationFee) {
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

    public String getAvailableTimeStart() {
        return availableTimeStart;
    }

    public void setAvailableTimeStart(String availableTimeStart) {
        this.availableTimeStart = availableTimeStart;
    }

    public String getAvailableTimeEnd() {
        return availableTimeEnd;
    }

    public void setAvailableTimeEnd(String availableTimeEnd) {
        this.availableTimeEnd = availableTimeEnd;
    }

    public Double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Double consultationFee) {
        this.consultationFee = consultationFee;
    }
}
