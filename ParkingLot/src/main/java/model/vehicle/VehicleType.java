package model.vehicle;

public enum VehicleType {
    CAR(1), TRUCK(2), BIKE(1);

    int slotsRequired;

    VehicleType(int slotsRequired) {
        this.slotsRequired = slotsRequired;
    }

    public int getSlotsRequired() {
        return slotsRequired;
    }
}
