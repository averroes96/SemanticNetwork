/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import inc.Node;
import inc.Relation;
import inc.SemanticNetwork;
import inc.SpecialAlert;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InheritController implements Initializable {

    @FXML private JFXTextArea all;
    @FXML private JFXComboBox<Node> selectedNode;
    @FXML private JFXButton startBtn;
    
    SpecialAlert alert = new SpecialAlert();
    SemanticNetwork sn = new SemanticNetwork();
    
    public void initNetwork(ObservableList<Node> nodes, ObservableList<Relation> relation){
        
        sn.setNodes(nodes);
        sn.setRelations(relation);
        
        sn.setNodeChildren();
        sn.setNodeParents();
        
        selectedNode.setItems(sn.getNodes());
        
        //sn.MarkPropagationInference();
        
        //resultArea.setText(sn.sol);
        
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
