from datetime import datetime

# Observer interface
class Observer:
    def update(self, message: str):
        pass

# Subject interface
class Subject:
    def add_observer(self, observer: Observer):
        pass

    def remove_observer(self, observer: Observer):
        pass

    def notify_observers(self, message: str):
        pass

# Represents a Cinema
class Cinema:
    def __init__(self, name: str, location: str):
        self.name = name
        self.location = location

    def get_name(self) -> str:
        return self.name

    def get_location(self) -> str:
        return self.location

# Represents a Show
class Show:
    def __init__(self, movie_name: str, show_time: datetime, cinema: Cinema):
        self.movie_name = movie_name
        self.show_time = show_time
        self.cinema = cinema

    def get_movie_name(self) -> str:
        return self.movie_name

    def get_show_time(self) -> datetime:
        return self.show_time

    def get_cinema(self) -> Cinema:
        return self.cinema

# Manages Cinemas and Shows
class CinemaManager(Subject):
    def __init__(self):
        self.cinemas = []
        self.shows = []
        self.observers = []

    def add_cinema(self, cinema: Cinema):
        self.cinemas.append(cinema)

    def add_show(self, show: Show):
        self.shows.append(show)
        self.notify_observers(f"New show added: {show.get_movie_name()} at {show.get_cinema().get_name()}")

    # Subject methods
    def add_observer(self, observer: Observer):
        self.observers.append(observer)

    def remove_observer(self, observer: Observer):
        self.observers.remove(observer)

    def notify_observers(self, message: str):
        for obs in self.observers:
            obs.update(message)

# Represents a Booking
class Booking:
    def __init__(self, show: Show, seat_number: int):
        self.show = show
        self.seat_number = seat_number

    def get_show(self) -> Show:
        return self.show

    def get_seat_number(self) -> int:
        return self.seat_number

# Manages ticket bookings
class TicketBookingManager(Observer):
    def __init__(self):
        self.bookings = []

    def book_ticket(self, show: Show, seat_number: int):
        booking = Booking(show, seat_number)
        self.bookings.append(booking)
        print(f"Ticket booked for {show.get_movie_name()} at seat {seat_number}")

    def update(self, message: str):
        print(f"Notification received: {message}")

# Main function to demonstrate functionality
if __name__ == "__main__":
    cinema1 = Cinema("PVR Cinemas", "Downtown")
    cinema_manager = CinemaManager()
    ticket_booking_manager = TicketBookingManager()

    # Register observer
    cinema_manager.add_observer(ticket_booking_manager)

    # Add a cinema and a show
    cinema_manager.add_cinema(cinema1)
    show1 = Show("Inception", datetime.now(), cinema1)
    cinema_manager.add_show(show1)

    # Book a ticket
    ticket_booking_manager.book_ticket(show1, 42)