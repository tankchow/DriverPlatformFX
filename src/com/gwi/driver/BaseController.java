package com.gwi.driver;

import javafx.stage.Stage;

public class BaseController {
	private Stage stage;
	public static Stage mainStage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void close() {
		if (stage != null) {
			stage.close();
		} else {
			System.out.println("stage is null");
		}
	}
	
	public void hide() {
		if (stage != null) {
			stage.hide();
		} else {
			System.out.println("stage is null");
		}
	}

}
