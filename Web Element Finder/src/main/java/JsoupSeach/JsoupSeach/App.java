package JsoupSeach.JsoupSeach;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
	TextField tfUrl = new TextField();
	TextField tfEle = new TextField();
	TextArea textArea = new TextArea();
	private List<String> elemList = new ArrayList<String>();
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		
		VBox root = new VBox();
		root.setPadding(new Insets(15));
		root.setSpacing(5);
        root.setStyle("-fx-background-color: #F0591E;");
		// Label
		Label lbUrl = new Label("URL:");
		lbUrl.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
		tfUrl.setText("https://");
		Label lbEle = new Label("Element:");
		lbEle.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");

		HBox tarea = new HBox();
		tarea.setPadding(new Insets(0));
		tarea.setSpacing(0);

		// TextArea
		textArea.setStyle("-fx-highlight-fill: lightgray; -fx-highlight-text-fill: firebrick; -fx-font-size: 16px;");
		textArea.setPrefSize(3000, 3000);

		// To contain the buttons
		HBox buttonBar = new HBox();

		// Button to Append text
		Button buttonSearch = new Button("Search");
		buttonSearch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				seachPage();

			}
		});

		Button buttonClear = new Button("Clear");
		buttonClear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textArea.clear();
			}

		});
		tfEle.setOnKeyPressed(event -> {
			   if(event.getCode() == KeyCode.ENTER){
				   seachPage();
			   }
			}); 
		
		tarea.getChildren().addAll(textArea);
		buttonBar.getChildren().addAll(buttonSearch, buttonClear);
		root.getChildren().addAll(lbUrl, tfUrl, lbEle, tfEle, tarea, buttonBar);

		Scene scene = new Scene(root, 1000, 650);
		primaryStage.setTitle("Web Element Finder");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public void seachPage() {
		String url = tfUrl.getText();
		String ele = tfEle.getText();
		int count = 1;
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements allElements = doc.getAllElements();
			Elements elems = allElements.select(ele);
			
			for (Element elem : elems) {
				textArea.appendText("Element "+ count++ +" : " + elem + "\n");
				String element = elem.toString();
				elemList.add(element);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
