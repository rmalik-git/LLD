package org.example.entities;

import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleSize;

public class ParkingSpot {
    private final String spotId;
    private final VehicleSize size;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

   public ParkingSpot(String spotId, VehicleSize size) {
        this.spotId = spotId;
        this.size = size;
        this.isOccupied = false;
        this.parkedVehicle = null;
   }

    public String getSpotId() {
        return spotId;
    }

    public VehicleSize getSize() {
        return size;
    }

    public synchronized boolean isAvailable(){
        return !isOccupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public synchronized void unparkVehicle(){
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
       if(isOccupied) return false;

       switch(vehicle.getSize()){
              case SMALL:
                return true;
              case MEDIUM:
                return this.size == VehicleSize.MEDIUM || this.size == VehicleSize.LARGE;
              case LARGE:
                return this.size == VehicleSize.LARGE;
              default:
                return false;
       }
    }
}
