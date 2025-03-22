package book_my_show_app;

import java.util.ArrayList;
import java.util.List;

public class Booking {
    Show show;
    List<Seat> bookedSeats = new ArrayList<>();
    PaymentStrategy payment;
}
