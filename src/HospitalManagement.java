//Coded in IntelliJ IDEA - disable com.company package to be used in Eclipse Oxygen IDE default package

//External imports
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

//App
public class HospitalManagement extends Application{

	//HSM Object Array
	static Staff[] staff = new Staff[100];
	static Doctor[] doctor = new Doctor[25];
	static Patient[] patient = new Patient[100];
	static Medical[] medical = new Medical[100];
	static Lab[] lab = new Lab[20];
	static Facility[] facility = new Facility[20];

	//Functions
	@Override
	public void start(Stage PrimaryStage) {
		try {
			//Initial Declare
			staff[0] = new Staff("180","Sammy Yee", "Junior IT Operator", "Male", 2760);
			staff[1] = new Staff("175","Senku Ishigami", "Intern", "Male", 1750);
			staff[2] = new Staff("478","Emmy Hawoltzer", "Janitor", "Female", 2060);
			staff[3] = new Staff("510","Kim Possible", "Umeni Zulu Security Consultant", "Female", 3570);
			staff[4] = new Staff("058","Bon Fuccacimo ", "Executive Chef", "Male", 3270);

			doctor[0] = new Doctor("180","Sammy Yee","Immunologist", "0800-1900","DO,MD,MBBS",165);
			doctor[1] = new Doctor("774","Albert Klein","Neurologist", "0900-2100","MD,MBBS",245);
			doctor[2] = new Doctor("058","Jane Emberson","Family Physician", "1040-1800","MD,MBBS",187);
			doctor[3] = new Doctor("980","Amir Isbar Singh","Radiologist", "0700-1930","DO,MD,MBBS",125);
			doctor[4] = new Doctor("342","Olobona Okuyone","Psychiatrist", "0800-1900","DO,MD",269);

			patient[0] = new Patient("180","Sammy Yee","PTSD","Male","No",20);
			patient[1] = new Patient("222","Fira Schmitte","COVID-19 Viral Infection","Female","Yes",36);
			patient[2] = new Patient("145","Kazir Al-Mulmar","Tetanus","Male","No",8);
			patient[3] = new Patient("721","John Alley","Spinal Dislocation","Male","Yes",67);
			patient[4] = new Patient("025","Miyuki Shirogane","Love Sick","Male","No",17);
			patient[5] = new Patient("024","Kaguya Shinomiya","Love Sick","Female","No",17);

			medical[0] = new Medical("Ascorbic acid (250mL vial)","Vita-Care","2021-07-12",450,631);
			medical[1] = new Medical("Aspirin (50mg tablet)","Cytokine Pharmaceutical","2022-05-06",2100,14);
			medical[2] = new Medical("T-Virus Sample (100mL vial)","Umbrella Corporations","1986-12-14",4700,2);
			medical[3] = new Medical("a-Methamphetamine (250mg bottle)","General Health Pharmacy","2020-10-15",950,237);
			medical[4] = new Medical("5% Sodium water (500mL IV-bag)","General Health Pharmacy","2020-04-27",850,566);

			lab[0] = new Lab("Virus Lab",105000);
			lab[1] = new Lab("Cancer Research",155000);
			lab[2] = new Lab("X-Ray",386000);
			lab[3] = new Lab("Medicine R&D I",250000);
			lab[4] = new Lab("Medicine R&D II",250000);

			facility[0] = new Facility("Canteen");
			facility[1] = new Facility("Children's Ward");
			facility[2] = new Facility("Ward I");
			facility[3] = new Facility("Ward II");
			facility[4] = new Facility("Surgery Hall");

			//Launch
			PrimaryStage.getIcons().add(new Image("/icon.png"));
			PrimaryWindow.initUI(PrimaryStage);

		} catch (FileNotFoundException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("File Not Found");
			a.show();

		}
	}

	//For testing purposes
	private void test(){
		System.out.println("This line is executed!");
	}

	//Launch
	public static void main(String[] args) { launch(args); }

}

class PrimaryWindow extends HospitalManagement{

	public static void initUI(Stage PrimaryStage) throws FileNotFoundException {

		//Nodes
		VBox Pane = GUI.welcomePanel();
		HBox Navbar = GUI.welcomeSelection(PrimaryStage);

		BorderPane root = new BorderPane();
		root.setCenter(Pane);
		root.setBottom(Navbar);

		//Scenes
		Scene window = new Scene(root, 1280, 600);

		//Launch
		PrimaryStage.setTitle("HMS Application");
		PrimaryStage.setScene(window);
		PrimaryStage.show();
	}

	public static void MainMenu(Stage PrimaryStage) {

		//Nodes
		VBox Pane = GUI.menuPanel(PrimaryStage, staff, doctor, patient, medical, lab, facility);
		HBox Navbar = GUI.menuSelection(PrimaryStage);
		HBox ImgHolder = new HBox();
		StackPane ImgSide = new StackPane();
		ImgSide.setAlignment(Pos.CENTER);
		ImgSide.getChildren().add(new ImageView(new Image("/side.png", 400,400,true,false)));
		ImgHolder.getChildren().add(ImgSide);
		HBox parentPane = new HBox();
		parentPane.getChildren().addAll(Pane,ImgHolder);

		BorderPane root = new BorderPane();
		root.setCenter(parentPane);
		root.setBottom(Navbar);

		//Scenes
		Scene window = new Scene(root, 1280, 600);

		//Launch
		PrimaryStage.setTitle("Main Menu");
		PrimaryStage.setScene(window);
		PrimaryStage.show();
	}

}