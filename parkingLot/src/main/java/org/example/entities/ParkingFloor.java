package org.example.entities;

import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleSize;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ParkingFloor {
    private final int floorNumber;
    private final Map<String, ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingSpots = new ConcurrentHashMap<>();
    }

    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpots.put(parkingSpot.getSpotId(), parkingSpot);
    }

    public synchronized Optional<ParkingSpot> findAvailableParkingSpot(Vehicle vehicle) {
       return parkingSpots.values().stream()
               .filter(parkingSpot -> !parkingSpot.isOccupied() && parkingSpot.canFitVehicle(vehicle))
               .sorted(Comparator.comparing(ParkingSpot::getSize))
               .findFirst();
    }

    public void displayAvailability(){
        System.out.println("Parking Floor " + floorNumber + " Availability:");
        Map<VehicleSize, Long> availability = parkingSpots.values().stream()
                .filter(parkingSpot -> !parkingSpot.isOccupied())
                .collect(Collectors.groupingBy(ParkingSpot::getSize, Collectors.counting()));

        for (VehicleSize size : VehicleSize.values()) {
        System.out.println("  " + size + " " + availability.getOrDefault(size, 0L));
        }
    }
}
