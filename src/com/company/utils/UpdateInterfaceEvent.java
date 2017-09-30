package com.company.utils;

public class UpdateInterfaceEvent {

    Position position;
    String typeEvent;

    public UpdateInterfaceEvent(Position position, String typeEvent) {
        this.position = position;
        this.typeEvent = typeEvent;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }
}
