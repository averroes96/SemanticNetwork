/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks.controllers;

import com.jfoenix.controls.JFXButton;
import inc.Init;
import static inc.Init.APP_ICON;
import inc.SpecialAlert;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ChoiceController implements Initializable, Init {

    
    @FXML private JFXButton markProp,inherit;
    
    SpecialAlert alert = new SpecialAlert();
    
    public void choiceAction(ActionEvent Action, String choice){
        
            try {
                ((javafx.scene.Node)Action.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                stage.getIcons().add(new Image(RelationsController.class.getResourceAsStream(APP_ICON)));
                FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLS_PATH + "Nodes.fxml"));
                Pane root = (Pane)loader.load();
                NodesController mControl = (NodesController)loader.getController();
                mControl.setChoice(choice);
                Scene scene = new Scene(root);
                stage.setScene(scene);              
                stage.show();
            } catch (IOException ex) {
                alert.show(IO_ERROR, ex.getMessage(), Alert.AlertType.ERROR, true);
            }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        markProp.setOnAction(Action -> {
            choiceAction(Action, MARK_PROP);
        });
        inherit.setOnAction(Action -> {
            choiceAction(Action, INHERITANCE);
        });        
        
    }    
    
}
