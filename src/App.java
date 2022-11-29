
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PrinterJob;

public class App extends Application {

    private final TableView<Movie> tableV = new TableView<Movie>();
    private final ObservableList<Movie> data = FXCollections.observableArrayList(
            new Movie("The Matrix", "Lana Wachowski, Lilly Wachowski", "1999", "5", "Sci-Fi"),
            new Movie("Crouching Tiger, Hidden Dragon", "Ang Lee", "2000", "5", "Action"),
            new Movie("Pulp Fiction", "Quentin Tarantino", "1994", "5", "Crime"),
            new Movie("Frances Ha", "Noah Baumbach", "2012", "5", "Comedy"),
            new Movie("2001: A Space Odyssey", "Stanley Kubrick", "1968", "5", "Sci-Fi"),
            new Movie("Fight Club", "David Fincher", "1999", "5", "Drama")
    );
            

    // final HBox hb = new HBox();
    Label response;

    private Desktop desktop = Desktop.getDesktop();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox vb = new VBox(); // Create new vertical box.

        Scene scene = new Scene(vb); // Create scene.
        vb.setSpacing(7); // Spacing for box.

        response = new Label("Menu");

        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("_File"); // Alt-f shortcut for file
        MenuItem open = new MenuItem("Open...");
        MenuItem save = new MenuItem("Save As...");
        MenuItem print = new MenuItem("Print...");
        MenuItem exit = new MenuItem("Exit");
        SeparatorMenuItem separator = new SeparatorMenuItem();

        fileMenu.getItems().add(open);
        fileMenu.getItems().add(save);
        fileMenu.getItems().add(print);
        fileMenu.getItems().add(separator);
        fileMenu.getItems().add(exit);

        menuBar.getMenus().add(fileMenu);

        // Create one event handler that will handle menu action events.
        EventHandler<ActionEvent> AHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                String name = ((MenuItem) ae.getTarget()).getText();

                // if Exit is chosen, the program is terminated.
                if (name.equals("Exit"))
                    Platform.exit();

