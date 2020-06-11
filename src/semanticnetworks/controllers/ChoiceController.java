/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ChoiceController implements Initializable {

    
    @FXML private JFXButton markProp,inherit;
    @FXML private JFXToggleButton exceptions;
    
    public void choiceAction(ActionEvent Action, String choice){
        
            try {
                ((javafx.scene.Node)Action.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/semanticnetworks/fxmls/Nodes.fxml"));
                Pane root = (Pane)loader.load();
                NodesController mControl = (NodesController)loader.getController();
                mControl.setChoice(choice);
                Scene scene = new Scene(root);
                stage.setScene(scene);              
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NodesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        markProp.setOnAction(Action -> {
            choiceAction(Action, "mark-prop");
        });
        inherit.setOnAction(Action -> {
            choiceAction(Action, "inherit");
        });        
        
    }    
    
}
