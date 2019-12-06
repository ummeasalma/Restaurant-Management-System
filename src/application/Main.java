package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load
					(getClass().getResource("/application/LoginForm.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
