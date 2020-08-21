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

public class Staff {

    private String id;
    private String name;
    private String designation;
    private String sex;
    private int salary;

    public Staff(String id, String name, String designation, String sex, int salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.sex = sex;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public static void StaffMenu(Stage PrimaryStage, Staff[] staff) {

        //Table Initialization
        TableView<Staff> staffTB = GUI.tableCreate("Staff");

        //Table Filter
        ObservableList<Staff> staffData = FXCollections.observableArrayList();
        ObservableList<Staff> filterData = FXCollections.observableArrayList();
        for (Staff value : staff) {
            if (value != null)
                staffData.add(value);
        }
        filterData.addAll(staffData);

        Text filterTxt = new Text("Filter Table");
        final TextField filterField = new TextField();
        filterField.setPromptText("Search");
        filterField.setMaxWidth(300);

        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterData.clear();

                for (Staff s : staffData) {
                    if (Validation.filter(s, filterField.getText())) {
                        filterData.add(s);
                    }
                }
            }
        });

        HBox searchpane = new HBox();
        searchpane.setSpacing(10);
        searchpane.setAlignment(Pos.CENTER_LEFT);
        searchpane.getChildren().addAll(filterTxt,filterField);
        staffTB.setItems(filterData);

        //Table Prompt
        HBox submit = GUI.tablePrompt(PrimaryStage,staff,staffTB);

        //Nodes
        Text Title = new Text("Staff Table");
        VBox Pane = GUI.tablePanel(Title,searchpane,staffTB,submit);
        HBox Navbar = GUI.tableSelection(PrimaryStage);

        BorderPane root = new BorderPane();
        root.setCenter(Pane);
        root.setBottom(Navbar);

        //Scenes
        Scene window = new Scene(root, 1280, 600);

        //Launch
        PrimaryStage.setTitle("Staff Control Panel");
        PrimaryStage.setScene(window);
        PrimaryStage.show();
    }

}

