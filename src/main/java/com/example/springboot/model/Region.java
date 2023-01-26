package com.example.springboot.model;

public enum Region {
    AMERICAS, EUROPE, ASIA, AFRICA, OCEANIA, ANTARCTIC, NA;

    public boolean isConnection(Region destination) {
        if (this.equals(destination)) {
            return true;
        }

        if ((this.equals(AFRICA) || this.equals(ASIA) || this.equals(EUROPE))
            && (destination.equals(AFRICA) || destination.equals(ASIA) || destination.equals(EUROPE))) {
            return true;
        }

        return false;
    }

}
