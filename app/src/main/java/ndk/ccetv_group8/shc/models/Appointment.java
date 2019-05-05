package ndk.ccetv_group8.shc.models;

public class Appointment {
    String name, address, contactNumber, consultationSlot, disease;

    public Appointment(String name, String address, String contactNumber, String consultationSlot, String disease) {
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.consultationSlot = consultationSlot;
        this.disease = disease;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getConsultationSlot() {
        return consultationSlot;
    }

    public void setConsultationSlot(String consultationSlot) {
        this.consultationSlot = consultationSlot;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
