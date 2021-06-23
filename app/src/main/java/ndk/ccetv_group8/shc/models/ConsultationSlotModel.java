package ndk.ccetv_group8.shc.models;

public class ConsultationSlotModel {
    private String slotStart, slotEnd;
    int slotId;

    public ConsultationSlotModel(String slotStart, String slotEnd, int slotId) {
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.slotId = slotId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
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
