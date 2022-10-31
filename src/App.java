
import javafx.application.*;
import javafx.beans.property.SimpleStringProperty;
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
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PrinterJob;

public class App extends Application {

    private final TableView<Movie> tableChron = new TableView<Movie>();
    private final ObservableList<Movie> data = FXCollections.observableArrayList(
            new Movie("Annie Hall", "Allen", "1978", "4", "Comedy"),
            new Movie("BrokeBack Mountain", "Lee", "2005", "5", "N/A"));

    final HBox hb = new HBox();
    Label response;

    private Desktop desktop = Desktop.getDesktop();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // To create: Add check box next to each movie you add, delete and edit buttons,
        // add a week and month view, and a menu on top so you can save different
        // copies.

        VBox vBox1 = new VBox(); // Create new vertical box.
        Scene scene1 = new Scene(vBox1); // Create scene1.
        vBox1.setSpacing(7); // Spacing for box.

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
        EventHandler<ActionEvent> MEHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                String name = ((MenuItem) ae.getTarget()).getText();

                // if Exit is chosen, the program is terminated.
                if (name.equals("Exit"))
                    Platform.exit();

                response.setText(name + " selected");
            }
        };

        open.setOnAction(MEHandler);
        save.setOnAction(MEHandler);
        print.setOnAction(MEHandler);
        exit.setOnAction(MEHandler);

        final FileChooser fileChooser = new FileChooser();

        open.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File opensFile = fileChooser.showOpenDialog(primaryStage);
                        if (opensFile != null) {
                            openFile(opensFile);
                        }
                    }

                });

        save.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        File savesFile = fileChooser.showSaveDialog(primaryStage);
                        if (savesFile != null) {
                            saveFile(savesFile);
                        }
                    }

                });

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
                });

        final Label label = new Label("Movie List"); // Main window heading label.
        label.setFont(new Font("Arial", 18));
        label.setPadding(new Insets(10, 10, 10, 10));

        tableChron.setEditable(true);

        // Column headings in the tableChron.
        TableColumn movieCol = new TableColumn("Movie");
        TableColumn directorCol = new TableColumn("Director");
        TableColumn yearreleasedCol = new TableColumn("YearReleased");
        TableColumn ratingCol = new TableColumn("Rating");
        TableColumn genresCol = new TableColumn("Genres");
        TableColumn descriptionCol = new TableColumn("Movie Description");

        movieCol.setCellValueFactory(
                new PropertyValueFactory<Movie, String>("Movie"));
        movieCol.setCellFactory(TextFieldTableCell.forTableColumn());
        movieCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setTitle(t.getNewValue());
                    }
                });

        directorCol.setCellValueFactory(
                new PropertyValueFactory<Movie, String>("Director"));
        directorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        directorCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setDirector(t.getNewValue());
                    }
                });

        yearreleasedCol.setCellValueFactory(
                new PropertyValueFactory<Movie, String>("YearReleased"));
        yearreleasedCol.setCellFactory(TextFieldTableCell.forTableColumn());
        yearreleasedCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setYearReleased(t.getNewValue());
                    }
                });

        ratingCol.setCellValueFactory(
                new PropertyValueFactory<Movie, String>("Rating"));
        ratingCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ratingCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setRating(t.getNewValue());
                    }
                });

        genresCol.setCellValueFactory(
                new PropertyValueFactory<Movie, String>("Genres"));
        genresCol.setCellFactory(TextFieldTableCell.forTableColumn());
        genresCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Movie, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Movie, String> t) {
                        ((Movie) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())).setGenres(t.getNewValue());
                    }
                });


        tableChron.setItems(data);
        tableChron.getColumns().addAll(movieCol, directorCol, yearreleasedCol, ratingCol, genresCol, descriptionCol); // Place the
                                                                                                          // column
                                                                                                          // headings in
                                                                                                          // tableChron.
        // Set width
        tableChron.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        movieCol.setMinWidth(150);
        directorCol.setMinWidth(70);
        yearreleasedCol.setMinWidth(60);
        ratingCol.setMinWidth(60);
        genresCol.setMinWidth(120);
        descriptionCol.setMinWidth(200);

        // Create horizontal box.
        HBox hbox1 = new HBox();
        hbox1.setSpacing(8);
        hbox1.setPadding(new Insets(10, 10, 10, 10));

        // Create text fields so the user can enter new movies into the table.
        TextField addTitle = new TextField();
        TextField addDirector = new TextField();
        TextField addYearReleased = new TextField();
        TextField addRating = new TextField();
        TextField addGenres = new TextField();
        TextField addDescription = new TextField();

        // Set initial text in fields.
        addTitle.setText("Enter movie");
        addDirector.setText("Enter director");
        addYearReleased.setText("YearReleased");
        addRating.setText("Rating");
        addGenres.setText("Give report to...");
        addDescription.setText("Add specific descriptions");

        // Set length
        addTitle.setPrefWidth(150);
        addDirector.setPrefWidth(75);
        addYearReleased.setPrefWidth(65);
        addRating.setPrefWidth(65);
        addGenres.setPrefWidth(120);
        addDescription.setPrefWidth(150);

        // Create add button.
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
                addTitle.clear();
                addDirector.clear();
                addYearReleased.clear();
                addRating.clear();
                addGenres.clear();
                addDescription.clear();
            }
        });

        // Get user entry fields all in a horizontal row.
        hbox1.getChildren().addAll(addTitle, addDirector, addYearReleased, addRating, addGenres, addDescription, btnAdd);

        // Get all items in a vertical, stacking format.
        vBox1.getChildren().addAll(menuBar, label, tableChron, hbox1);

        primaryStage.setWidth(750);
        primaryStage.setHeight(500);
        primaryStage.setTitle("Movie List"); // Names the window.
        primaryStage.setScene(scene1); // Sets the scene to the stage.
        primaryStage.show(); // Shows the stage.
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