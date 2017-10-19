package com.company.utils;

public class UpdateInterfaceEvent {

    Position position;
    String informations;
    String typeEvent;

    public UpdateInterfaceEvent(Position position, String informations, String typeEvent) {
        this.position = position;
        this.informations = informations;
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

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
    }
}
