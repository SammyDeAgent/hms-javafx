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

public class Patient {
    private String id;
    private String name;
    private String disease;
    private String sex;
    private String admitStatus;

    private int age;

    public Patient(String id, String name, String disease, String sex, String admitStatus, int age) {
        this.id = id;
        this.name = name;
        this.disease = disease;
        this.sex = sex;
        this.admitStatus = admitStatus;
        this.age = age;
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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAdmitStatus() {
        return admitStatus;
    }

    public void setAdmitStatus(String admitStatus) {
        this.admitStatus = admitStatus;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void PatientMenu(Stage PrimaryStage, Patient[] patient){

        //Table Initialization
        TableView<Patient> patientTB = GUI.tableCreate("Patient");

        //Table Filter
        ObservableList<Patient> patientData = FXCollections.observableArrayList();
        ObservableList<Patient> filterData = FXCollections.observableArrayList();
        for (Patient value : patient) {
            if (value != null)
                patientData.add(value);
        }
        filterData.addAll(patientData);

        Text filterTxt = new Text("Filter Table");
        final TextField filterField = new TextField();
        filterField.setPromptText("Search");
        filterField.setMaxWidth(300);

        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterData.clear();

                for (Patient p : patientData) {
                    if (Validation.filter(p, filterField.getText())) {
                        filterData.add(p);
                    }
                }
            }
        });

        HBox searchpane = new HBox();
        searchpane.setSpacing(10);
        searchpane.setAlignment(Pos.CENTER_LEFT);
        searchpane.getChildren().addAll(filterTxt,filterField);
        patientTB.setItems(filterData);

        //Table Prompt
        HBox submit = GUI.tablePrompt(PrimaryStage,patient,patientTB);

        //Nodes
        Text Title = new Text("Patient Table");
        VBox Pane = GUI.tablePanel(Title,searchpane,patientTB,submit);
        HBox Navbar = GUI.tableSelection(PrimaryStage);

        BorderPane root = new BorderPane();
        root.setCenter(Pane);
        root.setBottom(Navbar);

        //Scenes
        Scene window = new Scene(root, 1280, 600);

        //Launch
        PrimaryStage.setTitle("Patient Control Panel");
        PrimaryStage.setScene(window);
        PrimaryStage.show();
    }
}
