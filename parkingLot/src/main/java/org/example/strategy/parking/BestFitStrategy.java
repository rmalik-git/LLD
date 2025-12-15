package org.example.strategy.parking;

import org.example.entities.ParkingFloor;
import org.example.entities.ParkingSpot;
import org.example.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class BestFitStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findParkingSpot(List<ParkingFloor> parkingFloors,
                                                 Vehicle vehicle) {
        Optional<ParkingSpot> bestSpot = Optional.empty();
        for (ParkingFloor floor : parkingFloors) {
            Optional<ParkingSpot> spot = floor.findAvailableParkingSpot(vehicle);
            if (spot.isPresent()) {
                if (bestSpot.isEmpty()) {
                    bestSpot = spot;
                } else {
                    if (spot.get().getSize().ordinal() < bestSpot.get().getSize().ordinal()) {
                        bestSpot = spot;
                    }
                }
            }
        }
        return Optional.empty();
    }
}
