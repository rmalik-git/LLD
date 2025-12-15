package org.example.state;

import org.example.Elevator;
import org.example.enums.Direction;
import org.example.enums.RequestSource;
import org.example.models.Request;

public class MovingDownState implements ElevatorState {

    @Override
    public void addRequest(Elevator elevator, Request request) {
        if(request.getSource()== RequestSource.INTERNAL){
            if(request.getTargetFloor()>elevator.getCurrentFloor()){
                elevator.getUpRequests().add(request.getTargetFloor());
            } else {
                elevator.getDownRequests().add(request.getTargetFloor());
            }
            return;
        }

        if(request.getSource()== RequestSource.EXTERNAL){
            if(request.getDirection() ==Direction.DOWN && request.getTargetFloor()<=elevator.getCurrentFloor()){
                elevator.getDownRequests().add(request.getTargetFloor());
            }else if(request.getDirection()==Direction.UP ){
                elevator.getUpRequests().add(request.getTargetFloor());
            }
        }
    }

    @Override
    public Direction getDirection() {
        return Direction.DOWN;
    }

    @Override
    public void move(Elevator elevator) {
        if (elevator.getDownRequests().isEmpty()) {
            elevator.setState(new IdleState());
            return;
        }

        Integer nextFloor = elevator.getDownRequests().first();
        elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);

        if(elevator.getCurrentFloor() == nextFloor) {
            System.out.println("Elevator " + elevator.getElevatorId() + " stopped at floor " + nextFloor);
            elevator.getDownRequests().pollFirst();
        }

        if(elevator.getDownRequests().isEmpty()) {
            elevator.setState(new IdleState());
        }
    }
}
