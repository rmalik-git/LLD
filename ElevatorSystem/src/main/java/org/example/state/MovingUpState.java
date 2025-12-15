package org.example.state;


import org.example.Elevator;
import org.example.enums.Direction;
import org.example.models.Request;

public class MovingUpState implements ElevatorState {

    @Override
    public void addRequest(Elevator elevator, Request request) {

    }

    @Override
    public Direction getDirection() {
        return Direction.UP;
    }

    @Override
    public void move(Elevator elevator) {
        if (elevator.getUpRequests().isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }

        Integer nextFloor = elevator.getDownRequests().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);

        if(elevator.getCurrentFloor() == nextFloor) {
            System.out.println("Elevator " + elevator.getElevatorId() + " stopped at floor " + nextFloor);
            elevator.getUpRequests().pollFirst();
        }

        if(elevator.getUpRequests().isEmpty()) {
            elevator.setState(new IdleState());
        }
    }
}
