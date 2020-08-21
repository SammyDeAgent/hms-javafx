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

public class Lab {
    private String lab;

    private int cost;

    public Lab(String lab, int cost) {
        this.lab = lab;
        this.cost = cost;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public static void LabMenu(Stage PrimaryStage, Lab[] lab){

        //Table Initialization
        TableView<Lab> labTB = GUI.tableCreate("Lab");

        //Table Filter
        ObservableList<Lab> labData = FXCollections.observableArrayList();
        ObservableList<Lab> filterData = FXCollections.observableArrayList();
        for (Lab value : lab) {
            if (value != null)
                labData.add(value);
        }
        filterData.addAll(labData);

        Text filterTxt = new Text("Filter Table");
        final TextField filterField = new TextField();
        filterField.setPromptText("Search");
        filterField.setMaxWidth(300);

        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterData.clear();

                for (Lab l : labData) {
                    if (Validation.filter(l, filterField.getText())) {
                        filterData.add(l);
                    }
                }
            }
        });
        HBox searchpane = new HBox();
        searchpane.setSpacing(10);
        searchpane.setAlignment(Pos.CENTER_LEFT);
        searchpane.getChildren().addAll(filterTxt,filterField);
        labTB.setItems(filterData);

        //Table Prompt
        HBox submit = GUI.tablePrompt(PrimaryStage,lab,labTB);

        //Nodes
        Text Title = new Text("Lab Table");
        VBox Pane = GUI.tablePanel(Title,searchpane,labTB,submit);
        HBox Navbar = GUI.tableSelection(PrimaryStage);

        BorderPane root = new BorderPane();
        root.setCenter(Pane);
        root.setBottom(Navbar);

        //Scenes
        Scene window = new Scene(root, 1280, 600);

        //Launch
        PrimaryStage.setTitle("Lab Control Panel");
        PrimaryStage.setScene(window);
        PrimaryStage.show();
    }
}
