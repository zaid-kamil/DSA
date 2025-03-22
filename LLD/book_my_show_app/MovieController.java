package book_my_show_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieController {
    Map<Location, List<Movie>> locationToMovies;
    List<Movie> movies;

    MovieController() {
        locationToMovies = new HashMap<>();
        movies = new ArrayList<>();
    }

    void addMovie(Movie movie, Location location){
        addMovie(movie, location);
        List<Movie> movieList = locationToMovies.getOrDefault(location, new ArrayList<>());
        movieList.add(movie);
        locationToMovies.put(location, movieList);
    }

    Movie getMovieByName(String name){
        for(Movie movie: movies){
            if(movie.name.equals(name)){
                return movie;
            }
        }
        return null;
    }

    List<Movie> getMoviesByLocation(Location location){
        return locationToMovies.get(location);
    }
}
