package LLD;

import java.util.*;

// Observer interface for notification
interface Observer {
    void update(String message);
}

// Subject interface for managing observers
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

// Represents a Cinema
class Cinema {
    private String name;
    private String location;

    public Cinema(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}

// Represents a Show
class Show {
    private String movieName;
    private Date showTime;
    private Cinema cinema;

    public Show(String movieName, Date showTime, Cinema cinema) {
        this.movieName = movieName;
        this.showTime = showTime;
        this.cinema = cinema;
    }

    public String getMovieName() {
        return movieName;
    }

    public Date getShowTime() {
        return showTime;
    }

    public Cinema getCinema() {
        return cinema;
    }
}

// Manages cinemas and shows
class CinemaManager implements Subject {
    private List<Cinema> cinemas = new ArrayList<>();
    private List<Show> shows = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public void addShow(Show show) {
        shows.add(show);
        notifyObservers("New show added: " + show.getMovieName() + " at " + show.getCinema().getName());
    }

    // Observer pattern methods
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Represents a Booking
class Booking {
    private Show show;
    private int seatNumber;

    public Booking(Show show, int seatNumber) {
        this.show = show;
        this.seatNumber = seatNumber;
    }

    public Show getShow() {
        return show;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}

// Manages ticket bookings
class TicketBookingManager implements Observer {
    private List<Booking> bookings = new ArrayList<>();

    public void bookTicket(Show show, int seatNumber) {
        Booking booking = new Booking(show, seatNumber);
        bookings.add(booking);
        System.out.println("Ticket booked for " + show.getMovieName() + " at seat " + seatNumber);
    }

    @Override
    public void update(String message) {
        System.out.println("Notification received: " + message);
    }
}

// Main class to demonstrate functionality
public class book_my_show {
    public static void main(String[] args) {
        Cinema cinema1 = new Cinema("PVR Cinemas", "Downtown");
        CinemaManager cinemaManager = new CinemaManager();
        TicketBookingManager ticketBookingManager = new TicketBookingManager();

        // Register ticket booking manager as an observer
        cinemaManager.addObserver(ticketBookingManager);

        // Add a cinema and a show
        cinemaManager.addCinema(cinema1);
        Show show1 = new Show("Inception", new Date(), cinema1);
        cinemaManager.addShow(show1);

        // Book a ticket
        ticketBookingManager.bookTicket(show1, 42);
    }
}

