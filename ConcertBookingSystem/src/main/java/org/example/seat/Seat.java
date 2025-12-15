package org.example.seat;

import org.example.exceptions.SeatNotAvailableException;

public class Seat {
    private final String seatId;
    private final String seatNumber;
    private final SeatType seatType;
    private SeatStatus seatStatus;
    private final double price;

    public Seat(String seatId, String seatNumber, SeatType seatType, double price) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public synchronized void book(){
        if(seatStatus == SeatStatus.AVAILABLE){
            seatStatus = SeatStatus.BOOKED;
        } else {
            throw new SeatNotAvailableException("Seat is not available for booking.");
        }
    }

    public synchronized void release(){
        if(seatStatus == SeatStatus.BOOKED){
            seatStatus = SeatStatus.AVAILABLE;
        }
    }

    public String getSeatId() {
        return seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatType getSeatingType() {
        return seatType;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public double getPrice() {
        return price;
    }
}
