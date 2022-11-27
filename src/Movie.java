import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;

public class Movie implements Serializable {

        private final SimpleStringProperty title;
        private final SimpleStringProperty director;
        private final SimpleStringProperty yearReleased;
        private final SimpleStringProperty rating;
        private final SimpleStringProperty genres;

        Movie(String title1, String director1, String yearReleased1, String rating1, String genres1) {

            this.title = new SimpleStringProperty(title1);
            this.director = new SimpleStringProperty(director1);
            this.yearReleased = new SimpleStringProperty(yearReleased1);
            this.rating = new SimpleStringProperty(rating1);
            this.genres = new SimpleStringProperty(genres1);
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