                response.setText(name + " selected");
            }
        };

        open.setOnAction(AHandler);
        save.setOnAction(AHandler);
        print.setOnAction(AHandler);
        exit.setOnAction(AHandler);

        final FileChooser fileChooser = new FileChooser();

        // TODO:
        // open.setOnAction(
        //         new EventHandler<ActionEvent>() {
        //             @Override
        //             public void handle(final ActionEvent e) {
        //                 File opensFile = fileChooser.showOpenDialog(primaryStage);
        //                 if (opensFile != null) {
        //                     try{
        //                         FileInputStream fileIn = new FileInputStream(opensFile);
        //                         ObjectInputStream os = new ObjectInputStream(fileIn);
        //                         ArrayList<Movie> movieList;
        //                         movieList = (ArrayList<Movie>)os.readObject();
        //                         System.out.println(movieList.toString());
        //                         System.out.println("before loop");
        //                         for (int i = 0; i < movieList.size(); i++) {
        //                             Movie movie = movieList.get(i);
        //                             System.out.println(movie.toString());
        //                         }
        //                         os.close();
        //                     } catch (IOException i){
        //                         i.printStackTrace();
        //                     } catch (ClassNotFoundException e1) {
        //                         // TODO Auto-generated catch block
        //                         e1.printStackTrace();
        //                     }
                            
        //                 }
        //             }
        //         });

        save.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if (data != null) {
                            System.out.println(data);
                            try {
                                FileOutputStream fileOut = new FileOutputStream("movies.ser");
                                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                                for(Movie m : data){
                                    out.writeObject(m);
                                }
                                out.close();
                                fileOut.close();
                                System.out.printf("Saved!");
                            } catch (IOException i) {
                                i.printStackTrace();
                            }
                        }
                    }

                }
        );

        // Allow a clicks on the print menuItem to open the print dialogue box.
        print.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        PrinterJob job = PrinterJob.createPrinterJob();
                        if (job != null) {
                            boolean showPrintDialog = job.showPrintDialog(primaryStage.getOwner());
                            if (showPrintDialog) {
                                job.endJob();
                            }
                        }
                    }
                }
        );

        // final Label label = new Label("MOVIE LIST"); // Main window heading label.
        // label.setFont(new Font("Arial", 20));
        // label.setPadding(new Insets(10, 10, 10, 10));


        tableV.setEditable(true);

        // Column headings in the tableV.
        TableColumn<Movie,String> titleCol = new TableColumn<>("Title");
        TableColumn<Movie,String> directorCol = new TableColumn<>("Director");
        TableColumn<Movie,String> yearreleasedCol = new TableColumn<>("YearReleased");
        TableColumn<Movie,String> ratingCol = new TableColumn<>("Rating");
        TableColumn<Movie,String> genresCol = new TableColumn<>("Genres");


        titleCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("Title"));
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        titleCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTitle(t.getNewValue());
                    }
                }
        );

        directorCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("Director"));
        directorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        directorCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setDirector(t.getNewValue());
                    }
                });

        yearreleasedCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("YearReleased"));
        yearreleasedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        yearreleasedCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setYearReleased(t.getNewValue());
                    }
                });

        ratingCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("Rating"));
        ratingCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ratingCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setRating(t.getNewValue());
                    }
                });

        genresCol.setCellValueFactory(new PropertyValueFactory<Movie, String>("Genres"));
        genresCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genresCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setGenres(t.getNewValue());
                    }
                });


        tableV.setItems(data);
        tableV.getColumns().addAll(titleCol, directorCol, yearreleasedCol, ratingCol, genresCol); // Place the column headings in tableV.
        // Set width
        tableV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        titleCol.setMinWidth(150);
        directorCol.setMinWidth(70);
        yearreleasedCol.setMinWidth(60);
        ratingCol.setMinWidth(60);
        genresCol.setMinWidth(60);


        // Create horizontal box.
        HBox hbox = new HBox();
        hbox.setSpacing(8);
        hbox.setPadding(new Insets(10, 10, 10, 10));

        // for enter new movies into the table.
        TextField addTitle = new TextField();
        TextField addDirector = new TextField();
        TextField addYearReleased = new TextField();
        TextField addRating = new TextField();
        TextField addGenres = new TextField();

        // Set placeholder text in fields.
        addTitle.setText("movie");
        addDirector.setText("director");
        addYearReleased.setText("Year");
        addRating.setText("Rating");
        addGenres.setText("Genres");

        addTitle.setPrefWidth(150);
        addDirector.setPrefWidth(75);
        addYearReleased.setPrefWidth(65);
        addRating.setPrefWidth(65);
        addGenres.setPrefWidth(65);

        // Create an add button.
        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Movie(
                        addTitle.getText(),
                        addDirector.getText(),
                        addYearReleased.getText(),
                        addRating.getText(),
                        addGenres.getText()));
                System.out.println(addTitle.getText());

                addTitle.clear();
                addDirector.clear();
                addYearReleased.clear();
                addRating.clear();
                addGenres.clear();
            }
        });

        // Get user entry fields all in a horizontal row.
        hbox.getChildren().addAll(addTitle, addDirector, addYearReleased, addRating, addGenres, btnAdd);
        ImageView iv1 = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("scifi.jpg"));
        iv1.setImage(image);
        iv1.setFitWidth(600);
        iv1.setPreserveRatio(true);
        // iv1.setSmooth(true);
        // iv1.setCache(true);
        // Get all items in a vertical, stacking format.
        vb.getChildren().addAll(menuBar, tableV, hbox, iv1);

        primaryStage.setWidth(600);
        primaryStage.setHeight(900);
        primaryStage.setTitle("Movie List"); 
        primaryStage.setScene(scene); 
        scene.getStylesheets().add(getClass().getResource("App.css").toExternalForm());
        primaryStage.show(); 
    }


    private void openFile(File opensFile) {
        try {
            desktop.open(opensFile);
        } catch (IOException ex) {
            Logger.getLogger(
                    App.class.getName()).log(
                            Level.SEVERE, null, ex);
        }
    }

    private void saveFile(File savesFile) {
        try {
            desktop.open(savesFile);
        } catch (IOException ex) {
            Logger.getLogger(
                    App.class.getName()).log(
                            Level.SEVERE, null, ex);
        }
    }
}