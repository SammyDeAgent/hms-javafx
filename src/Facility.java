import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Facility {
    private String facility;

    public Facility(String facility) {
        this.facility = facility;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public static void FacilityMenu(Stage PrimaryStage, Facility[] facility){

        //Table Initialization
        TableView<Facility> facTB = GUI.tableCreate("Facility");

        //Table Filter
        ObservableList<Facility> facData = FXCollections.observableArrayList();
        ObservableList<Facility> filterData = FXCollections.observableArrayList();
        for (Facility value : facility) {
            if (value != null)
                facData.add(value);
        }
        filterData.addAll(facData);

        Text filterTxt = new Text("Filter Table");
        final TextField filterField = new TextField();
        filterField.setPromptText("Search");
        filterField.setMaxWidth(300);

        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterData.clear();

                for (Facility f : facData) {
                    if (Validation.filter(f, filterField.getText())) {
                        filterData.add(f);
                    }
                }
            }
        });
        HBox searchpane = new HBox();
        searchpane.setSpacing(10);
        searchpane.setAlignment(Pos.CENTER_LEFT);
        searchpane.getChildren().addAll(filterTxt,filterField);
        facTB.setItems(filterData);

        //Table Prompt
        HBox submit = GUI.tablePrompt(PrimaryStage,facility,facTB);

        //Nodes
        Text Title = new Text("Facility Table");
        VBox Pane = GUI.tablePanel(Title,searchpane,facTB,submit);
        HBox Navbar = GUI.tableSelection(PrimaryStage);

        BorderPane root = new BorderPane();
        root.setCenter(Pane);
        root.setBottom(Navbar);

        //Scenes
        Scene window = new Scene(root, 1280, 600);

        //Launch
        PrimaryStage.setTitle("Facility Control Panel");
        PrimaryStage.setScene(window);
        PrimaryStage.show();
    }
}
