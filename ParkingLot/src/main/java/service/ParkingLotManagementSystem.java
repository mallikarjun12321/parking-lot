package service;

import helper.ParkingLotHelper;
import model.ParkingLot;
import model.Slot;
import model.vehicle.Vehicle;

import java.time.Instant;
import java.util.List;

public class ParkingLotManagementSystem {
    ParkingLot parkingLot = new ParkingLot();
    ParkingLotHelper parkingLotHelper = new ParkingLotHelper(parkingLot);

    public void parkVehicle(Vehicle vehicle) {
        if (parkingLotHelper.isVehicleAllowed(vehicle.getVehicleType())) {
            List<Slot> allocatedSlots = parkingLotHelper.allocateSlots(vehicle.getVehicleType().getSlotsRequired());
            if (!allocatedSlots.isEmpty()) {
                allocatedSlots.forEach(allocatedSlot -> parkInSlot(allocatedSlot, vehicle));
            } else {
                System.out.println("Could not find parking slots");
            }
        }
    }

    private void parkInSlot(Slot allocatedSlot, Vehicle vehicle) {
        allocatedSlot.setOccupied(true);
        vehicle.setEntryTime(Instant.now());
        vehicle.getOccupiedSlots().add(allocatedSlot);
    }

    public void exitParking(Vehicle vehicle) {
        Instant exitTime = Instant.now();
        long charges = parkingLotHelper.calculateCharges(vehicle, exitTime);
        payCharges(vehicle, charges);
        if(vehicle.isChargesPaid()) {
            vehicle.getOccupiedSlots().forEach(allocatedSlot -> allocatedSlot.setOccupied(false));
        }
    }

    private void payCharges(Vehicle vehicle, long charges) {
        System.out.println("Charges paid:" + charges);
        vehicle.setChargesPaid(true);
    }
}
