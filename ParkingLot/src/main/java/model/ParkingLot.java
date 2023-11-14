package model;

import lombok.Data;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingLot {
    private final int numberOfSlots = 100;
    List<Slot> slots;
    List<Vehicle> vehicles;
    List<VehicleType> allowedVehicleTypes;
    long chargesPerHour = 2;

    public ParkingLot() {
        slots = new ArrayList<>();
        allowedVehicleTypes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            slots.add(Slot.builder().id(i).build());
        }
        allowedVehicleTypes.add(VehicleType.CAR);
    }
}
