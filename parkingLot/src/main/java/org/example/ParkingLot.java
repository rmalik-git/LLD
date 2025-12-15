package org.example;

import org.example.entities.ParkingFloor;
import org.example.entities.ParkingSpot;
import org.example.entities.ParkingTicket;
import org.example.strategy.fee.FeeStrategy;
import org.example.strategy.parking.ParkingStrategy;
import org.example.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot parkingLot;
    private final List<ParkingFloor> parkingFloors = new ArrayList<>();
    private final Map<String, ParkingTicket> activeParkingTickets;
    private FeeStrategy feeStrategy;
    private ParkingStrategy parkingStrategy;

    private ParkingLot() {
        this.feeStrategy = feeStrategy;
        this.parkingStrategy = parkingStrategy;
        this.activeParkingTickets = new ConcurrentHashMap<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    public void addFloor(ParkingFloor floor) {
        parkingFloors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> availableSpot = parkingStrategy.findParkingSpot(parkingFloors,
                vehicle);
        if (availableSpot.isEmpty()) {
            System.out.println("No available spot for " + vehicle.getLicensePlate());
            return Optional.empty();
        }

        ParkingSpot parkingSpot = availableSpot.get();
        parkingSpot.parkVehicle(vehicle);
        ParkingTicket ticket = new ParkingTicket(vehicle, parkingSpot);
        activeParkingTickets.put(ticket.getTicketId(), ticket);
        System.out.printf("%s parked at %s. Ticket id: %s\n", vehicle.getLicensePlate(),
                parkingSpot.getSpotId(),
                ticket.getTicketId());

        return Optional.of(ticket);


    }

    public Optional<Double> unParkVehicle(ParkingTicket ticket) {
        if(ticket == null){
            return Optional.empty();
        }
        ticket.setExitTime();
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.unparkVehicle();
        activeParkingTickets.remove(ticket.getTicketId());
        double fee = feeStrategy.calculateFee(ticket);
        System.out.printf("%s unparked from %s. Fee: %.2f\n",
                ticket.getVehicle().getLicensePlate(),
                parkingSpot.getSpotId(),
                fee);
        return Optional.of(fee);
    }

}
