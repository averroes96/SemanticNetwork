/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks;

import static inc.Init.APP_ICON;
import static inc.Init.FXMLS_PATH;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Launcher extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image(Launcher.class.getResourceAsStream(APP_ICON)));
        Parent root = FXMLLoader.load(getClass().getResource(FXMLS_PATH + "Choice.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
