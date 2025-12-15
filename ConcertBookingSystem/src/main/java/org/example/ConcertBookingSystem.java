package org.example;

import org.example.booking.Booking;
import org.example.concert.Concert;
import org.example.exceptions.SeatNotAvailableException;
import org.example.seat.Seat;
import org.example.seat.SeatStatus;
import org.example.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcertBookingSystem {
    private static ConcertBookingSystem instance;

    private final Map<String, Concert> concerts;
    private final Map<String, Booking> bookings;
    private final Object lock = new Object();

    public ConcertBookingSystem() {
        this.bookings = new ConcurrentHashMap<>();
        this.concerts = new ConcurrentHashMap<>();
    }

    public static ConcertBookingSystem getInstance() {
        if (instance == null) {
            instance = new ConcertBookingSystem();
        }
        return instance;
    }

    public void addConcert(Concert concert) {
        concerts.put(concert.getConcertId(), concert);
    }

    public void getConcert(String concertId) {
        concerts.get(concertId);
    }

    public List<Concert> searchConcerts(String artist, String venue, LocalDateTime dateTime) {
        return concerts.values().stream()
                .filter(concert -> (artist == null || concert.getArtist()
                        .equalsIgnoreCase(artist)) &&
                        (venue == null || concert.getVenue().equalsIgnoreCase(venue)) &&
                        (dateTime == null || concert.getDateTime().toLocalDate()
                                .equals(dateTime.toLocalDate()))
                )
                .collect(Collectors.toList());
    }

    public Booking bookTickets(User user, Concert concert, List<Seat> seats) {
        synchronized (lock) {
            for (Seat seat : seats) {
                if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
                    throw new SeatNotAvailableException(
                            "Seat " + seat.getSeatNumber() + " is not available.");
                }
            }
            seats.forEach(Seat::book);

            String bookingId = generateBookingId();
            Booking booking = new Booking(bookingId, user, concert, seats);
            bookings.put(bookingId, booking);
            processPayment(booking);

            booking.confirmBooking();

            System.out.println("\nBooking " + booking.getBookingId() + " - " + booking.getSeats().size() + " " +
                    "seats booked");

            return booking;
        }
    }

    public void cancelTickets(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.cancelBooking();
            bookings.remove(bookingId);
        }
    }

    private void processPayment(Booking booking) {
        System.out.printf("Payment processed for booking ID: %s and amount %s" ,
                booking.getBookingId(), booking.getTotalPrice());
    }

    private String generateBookingId() {
        return "BKG" + UUID.randomUUID();
    }


}
