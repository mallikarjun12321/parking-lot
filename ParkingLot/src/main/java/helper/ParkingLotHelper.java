package helper;

import model.ParkingLot;
import model.Slot;
import model.vehicle.Vehicle;
import model.vehicle.VehicleType;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotHelper {
    private final ParkingLot parkingLot;

    public ParkingLotHelper(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public boolean isVehicleAllowed(final VehicleType vehicleType) {
        return parkingLot.getAllowedVehicleTypes().contains(vehicleType);
    }

    public List<Slot> allocateSlots(int slotsRequired) {
        List<Slot> allocatedSlots = new ArrayList<>();
        List<Slot> slots = parkingLot.getSlots();
        for (int j = 0; j < slots.size(); j++) {
            for (int i = 0; i < slotsRequired; i++) {
                Slot slot = slots.get(i + j);
                if (!slot.isOccupied()) {
                    allocatedSlots.add(slot);
                } else {
                    allocatedSlots.clear();
                    j = i + j;
                    break;
                }
            }
            if(allocatedSlots.size() == slotsRequired) {
                break;
            }
        }
        return allocatedSlots;
    }


    public long calculateCharges(Vehicle vehicle, Instant exitTime) {
        long durationInMinutes = Duration.between(exitTime, vehicle.getEntryTime()).toMinutes();
        int durationInHours = (int) (durationInMinutes / 60);
        int remainingMinutes = (int) (durationInMinutes % 60);
        if(remainingMinutes >30 ) {
            durationInHours++;
        }

        long charges = durationInHours * parkingLot.getChargesPerHour();
        return charges;
    }
}
