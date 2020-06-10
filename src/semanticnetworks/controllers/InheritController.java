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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

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
        selectedNode.getSelectionModel().selectFirst();
        
        sn.Inheritance();
        
        String msg = sn.sol;
        all.setText(msg);
        
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*ObservableList<Node> nodes = FXCollections.observableArrayList();
        ObservableList<Relation> relations = FXCollections.observableArrayList();
        
        nodes.add(new Node("mortel"));
        nodes.add(new Node("animal"));
        nodes.add(new Node("Vertébrés"));
        nodes.add(new Node("Oiseaux"));
        nodes.add(new Node("Moineaux"));
        nodes.add(new Node("Titi"));
        nodes.add(new Node("os"));
        nodes.add(new Node("Bec_Plumes_Ailes"));
        
        relations.add(new Relation(nodes.get(0),nodes.get(1), "est_un"));
        relations.add(new Relation(nodes.get(1),nodes.get(2), "est_un"));
        relations.add(new Relation(nodes.get(2),nodes.get(3), "est_un"));
        relations.add(new Relation(nodes.get(3),nodes.get(4), "est_un"));
        relations.add(new Relation(nodes.get(4),nodes.get(5), "est_un"));
        relations.add(new Relation(nodes.get(6),nodes.get(2), "a_pour_partie"));
        relations.add(new Relation(nodes.get(7),nodes.get(3), "a_pour_partie"));
        
        initNetwork(nodes, relations);*/
        
        startBtn.setOnAction(Action -> {
            
            String msg = sn.getNodeProperties(selectedNode.getValue());
            if(!"".equals(msg))
                alert.show("RESULT", msg, Alert.AlertType.INFORMATION, false);
            else
                alert.show("RESULT", "No properties were found !", Alert.AlertType.INFORMATION, false);
        });


    }    
    
}
