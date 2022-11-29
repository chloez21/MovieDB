import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

public class Movie implements Serializable {

        private final SimpleStringProperty title;
        private final SimpleStringProperty director;
        private final SimpleStringProperty yearReleased;
        private final SimpleStringProperty rating;
        private final SimpleStringProperty genres;

        Movie(String title, String director, String yearReleased, String rating, String genres) {

            this.title = new SimpleStringProperty(title);
            this.director = new SimpleStringProperty(director);
            this.yearReleased = new SimpleStringProperty(yearReleased);
            this.rating = new SimpleStringProperty(rating);
            this.genres = new SimpleStringProperty(genres);
        }

        public String getTitle() {
            return title.get();
        }

        public void setTitle(String title1) {
            title.set(title1);
        }

        public String getDirector() {
            return director.get();
        }

        public void setDirector(String director1) {
            director.set(director1);
        }

        public String getYearReleased() {
            return yearReleased.get();
        }

        public void setYearReleased(String yearReleased1) {
            yearReleased.set(yearReleased1);
        }

        public String getRating() {
            return rating.get();
        }

        public void setRating(String rating1) {
            rating.set(rating1);
        }

        public String getGenres() {
            return genres.get();
        }

        public void setGenres(String genres1) {
            genres.set(genres1);
        }
    }