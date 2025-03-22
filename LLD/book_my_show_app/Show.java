package book_my_show_app;

import java.util.ArrayList;
import java.util.List;

public class Show {
    int showId;
    Movie movie;
    Screen screen;
    int showStartTime;
    List<Seat> bookedSeats = new ArrayList<>();
}
