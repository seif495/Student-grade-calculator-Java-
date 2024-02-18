package application;
	
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextArea;

public class Main extends Application {

	private static ArrayList<Student> students;

	public Main() {
		students = new ArrayList<>();
	}
	
	public void addStudent(Student student) {
		students.add(student);
	}
	public static double Min() {
		if (students.isEmpty()) {
			throw new IllegalStateException("No grades available.");
		}
			double min = students.get(0).getGrade();
			for (int i = 1 ; i<students.size() ; i++) {
				if (min> students.get(i).getGrade()){
					min = students.get(i).getGrade();
				}
			}
		return min;
	}
	
	public static double Max() {
		if (students.isEmpty()) {
			throw new IllegalStateException("No grades available.");
		}
			double max = students.get(0).getGrade();
			for (int i = 1 ; i<students.size() ; i++) {
				if (max< students.get(i).getGrade()){
					max = students.get(i).getGrade();
				}
			}
		return max;
	}
	
	public static double Average() {
		if (students.isEmpty()) {
			throw new IllegalStateException("No grades available.");
		}
		double average = students.get(0).getGrade();
		double sum = average;
		int i = 1;
		while (i < students.size()) {
			sum+= students.get(i).getGrade();
			i+=1;
		}
		average = sum/students.size();
		return average;
	}
	@Override
	public void start(Stage primaryStage) {
		   Image backgroundImage = new Image(getClass().getResourceAsStream("classroom-2093743_640.jpg"));

	        // Create the background image
	        BackgroundImage background = new BackgroundImage(
	                backgroundImage,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT,
	                BackgroundPosition.DEFAULT,
	                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
	        );
		TextField name = new TextField();
		name.setPromptText("Student name:");
		
		TextField ID = new TextField();
		ID.setPromptText("Student ID:");
		
		TextField grade = new TextField();
		grade.setPromptText("Grade:");
		
		Button enter = new Button("Enter");
		TextArea output = new TextArea();
        output.setEditable(false);
        output.setPrefRowCount(3);
        output.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		enter.setOnAction(event -> {
			String Sname = name.getText();
			int SID = Integer.parseInt(ID.getText());
			double Sgrade = Double.parseDouble(grade.getText());
			
			students.add(new Student(Sname,SID,Sgrade));
			
			double min = Min();
			double max = Max();
			double average = Average();
			output.appendText("The minimum grade is: " + min + "\n");
	        output.appendText("The maximum grade is: " + max + "\n");
	        output.appendText("The average grade is: " + average + "\n");
		});
		enter.setPrefWidth(100);
		Button quitButton = new Button("Quit");
		quitButton.setPrefWidth(100);
		quitButton.setOnAction(event -> primaryStage.close());
			
			
		Label IDL = new Label("Student ID:");
		Label NameL = new Label("Student Name:");
		Label GradeL = new Label("Student grade:");
		IDL.setStyle("-fx-font-weight: bold; -fx-text-fill: blue; -fx-font-size: 20px;");
        NameL.setStyle("-fx-font-weight: bold; -fx-text-fill: red; -fx-font-size: 20px;");
        GradeL.setStyle("-fx-font-weight: bold; -fx-text-fill: green; -fx-font-size: 20px;");
		IDL.setLabelFor(ID);
		NameL.setLabelFor(name);
		GradeL.setLabelFor(grade);
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(enter, quitButton);
		VBox root = new VBox(50);
		root.setBackground(new Background(background));
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(600));
		root.getChildren().addAll(new HBox(10,NameL,name),new HBox(10,IDL,ID),new HBox(10,GradeL,grade),enter,buttonBox,output);
		
		HBox.setMargin(ID, new Insets(0, 0, 0, 34  ));
		Scene scene = new Scene(root,300,200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Student grade calculator");
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		primaryStage.show();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
