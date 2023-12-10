package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.util.Random;

public class Main extends Application {
	
	@Override
	public void start(Stage applicationStage) throws Exception {
		
		// initialize menu options
		applicationStage.setTitle("Date / Time & Color Controller");
		MenuItem timeMenuItem = new MenuItem("Show Date & Time");
		MenuItem writeToTextMenuItem = new MenuItem("Create Log File");
		MenuItem changeColorMenuItem = new MenuItem("Change Theme");
		MenuItem exitMenuItem = new MenuItem("Exit Application");
		
		final Text dateAndTime = new Text();

		// create grid pane and set to scene
		GridPane gridPane = new GridPane();   // Create an empty pane
		// UPDATED increased window size for readability
		Scene scene = new Scene(gridPane, 480, 200); // Create scene containing the grid pane
		
		// add empty text field to gridpane
		gridPane.add(dateAndTime, 0, 3, 1, 3);
		
		// create dropdown menu
		MenuButton menuButton = new MenuButton("Options", null, timeMenuItem, writeToTextMenuItem, changeColorMenuItem, exitMenuItem);
		HBox hbox = new HBox(menuButton);
		gridPane.add(hbox, 0, 0, 1, 1);
		
		// initialize method to write text file
		File file = new File("log.txt");
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
	
		// UPDATED - changed random value logic to return a color value closer to orange
		// values to create an initial random orange hue
		Random randomNumber = new Random();
		int minRgbValue2 = 102;
		int maxRgbValue2 = 191;
		int randomHueValue = randomNumber.nextInt(maxRgbValue2 - minRgbValue2 + 1) + minRgbValue2;
		String stringNumber = String.valueOf(randomHueValue);
		
		
		timeMenuItem.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// show date and time
				// Ellision, B. (2022) Java for Beginners: A Crash Course to Learn Java
				LocalDateTime currentTime = LocalDateTime.now();
				// write to text
				dateAndTime.setText(currentTime.toString());
			}
		});
		
		writeToTextMenuItem.setOnAction(event -> {
			
			// NEW - fixed input methods to pass dateAndTime Text value into log.txt file 
			// referenced https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html
			pw.println(dateAndTime);
			pw.flush();
			pw.close();

	
		});
		
		// change background color to show the initial random hue of orange set a the start of the application
		changeColorMenuItem.setOnAction(event -> {
			
			gridPane.setStyle("-fx-background-color: rgb(255," + stringNumber + ", 0);");

		});
		
		// exit program
		exitMenuItem.setOnAction(event -> {
			applicationStage.close();
		});

		applicationStage.setScene(scene);
		applicationStage.show();
	
	}
		
	public static void main(String[] args) {
		Application.launch(args);
	}
}