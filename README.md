# MovieDB project

I like watching movies and documentaries. So I would like to build a movie database to rate the movie that I have watched.  
Users can add, remove, delete the movies in the database.(CRUD) They can also filte the result list.  
At first, the app will use local storage. Later, I will add database in the backend.  

**UML class diagrams**

![mermaid-diagram-2022-10-31-225115](https://user-images.githubusercontent.com/86437004/199149265-104fd342-b8be-4f3d-8f64-681e15878a32.png)





To learn:
+ springboot   
+ book and music schema  
+ social feature  
+ visualization  


classDiagram
    Movie o-- Actor
    Movie o-- Director

    Person <|-- Director
    Person <|-- Actor

    class Person{
        <<interface>> 
        +String Name
        +String Country
        +getMovie()
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
    }

    class Actor{
    }

    class User{
        +addMovie()
        +editMovie()
        +deleteMovie()
    }
    
    class Director{
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
