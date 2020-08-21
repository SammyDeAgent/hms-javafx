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

public class Medical {
    private String name;
    private String manufacturer;
    private String expiryDate;

    private int cost;
    private int count;

    public Medical(String name, String manufacturer, String expiryDate, int cost, int count) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.cost = cost;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void MedicalMenu(Stage PrimaryStage, Medical[] medical){

        //Table Initialization
        TableView<Medical> medTB = GUI.tableCreate("Medical");

        //Table Filter
        ObservableList<Medical> medData = FXCollections.observableArrayList();
        ObservableList<Medical> filterData = FXCollections.observableArrayList();
        for (Medical value : medical) {
            if (value != null)
                medData.add(value);
        }
        filterData.addAll(medData);

        Text filterTxt = new Text("Filter Table");
        final TextField filterField = new TextField();
        filterField.setPromptText("Search");
        filterField.setMaxWidth(300);

        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterData.clear();

                for (Medical m : medData) {
                    if (Validation.filter(m, filterField.getText())) {
                        filterData.add(m);
                    }
                }
            }
        });

        HBox searchpane = new HBox();
        searchpane.setSpacing(10);
        searchpane.setAlignment(Pos.CENTER_LEFT);
        searchpane.getChildren().addAll(filterTxt,filterField);
        medTB.setItems(filterData);

        //Table Prompt
        HBox submit = GUI.tablePrompt(PrimaryStage,medical,medTB);

        //Nodes
        Text Title = new Text("Medical Table");
        VBox Pane = GUI.tablePanel(Title,searchpane,medTB,submit);
        HBox Navbar = GUI.tableSelection(PrimaryStage);

        BorderPane root = new BorderPane();
        root.setCenter(Pane);
        root.setBottom(Navbar);

        //Scenes
        Scene window = new Scene(root, 1280, 600);

        //Launch
        PrimaryStage.setTitle("Medical Control Panel");
        PrimaryStage.setScene(window);
        PrimaryStage.show();
    }
}
