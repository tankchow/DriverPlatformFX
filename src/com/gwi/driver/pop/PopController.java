package com.gwi.driver.pop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class PopController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
@FXML
private AnchorPane anchorPane;
	public void btnClicked() {
		Alert alert =new Alert(AlertType.ERROR);
		alert.show();
		
		System.out.println("PopController.btnClicked()");
		//anchorPane.getStyleClass();
		anchorPane.getStyleClass().add("pane2");
	}
	
}
