
package com.gwi.driver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private static String MAIN_FXML_PATH = "/com/gwi/driver/Main.fxml";

	@Override
	public void start(Stage primaryStage) {
		try {// BorderPane root = new BorderPane();
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource(MAIN_FXML_PATH));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			// 将primaryStage指定为当前main的controller的stage
			BaseController.mainStage=primaryStage;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 程序入口
	public static void main(String[] args) {
		launch(args);
	}
}