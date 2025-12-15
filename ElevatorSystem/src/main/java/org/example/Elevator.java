package org.example;

import org.example.enums.Direction;
import org.example.models.Request;
import org.example.observer.ElevatorObserver;
import org.example.state.ElevatorState;
import org.example.state.IdleState;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Elevator implements Runnable {
    private final int elevatorId;
    private AtomicInteger currentFloor;
    private ElevatorState state;
    private volatile boolean isRunning =true;
    private final TreeSet<Integer> upRequests;
    private final TreeSet<Integer> downRequests;

    private final List<ElevatorObserver> observers = new ArrayList<>();

    public Elevator( int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentFloor = new AtomicInteger(1);
        this.upRequests = new TreeSet<>();
        this.downRequests = new TreeSet<>((a,b)->b-a);
        this.state = new IdleState();
    }

    public void addObserver(ElevatorObserver observer) {
        this.observers.add(observer);
        observer.update(this);
    }

    public void notifyObservers(){
        for(ElevatorObserver observer : observers){
            observer.update(this);
        }
    }

    public void setState(ElevatorState state) {
        this.state = state;
        notifyObservers();
    }

    public void move() {
        state.move(this);
    }

    public synchronized void addRequest(Request request) {
        System.out.println("Elevator " + elevatorId + " processing: " + request);
        state.addRequest(this, request);
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void stopElevator(){
        isRunning = false;
    }
    public TreeSet<Integer> getUpRequests() {
        return upRequests;
    }

    public TreeSet<Integer> getDownRequests() {
        return downRequests;
    }


    public void setCurrentFloor(int currentFloor) {
        this.currentFloor.set(currentFloor);
        notifyObservers();
    }

    @Override
    public void run() {
        while (isRunning) {
            move();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
