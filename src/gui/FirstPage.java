package GUI;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Cyrine
 */

public class FirstPage extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
            Parent root = FXMLLoader.load(getClass().getResource("MenuPatissier.fxml"));
        Scene scene = new Scene(root);
         
        primaryStage.setTitle("admin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
