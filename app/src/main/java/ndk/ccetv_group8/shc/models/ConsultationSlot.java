package ndk.ccetv_group8.shc.models;

public class ConsultationSlot {
    private String slotStart, slotEnd;

    public ConsultationSlot(String slotStart, String slotEnd) {
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
    }

    public String getSlotStart() {
        return slotStart;
    }

    public void setSlotStart(String slotStart) {
        this.slotStart = slotStart;
    }

    public String getSlotEnd() {
        return slotEnd;
    }

    public void setSlotEnd(String slotEnd) {
        this.slotEnd = slotEnd;
    }
}
