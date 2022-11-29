# MovieDB project

I like watching movies and documentaries. So I would like to build a movie database to rate the movie that I have watched.  
Users can add, edit the movies in the database. They can also sort the list.  


<img width="602" alt="Screen Shot 2022-11-28 at 10 16 10 PM" src="https://user-images.githubusercontent.com/86437004/204430136-d8384270-b8c5-4aff-935c-70971bb83e94.png">

**Add a Movie**
To add a movie, simply enter the desired text in the text field boxes at the bottom and press the "Add" button. 

In order for the table to accept the text, each column uses setters and getters along with an action event and an event handler to access the information from the text fields.


**Edit a Movie**
The TableView class not only renders the table data but also provides the ability to edit it. 
By double-clicking, the text can be edited or erased. In order to retain the text you entered, you must press the Enter key.

This functionality is added by calling the setEditable() control. 






**Demo:**
https://youtu.be/xCFnyLFagDI


# UML class diagrams
![mermaid-diagram-2022-11-27-233415](https://user-images.githubusercontent.com/86437004/204307324-f1be3206-329f-4d2e-8b06-6246070b41e4.png)

```

classDiagram

VBox --* Scene
Scene --* Stage
HBox --* VBox
ImageView --* VBox
Movie --* ObservableList~Movie~
TextField --* HBox
Stage --* App
TableView --* VBox
TableColumn~Movie,String~ --* TableView
Button --*HBox
MenuBar --* VBox
ObservableList~Movie~ --* TableView

    class ObservableList~Movie~{
        +getMovie()
        +setMovie()
    }

    class Movie{
      -String title
      -String director
      -String yearReleased
      -String rating
      -String genres
      +getTitle()
      +setTitle()
      +getDirector()
      +setDirector()
      +getYearReleased()
      +setYearRleased()
      +getRating()
      +setRating()
      +getGenres()
      +setGenres()
    }

    class App{
        +void start(Stage primaryStage)
        +static void main(String[] args)
    }
    class TableColumn~Movie,String~{
        +setCellValueFactory()
        +setCellFactory()
        +setOnEditCommit()
    }
    class TableView{

        +setItems()
        +getColumns()

    }

    class VBox{
        -MenuBar menuBar
        -TableView tableV
        -HBox hbox
        -ImageView iv1
    }

    class HBox{
        +setSpacing()
        +setPadding()
    }
    class TextField{
        +setText()
        +setPrefWidth()
    }

    class ImageView{
        +setImage()
        +setFitWidth()
        +setPreserveRation()
    }

    class Scene{
        +getStylesheets()
    }

    class Stage{
        +setWidth()
        +setHeight()
        +setTitle()
        +setScene()
    }

    class Genre{
        <<enumeration>>
        DRAMA
        COMEDY
        ACTION
        ROMANCE
        FAMILY
        SCIFI
    }
```

