package com.eventmoment.rahat.eventmoment.Model;

/**
 * Created by rahat on 2/9/2017.
 */

public class Event {
    private int eventId;
    private String title;

    public Event(int eventId, String title) {
        this.eventId = eventId;
        this.title = title;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
