package com.gwi.driver;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainController extends BaseController implements Initializable {

	private Button btnNewBtn = new Button();

	@FXML
	private Button btnIDCard;

	@FXML
	private AnchorPane acpMain;

	private EventHandler<ActionEvent> btnClick = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			System.out.println("Main OK!" + "  " + ((Button) event.getSource()).getText());
			try {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/com/gwi/driver/idcard/IDCard.fxml"));
				Parent root = fxmlLoader.load();
				Stage newStage = new Stage();
				Scene newScene = new Scene(root);
				newStage.setTitle("二代證");
				newStage.setScene(newScene);
				newStage.initModality(Modality.APPLICATION_MODAL);
				newStage.show();
				BaseController baseController = (BaseController) fxmlLoader.getController();
				baseController.setStage(newStage);
				// BaseController.mainStage.hide();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	};	
	
	private EventHandler<ActionEvent> btnPopup = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			try {
				// BaseController.mainStage.close();
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/com/gwi/driver/pop/PopContent.fxml"));
				Parent acpPop = (AnchorPane) fxmlLoader.load();
				acpPop.getStylesheets()
						.add(getClass().getResource("/com/gwi/driver/pop/popStyle.css").toExternalForm());
				acpPop.getStyleClass().add("pane");
				// acpPop.setStyle("-fx-background-color: #336699;");
				Popup popup = new Popup();
				popup.setX(300);
				popup.setY(300);
				popup.getContent().add(acpPop);
				// popup.getContent().addAll(new Circle( 25,25,50));
				popup.setHideOnEscape(true);
				// popup.is
				popup.show(mainStage);
				System.out.println("popup");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}
	};
	
	private EventHandler<?super MouseEvent> btnClose =new EventHandler< MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			BaseController.mainStage.close();
			System.out.println("匿名类实现");
		};
	};
	

	
	// ActionEvent event
	// public void IDCardOnMouseClicked() {
	// System.out.println("OK");
	// }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 新增按钮
		acpMain.getChildren().add(btnNewBtn);
		btnNewBtn.setPrefHeight(50);
		btnNewBtn.setPrefWidth(50);
		// btnNewBtn.
		// btnNewBtn.setTextAlignment(TextAlignment.CENTER);
		btnNewBtn.setText("关闭");
		btnNewBtn.setFont(Font.font(10));
		AnchorPane.setRightAnchor(btnNewBtn, 0.0);
		
		btnNewBtn.setOnAction(btnPopup);		
		
		// lambda表达式实现
//		btnNewBtn.setOnMouseClicked(e -> {
//			BaseController.mainStage.close();
//			System.out.println("lambda表达式实现");
//		});
		btnNewBtn.setOnMouseClicked(btnClose);

		// 身份证按钮
		btnIDCard.setOnAction(btnClick);

	}
}
