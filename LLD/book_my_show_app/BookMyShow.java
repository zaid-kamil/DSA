package book_my_show_app;

public class BookMyShow {
    MovieController movieController;
    TheatreController theatreController;

    BookMyShow() {
        movieController = new MovieController();
        theatreController = new TheatreController();
    }

    void createBooking(Location location, Movie movie){}
    void init(){}
    void createTheatre(){}
    void createScreen(){}
    void createSeats(){}
    void createMovie(){}
}
