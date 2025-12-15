package org.example.state;

import org.example.Elevator;
import org.example.enums.Direction;
import org.example.models.Request;

public interface ElevatorState {
    void addRequest(Elevator elevator, Request request);
    Direction getDirection();
    void move(Elevator elevator);
}
