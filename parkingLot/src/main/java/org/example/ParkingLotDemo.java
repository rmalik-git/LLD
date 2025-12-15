package org.example;

import org.example.entities.ParkingFloor;
import org.example.entities.ParkingSpot;
import org.example.entities.ParkingTicket;
import org.example.strategy.fee.VehicleSizeBasedFeeStrategy;
import org.example.strategy.parking.NearestFirstStrategy;
import org.example.vehicle.Bike;
import org.example.vehicle.Car;
import org.example.vehicle.Truck;
import org.example.vehicle.Vehicle;
import org.example.vehicle.VehicleSize;

import java.util.Optional;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();

        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addParkingSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addParkingSpot(new ParkingSpot("F1-M1", VehicleSize.MEDIUM));
        floor1.addParkingSpot(new ParkingSpot("F1-L1", VehicleSize.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addParkingSpot(new ParkingSpot("F2-M1", VehicleSize.MEDIUM));
        floor2.addParkingSpot(new ParkingSpot("F2-M2", VehicleSize.MEDIUM));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        parkingLot.setFeeStrategy(new VehicleSizeBasedFeeStrategy());
        parkingLot.setParkingStrategy(new NearestFirstStrategy());

        System.out.println("\n--- Vehicle Entries ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle bike = new Bike("BIKE123");
        Vehicle car = new Car("CAR123");
        Vehicle truck = new Car("TRUCK123");

        Optional<ParkingTicket> bikeTicket1 = parkingLot.parkVehicle(bike);
        Optional<ParkingTicket> carTicket1 = parkingLot.parkVehicle(car);
        Optional<ParkingTicket> truckTicket1 = parkingLot.parkVehicle(truck);

        System.out.println("\n--- Availability after parking ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle car2 = new Car("C-999");
        Optional<ParkingTicket> car2TicketOpt = parkingLot.parkVehicle(car2);

        // 4. Simulate a vehicle entry that fails (no available spots)
        Vehicle truck2 = new Truck("T-000");
        Optional<ParkingTicket> failedTruck = parkingLot.parkVehicle(truck2);

        // 5. Simulate vehicle exits and fee calculation
        System.out.println("\n--- Vehicle Exits ---");

        if (carTicket1.isPresent()) {
            Optional<Double> feeOpt = parkingLot.unParkVehicle(carTicket1.get());
            feeOpt.ifPresent(fee -> System.out.printf("Car C-456 unparked. Fee: $%.2f\n", fee));
        }

        System.out.println("\n--- Availability after one car leaves ---");
        floor1.displayAvailability();
        floor2.displayAvailability();
    }
}