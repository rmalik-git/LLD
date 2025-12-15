package org.example;

import org.example.enums.Direction;
import org.example.enums.RequestSource;
import org.example.models.Request;
import org.example.observer.ElevatorDisplay;
import org.example.strategy.ElevatorSelectionStrategy;
import org.example.strategy.NearestElevatorStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ElevatorSystem {
    private static ElevatorSystem instance;
    private final ExecutorService executorService;
    private final ElevatorSelectionStrategy strategy;
    private final Map<Integer, Elevator> elevators;

    private ElevatorSystem(int numElevators) {
        this.strategy = new NearestElevatorStrategy();
        this.executorService = Executors.newFixedThreadPool(numElevators);
        List<Elevator> elevatorList = new ArrayList<>();
        ElevatorDisplay elevatorDisplay = new ElevatorDisplay();

        for (int i = 1; i <= numElevators; i++) {
            Elevator elevator = new Elevator(i);
            elevator.addObserver(elevatorDisplay);
            elevatorList.add(elevator);
        }

        this.elevators = elevatorList.stream().collect(Collectors.toMap(Elevator::getElevatorId,
                e -> e));

    }

    public synchronized static ElevatorSystem getInstance(int numElevators) {
        if (instance == null) {
            instance = new ElevatorSystem(numElevators); // Default to 3 elevators
        }
        return instance;
    }

    public void start() {
        for (Elevator elevator : elevators.values()) {
            executorService.submit(elevator);
        }
    }

    public void requestElevator(int currentFloor, Direction direction) {
        System.out.println(
                "\n>> Elevator requested for floor " + currentFloor + " and direction " + direction);
        Request request = new Request(currentFloor, direction, RequestSource.EXTERNAL);

        Optional<Elevator> selectedElevator = strategy.selectElevator(
                new ArrayList<>(elevators.values()), request);

        if(selectedElevator.isPresent()) {
            selectedElevator.get().addRequest(request);
        } else {
            System.out.println("No suitable elevator found for the request at floor " + currentFloor);
        }
    }

    public void selectFloor(int elevatorId, int targetFloor) {
        System.out.println(
                "\n>> Internal request in Elevator " + elevatorId + " to floor " + targetFloor);
        Request request = new Request(targetFloor, Direction.IDLE, RequestSource.INTERNAL);

        Elevator elevator = elevators.get(elevatorId);
        if(elevator != null) {
            elevator.addRequest(request);
        } else {
            System.out.println("Invalid Elevator ID: " +elevatorId);
        }
    }

    public void shutdown() {
        System.out.println("Shutting down elevator system...");
        for (Elevator elevator : elevators.values()) {
            elevator.stopElevator();
        }
        executorService.shutdown();
    }

}
