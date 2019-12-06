package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Label loginsts;
	@FXML
	private TextField username;	
	@FXML
	private TextField pass;
	
	public void login(ActionEvent event) throws Exception {
		
		try {
			((Node) event.getSource()).getScene().getWindow().hide();
			LoginManager manager = new LoginManager();
			if(manager.login(username.getText(),pass.getText())) {
				loginsts.setText("Login Successful");
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load
						(getClass().getResource
								("/application/Homepage.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			}
			else loginsts.setText("Login Failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
