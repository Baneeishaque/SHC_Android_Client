package ndk.ccetv_group8.shc.models;

import java.sql.Time;

public class ConsultationSlot {
    private Time slotStart, slotEnd;

    public ConsultationSlot(Time slotStart, Time slotEnd) {
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
    }

    public Time getSlotStart() {
        return slotStart;
    }

    public void setSlotStart(Time slotStart) {
        this.slotStart = slotStart;
    }

    public Time getSlotEnd() {
        return slotEnd;
    }

    public void setSlotEnd(Time slotEnd) {
        this.slotEnd = slotEnd;
    }
}
