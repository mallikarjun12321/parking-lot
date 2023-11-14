package model.vehicle;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import model.Slot;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class Vehicle {
    @NonNull
    String registrationId;
    Instant entryTime;
    @NonNull
    VehicleType vehicleType;
    List<Slot> occupiedSlots;
    boolean chargesPaid;
}
