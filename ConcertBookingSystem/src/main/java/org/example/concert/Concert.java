package org.example.concert;

import org.example.seat.Seat;

import java.time.LocalDateTime;
import java.util.List;

public class Concert {

    private final String concertId;
    private final String artist;
    private final String venue;
    private final LocalDateTime dateTime;
    private final List<Seat> seats;

    public Concert(String concertId, String artist, String venue,
                   LocalDateTime dateTime, List<Seat> seats) {
        this.concertId = concertId;
        this.artist = artist;
        this.venue = venue;
        this.dateTime = dateTime;
        this.seats = seats;
    }

    public String getConcertId() {
        return concertId;
    }

    public String getArtist() {
        return artist;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
