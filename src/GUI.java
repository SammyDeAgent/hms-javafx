import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class GUI {

    public static VBox welcomePanel() {

        Text welcomeText = new Text("Welcome to Hospital Management System");
        welcomeText.setStyle("-fx-font: 36 arial;");

        Date current = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d yyyy - hh:mm:ss aaa");
        Text welcomeSub = new Text(dateFormat.format(current));
        welcomeSub.setStyle("-fx-font: 20 arial;");

        VBox welcomePane = new VBox();
        welcomePane.setSpacing(10);
        welcomePane.getChildren().add(welcomeText);
        welcomePane.getChildren().add(welcomeSub);
        welcomePane.setPadding(new Insets(15, 12, 15, 12));

        Image welcomeIMG = new Image("/hospital.jpeg");
        ImageView imgV = new ImageView(welcomeIMG);
        imgV.setFitHeight(410);
        imgV.setFitWidth(1255);
        welcomePane.getChildren().add(imgV);

        return welcomePane;
    }

    public static VBox menuPanel(Stage PrimaryStage, Staff[] staff, Doctor[] doctor, Patient[] patient, Medical[] medical, Lab[] lab, Facility[] facility) {

        Text Title = new Text("HMS Control Panel");
        Title.setStyle("-fx-font: 36 arial;");
        Text menuSub = new Text("Warning, unauthorized personnel are prohibited. Please contact the IT Department if you are not supposed to see this.");
        menuSub.setStyle("-fx-font: 16 arial;");

        VBox menupane = new VBox();
        menupane.setSpacing(10);
        menupane.getChildren().addAll(Title, menuSub);
        menupane.setPadding(new Insets(15, 12, 15, 12));

        String[] btnBuffer = {"Staff", "Doctor", "Patient", "Medical", "Lab", "Facility"};
        String[] descBuffer = {
                "All the current staff working within the facility including the 3rd party members.",
                "Qualified medical professionals that is currently active in the facility.",
                "All active patients which is recorded by the administrators in this facility.",
                "Indexed and recorded medical stocks in the warehouse, also include live samples.",
                "Recorded laboratory that is currently active and in-use by the researchers.",
                "Summation of all sub-facilities with in the hospital ground, construction site not included."
        };

        for (int i = 0; i < 6; i++) {
            Button btn = new Button(btnBuffer[i]);
            btn.setPrefSize(100, 50);
            switch (i) {
                case 0:
                    btn.setOnAction((ActionEvent event) -> Staff.StaffMenu(PrimaryStage, staff));
                    break;
                case 1:
                    btn.setOnAction((ActionEvent event) -> Doctor.DoctorMenu(PrimaryStage, doctor));
                    break;
                case 2:
                    btn.setOnAction((ActionEvent event) -> Patient.PatientMenu(PrimaryStage, patient));
                    break;
                case 3:
                    btn.setOnAction((ActionEvent event) -> Medical.MedicalMenu(PrimaryStage, medical));
                    break;
                case 4:
                    btn.setOnAction((ActionEvent event) -> Lab.LabMenu(PrimaryStage, lab));
                    break;
                case 5:
                    btn.setOnAction((ActionEvent event) -> Facility.FacilityMenu(PrimaryStage, facility));
                    break;
            }
            Text txt = new Text(descBuffer[i]);
            HBox hb = new HBox(10);
            hb.setAlignment(Pos.CENTER_LEFT);
            hb.getChildren().addAll(btn, txt);
            menupane.getChildren().add(hb);
        }
        return menupane;
    }

    public static VBox tablePanel(Text Title, Pane searchpane, TableView tb, HBox submit) {
        Title.setStyle("-fx-font: 36 arial;");

        VBox menupane = new VBox();
        menupane.setSpacing(10);
        menupane.getChildren().add(Title);
        menupane.getChildren().add(searchpane);
        menupane.setPadding(new Insets(15, 12, 15, 12));
        menupane.getChildren().add(tb);
        menupane.getChildren().add(submit);

        return menupane;
    }

    public static HBox welcomeSelection(Stage PrimaryStage) {

        Button btnEXT = new Button();
        btnEXT.setText("Quit");
        btnEXT.setOnAction((ActionEvent event) -> Platform.exit());

        Button btnNEXT = new Button();
        btnNEXT.setText("Continue");
        btnNEXT.setOnAction((ActionEvent event) -> PrimaryWindow.MainMenu(PrimaryStage));

        btnEXT.setPrefSize(95, 40);
        btnNEXT.setPrefSize(95, 40);

        HBox selection = new HBox();
        selection.getChildren().add(btnNEXT);
        selection.setPadding(new Insets(15, 12, 15, 12));
        selection.setSpacing(10);
        selection.setStyle("-fx-background-color: #336699;");
        StackPane quitbox = new StackPane();
        quitbox.getChildren().add(btnEXT);
        quitbox.setAlignment(Pos.CENTER_RIGHT);
        selection.getChildren().add(quitbox);
        HBox.setHgrow(quitbox, Priority.ALWAYS);

        return selection;
    }

    public static HBox menuSelection(Stage PrimaryStage) {

        Button btnEXT = new Button();
        btnEXT.setText("Quit");
        btnEXT.setOnAction((ActionEvent event) -> Platform.exit());

        Button btnBACK = new Button();
        btnBACK.setText("Back");
        btnBACK.setOnAction((ActionEvent event) -> {
            try {
                PrimaryWindow.initUI(PrimaryStage);
            } catch (FileNotFoundException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("File Not Found");
                a.show();
            }
        });

        btnEXT.setPrefSize(95, 40);
        btnBACK.setPrefSize(95, 40);

        HBox selection = new HBox();
        selection.getChildren().add(btnBACK);
        selection.setPadding(new Insets(15, 12, 15, 12));
        selection.setSpacing(10);
        selection.setStyle("-fx-background-color: #336699;");
        StackPane quitbox = new StackPane();
        quitbox.getChildren().add(btnEXT);
        quitbox.setAlignment(Pos.CENTER_RIGHT);
        selection.getChildren().add(quitbox);
        HBox.setHgrow(quitbox, Priority.ALWAYS);

        return selection;
    }

    public static HBox tableSelection(Stage PrimaryStage) {

        Button btnEXT = new Button();
        btnEXT.setText("Quit");
        btnEXT.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        Button btnBACK = new Button();
        btnBACK.setText("Back");
        btnBACK.setOnAction((ActionEvent event) -> {
            PrimaryWindow.MainMenu(PrimaryStage);
        });

        btnEXT.setPrefSize(95, 40);
        btnBACK.setPrefSize(95, 40);

        HBox selection = new HBox();
        selection.getChildren().add(btnBACK);
        selection.setPadding(new Insets(15, 12, 15, 12));
        selection.setSpacing(10);
        selection.setStyle("-fx-background-color: #336699;");
        StackPane quitbox = new StackPane();
        quitbox.getChildren().add(btnEXT);
        quitbox.setAlignment(Pos.CENTER_RIGHT);
        selection.getChildren().add(quitbox);
        HBox.setHgrow(quitbox, Priority.ALWAYS);

        return selection;
    }

    public static <obj> TableView<obj> tableCreate(String s) {
        TableView<obj> tb = new TableView<>();
        tb.setEditable(true);

        if (s.equals("Staff")) {
            TableColumn staffIdCol = new TableColumn("ID");
            staffIdCol.setMinWidth(200);
            TableColumn staffNameCol = new TableColumn("Name");
            staffNameCol.setMinWidth(270);
            TableColumn staffDesCol = new TableColumn("Designation");
            staffDesCol.setMinWidth(350);
            TableColumn staffGenderCol = new TableColumn("Gender");
            staffGenderCol.setMinWidth(150);
            TableColumn staffSalCol = new TableColumn("Salary");
            staffSalCol.setMinWidth(250);

            staffIdCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("id"));
            staffNameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));
            staffDesCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("designation"));
            staffGenderCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("sex"));
            staffSalCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("salary"));

            tb.getColumns().clear();
            tb.getColumns().addAll(staffIdCol, staffNameCol, staffDesCol, staffGenderCol, staffSalCol);
        } else if (s.equals("Doctor")) {
            TableColumn doctorIdCol = new TableColumn("ID");
            doctorIdCol.setMinWidth(150);
            TableColumn doctorNameCol = new TableColumn("Name");
            doctorNameCol.setMinWidth(270);
            TableColumn doctorSpecCol = new TableColumn("Specialist");
            doctorSpecCol.setMinWidth(250);
            TableColumn doctorWTCol = new TableColumn("Work Time");
            doctorWTCol.setMinWidth(150);
            TableColumn doctorQCol = new TableColumn("Qualification");
            doctorQCol.setMinWidth(210);
            TableColumn doctorRCol = new TableColumn("Room Number");
            doctorRCol.setMinWidth(150);

            doctorIdCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("id"));
            doctorNameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
            doctorSpecCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("specialist"));
            doctorWTCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("workTime"));
            doctorQCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("qualification"));
            doctorRCol.setCellValueFactory(new PropertyValueFactory<Doctor, Integer>("room"));

            tb.getColumns().clear();
            tb.getColumns().addAll(doctorIdCol, doctorNameCol, doctorSpecCol, doctorWTCol, doctorQCol, doctorRCol);
        }else if(s.equals("Patient")){
            TableColumn patientIdCol = new TableColumn("ID");
            patientIdCol.setMinWidth(150);
            TableColumn patientNameCol = new TableColumn("Name");
            patientNameCol.setMinWidth(270);
            TableColumn patientDCol = new TableColumn("Disease");
            patientDCol.setMinWidth(320);
            TableColumn patientGenderCol = new TableColumn("Gender");
            patientGenderCol.setMinWidth(150);
            TableColumn patientASCol = new TableColumn("Admit Status");
            patientASCol.setMinWidth(120);
            TableColumn patientAgeCol = new TableColumn("Age");
            patientAgeCol.setMinWidth(100);

            patientIdCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("id"));
            patientNameCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("name"));
            patientDCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("disease"));
            patientGenderCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("sex"));
            patientASCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("admitStatus"));
            patientAgeCol.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("age"));

            tb.getColumns().clear();
            tb.getColumns().addAll(patientIdCol,patientNameCol,patientDCol,patientGenderCol,patientASCol,patientAgeCol);
        }else if(s.equals("Medical")){
            TableColumn medNameCol = new TableColumn("Name");
            medNameCol.setMinWidth(270);
            TableColumn medManCol = new TableColumn("Manufacturer");
            medManCol.setMinWidth(320);
            TableColumn medExpCol = new TableColumn("Expiry Date");
            medExpCol.setMinWidth(200);
            TableColumn medCostCol = new TableColumn("Cost");
            medCostCol.setMinWidth(180);
            TableColumn medCountCol = new TableColumn("Quantity");
            medCountCol.setMinWidth(150);

            medNameCol.setCellValueFactory(new PropertyValueFactory<Medical,String>("name"));
            medManCol.setCellValueFactory(new PropertyValueFactory<Medical,String>("manufacturer"));
            medExpCol.setCellValueFactory(new PropertyValueFactory<Medical,String>("expiryDate"));
            medCostCol.setCellValueFactory(new PropertyValueFactory<Medical,String>("cost"));
            medCountCol.setCellValueFactory(new PropertyValueFactory<Medical,String>("count"));

            tb.getColumns().clear();
            tb.getColumns().addAll(medNameCol,medManCol,medExpCol,medCostCol,medCountCol);
        }else if(s.equals("Lab")){
            TableColumn labCol = new TableColumn("Lab");
            labCol.setMinWidth(700);
            TableColumn costCol = new TableColumn("Cost");
            costCol.setMinWidth(200);

            labCol.setCellValueFactory(new PropertyValueFactory<Lab, String>("lab"));
            costCol.setCellValueFactory(new PropertyValueFactory<Lab, Integer>("cost"));

            tb.getColumns().clear();
            tb.getColumns().addAll(labCol,costCol);
        }else if(s.equals("Facility")){
            TableColumn facCol = new TableColumn("Facility");
            facCol.setMinWidth(1000);

            facCol.setCellValueFactory(new PropertyValueFactory<Facility,String>("facility"));

            tb.getColumns().clear();
            tb.getColumns().addAll(facCol);
        }

        return tb;
    }

    public static HBox tablePrompt(Stage PrimaryStage, Staff[] staff, TableView<Staff> staffTB) {
        final TextField addId = new TextField();
        addId.setPromptText("ID");
        addId.setMaxWidth(100);
        final TextField addName = new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(250);
        final TextField addDes = new TextField();
        addDes.setPromptText("Designation");
        addDes.setMaxWidth(300);
        final ComboBox addSex = new ComboBox();
        addSex.getItems().addAll(
                "Male",
                "Female"
        );
        addSex.setPromptText("Gender");
        final TextField addSal = new TextField();
        addSal.setPromptText("Salary");
        addSal.setMaxWidth(150);

        final Button btnAdd = new Button("Add");
        btnAdd.setOnAction((ActionEvent event) -> {
            for (int i = 0; i < staff.length; i++) {
                if (staff[i] == null) {
                    if (Validation.valid(staff,addId.getText(), addName.getText(), addDes.getText(), (String) addSex.getValue(), addSal.getText())) {
                        staff[i] = new Staff(addId.getText(), addName.getText(), addDes.getText(), (String) addSex.getValue(), parseInt(addSal.getText()));
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Incorrect syntax or empty field! Record is not added.");
                        a.show();
                    }
                    break;
                }
            }
            addId.clear();
            addName.clear();
            addDes.clear();
            addSal.clear();
            Staff.StaffMenu(PrimaryStage, staff);
        });

        final Button btnDEL = new Button();
        btnDEL.setText("Delete");
        btnDEL.setOnAction(e -> {
            Staff selectedItem = staffTB.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No row or empty row are selected! Record is unchanged.");
                a.show();
            } else {
                for (int i = 0; i < staff.length; i++) {
                    if (selectedItem.getId().equals(staff[i].getId())) {
                        if (staff.length - 1 - i >= 0)
                            System.arraycopy(staff, i + 1, staff, i, staff.length - 1 - i);
                        Staff.StaffMenu(PrimaryStage, staff);
                        break;
                    }
                }
            }
        });
        HBox submit = new HBox();
        submit.getChildren().addAll(addId, addName, addDes, addSex, addSal, btnAdd, btnDEL);
        submit.setSpacing(3);
        return submit;
    }

    public static HBox tablePrompt(Stage PrimaryStage, Doctor[] doctor, TableView<Doctor> doctorTB) {
        final TextField addId = new TextField();
        addId.setPromptText("ID");
        addId.setMaxWidth(100);
        final TextField addName = new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(250);
        final TextField addSpec = new TextField();
        addSpec.setPromptText("Specialist");
        addSpec.setMaxWidth(300);
        final TextField addWT = new TextField();
        addWT.setPromptText("Work Time");
        addWT.setMaxWidth(120);
        final TextField addQ = new TextField();
        addQ.setPromptText("Qualifications");
        addQ.setMaxWidth(200);
        final TextField addRoom = new TextField();
        addRoom.setPromptText("Room Number");
        addRoom.setMaxWidth(150);

        final Button btnAdd = new Button("Add");
        btnAdd.setOnAction((ActionEvent event) -> {
            for (int i = 0; i < doctor.length; i++) {
                if (doctor[i] == null) {
                    if (Validation.valid(doctor, addId.getText(), addName.getText(), addSpec.getText(), addWT.getText(), addQ.getText(), addRoom.getText())) {
                        doctor[i] = new Doctor(addId.getText(), addName.getText(), addSpec.getText(), addWT.getText(), addQ.getText(), parseInt(addRoom.getText()));
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Incorrect syntax or empty field! Record is not added.");
                        a.show();
                    }
                    break;
                }
            }
            addId.clear();
            addName.clear();
            addSpec.clear();
            addWT.clear();
            addQ.clear();
            addRoom.clear();
            Doctor.DoctorMenu(PrimaryStage, doctor);
        });

        final Button btnDEL = new Button();
        btnDEL.setText("Delete");
        btnDEL.setOnAction(e -> {
            Doctor selectedItem = doctorTB.getSelectionModel().getSelectedItem();
            if (selectedItem == null) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No row or empty row are selected! Record is unchanged.");
                a.show();
            } else {
                for (int i = 0; i < doctor.length; i++) {
                    if (selectedItem.getId().equals(doctor[i].getId())) {
                        if (doctor.length - 1 - i >= 0)
                            System.arraycopy(doctor, i + 1, doctor, i, doctor.length - 1 - i);
                        Doctor.DoctorMenu(PrimaryStage, doctor);
                        break;
                    }
                }
            }
        });
        HBox submit = new HBox();
        submit.getChildren().addAll(addId, addName, addSpec, addWT, addQ, addRoom, btnAdd, btnDEL);
        submit.setSpacing(3);
        return submit;
    }

    public static HBox tablePrompt(Stage PrimaryStage, Patient[] patient, TableView<Patient> patientTB) {
        final TextField addId = new TextField();
        addId.setPromptText("ID");
        addId.setMaxWidth(100);
        final TextField addName= new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(250);
        final TextField addD= new TextField();
        addD.setPromptText("Disease");
        addD.setMaxWidth(300);
        final ComboBox addSex = new ComboBox();
        addSex.getItems().addAll(
                "Male",
                "Female"
        );
        addSex.setPromptText("Gender");
        final ComboBox addAS = new ComboBox();
        addAS.getItems().addAll(
                "Yes",
                "No"
        );
        addAS.setMaxWidth(180);
        addAS.setPromptText("Admit Status");
        final TextField addAge = new TextField();
        addAge.setPromptText("Age");
        addAge.setMaxWidth(150);

        final Button btnAdd = new Button("Add");
        btnAdd.setOnAction((ActionEvent event)-> {
            for(int i = 0; i < patient.length; i++){
                if(patient[i]==null){
                    if(Validation.valid(patient,addId.getText(),addName.getText(),addD.getText(),(String)addSex.getValue(),(String)addAS.getValue(),addAge.getText())){
                        patient[i] = new Patient(addId.getText(),addName.getText(),addD.getText(),(String)addSex.getValue(),(String)addAS.getValue(),parseInt(addAge.getText()));
                    }else{
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Incorrect syntax or empty field! Record is not added.");
                        a.show();
                    }
                    break;
                }
            }
            addId.clear();
            addName.clear();
            addD.clear();
            addAge.clear();
            Patient.PatientMenu(PrimaryStage, patient);
        });

        final Button btnDEL = new Button();
        btnDEL.setText("Delete");
        btnDEL.setOnAction(e -> {
            Patient selectedItem = patientTB.getSelectionModel().getSelectedItem();
            if(selectedItem==null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No row or empty row are selected! Record is unchanged.");
                a.show();
            }
            else {
                for (int i = 0; i < patient.length; i++) {
                    if (selectedItem.getId().equals(patient[i].getId())) {
                        if (patient.length - 1 - i >= 0)
                            System.arraycopy(patient, i + 1, patient, i, patient.length - 1 - i);
                        Patient.PatientMenu(PrimaryStage, patient);
                        break;
                    }
                }
            }
        });

        HBox submit = new HBox();
        submit.getChildren().addAll(addId,addName,addD,addSex,addAS,addAge, btnAdd, btnDEL);
        submit.setSpacing(3);
        return submit;
    }

    public static HBox tablePrompt(Stage PrimaryStage, Medical[] medical, TableView<Medical> medTB) {
        final TextField addName = new TextField();
        addName.setPromptText("Name");
        addName.setMaxWidth(250);
        final TextField addMan = new TextField();
        addMan.setPromptText("Manufacturer");
        addMan.setMaxWidth(400);
        final DatePicker addExp = new DatePicker();
        addExp.setPromptText("Expiry Date");
        addExp.setShowWeekNumbers(true);
        final TextField addCost = new TextField();
        addCost.setPromptText("Cost");
        addCost.setMaxWidth(150);
        final TextField addCount = new TextField();
        addCount.setPromptText("Quantity");
        addCount.setMaxWidth(150);

        final Button btnAdd = new Button("Add");
        btnAdd.setOnAction((ActionEvent event)-> {
            String ExpDate;
            if(addExp.getValue()!=null)
                ExpDate = addExp.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            else{
                ExpDate = "";
            }
            for(int i = 0; i < medical.length; i++) {
                if (medical[i] == null) {
                    if(Validation.valid(medical, addName.getText(),addMan.getText(),ExpDate,addCost.getText(),addCount.getText())){
                        medical[i] = new Medical(addName.getText(),addMan.getText(),ExpDate,parseInt(addCost.getText()),parseInt(addCount.getText()));
                    }else{
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Incorrect syntax or empty field! Record is not added.");
                        a.show();
                    }
                    break;
                }
            }
            addName.clear();
            addMan.clear();
            addCost.clear();
            addCount.clear();
            Medical.MedicalMenu(PrimaryStage, medical);
        });

        final Button btnDEL = new Button();
        btnDEL.setText("Delete");
        btnDEL.setOnAction(e -> {
            Medical selectedItem = medTB.getSelectionModel().getSelectedItem();
            if(selectedItem==null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No row or empty row are selected! Record is unchanged.");
                a.show();
            }
            else {
                for (int i = 0; i < medical.length; i++) {
                    if (selectedItem.getName().equals(medical[i].getName())) {
                        if (medical.length - 1 - i >= 0)
                            System.arraycopy(medical, i + 1, medical, i, medical.length - 1 - i);
                        Medical.MedicalMenu(PrimaryStage, medical);
                        break;
                    }
                }
            }
        });

        HBox submit = new HBox();
        submit.getChildren().addAll(addName, addMan,addExp,addCost, addCount,btnAdd, btnDEL);
        submit.setSpacing(3);
        return submit;
    }

    public static HBox tablePrompt(Stage PrimaryStage, Lab[] lab, TableView<Lab> labTB) {
        final TextField addLab = new TextField();
        addLab.setPromptText("Lab");
        addLab.setMaxWidth(500);
        final TextField addCost = new TextField();
        addCost.setPromptText("Cost");
        addCost.setMaxWidth(200);

        final Button btnAdd = new Button("Add");
        btnAdd.setOnAction((ActionEvent event)-> {
            for (int i = 0; i < lab.length; i++) {
                if (lab[i] == null) {
                    if (Validation.valid(lab, addLab.getText(), addCost.getText())) {
                        lab[i] = new Lab(addLab.getText(), parseInt(addCost.getText()));
                    } else {
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Incorrect syntax or empty field! Record is not added.");
                        a.show();
                    }
                    break;
                }
            }
            addLab.clear();
            addCost.clear();
            Lab.LabMenu(PrimaryStage, lab);
        });

        final Button btnDEL = new Button();
        btnDEL.setText("Delete");
        btnDEL.setOnAction(e -> {
            Lab selectedItem = labTB.getSelectionModel().getSelectedItem();
            if(selectedItem==null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No row or empty row are selected! Record is unchanged.");
                a.show();
            }
            else {
                for (int i = 0; i < lab.length; i++) {
                    if (selectedItem.getLab().equals(lab[i].getLab())) {
                        if (lab.length - 1 - i >= 0)
                            System.arraycopy(lab, i + 1, lab, i, lab.length - 1 - i);
                        Lab.LabMenu(PrimaryStage, lab);
                        break;
                    }
                }
            }
        });

        HBox submit = new HBox();
        submit.getChildren().addAll(addLab, addCost,btnAdd, btnDEL);
        submit.setSpacing(3);
        return submit;
    }

    public static HBox tablePrompt(Stage PrimaryStage, Facility[] facility, TableView<Facility> facTB) {
        final TextField addFac = new TextField();
        addFac.setPromptText("Facility");
        addFac.setMaxWidth(650);

        final Button btnAdd = new Button("Add");
        btnAdd.setOnAction((ActionEvent event)-> {
            for(int i = 0; i < facility.length; i++) {
                if (facility[i] == null) {
                    if(Validation.valid(facility,addFac.getText())){
                        facility[i] = new Facility(addFac.getText());
                    }else{
                        Alert a = new Alert(Alert.AlertType.ERROR);
                        a.setContentText("Incorrect syntax or empty field! Record is not added.");
                        a.show();
                    }
                    break;
                }
            }
            addFac.clear();
            Facility.FacilityMenu(PrimaryStage, facility);
        });

        final Button btnDEL = new Button();
        btnDEL.setText("Delete");
        btnDEL.setOnAction(e -> {
            Facility selectedItem = facTB.getSelectionModel().getSelectedItem();
            if(selectedItem==null){
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("No row or empty row are selected! Record is unchanged.");
                a.show();
            }
            else {
                for (int i = 0; i < facility.length; i++) {
                    if (selectedItem.getFacility().equals(facility[i].getFacility())) {
                        if (facility.length - 1 - i >= 0)
                            System.arraycopy(facility, i + 1, facility, i, facility.length - 1 - i);
                        Facility.FacilityMenu(PrimaryStage, facility);
                        break;
                    }
                }
            }
        });

        HBox submit = new HBox();
        submit.getChildren().addAll(addFac,btnAdd, btnDEL);
        submit.setSpacing(3);
        return submit;
    }
}
