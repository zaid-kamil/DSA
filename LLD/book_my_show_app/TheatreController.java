package book_my_show_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreController {
    Map<Location, List<Theatre>> cityTheatreMap;
    List<Theatre> theatres;

    TheatreController() {
        cityTheatreMap = new HashMap<>();
        theatres = new ArrayList<>();
    }

    void addTheatre(Theatre theatre, Location location){
        theatres.add(theatre);
        List<Theatre> theatres = cityTheatreMap.getOrDefault(location, new ArrayList<Theatre>());
        theatres.add(theatre);
        cityTheatreMap.put(location, theatres);
    }

    Map<Theatre, List<Show>> getAllShow(Movie movie, Location loc){
        Map<Theatre, List<Show>> theatreShowMap = new HashMap<>();
        List<Theatre> theatres = cityTheatreMap.get(loc);
        for(Theatre theatre : theatres){
            List<Show> shows = new ArrayList<>();
            for(Show show : theatre.shows){
                if(show.movie.movieId == movie.movieId){
                    shows.add(show);
                }
            }
            if(!shows.isEmpty())
                theatreShowMap.put(theatre, shows);
        }
        return theatreShowMap;
    }
}
