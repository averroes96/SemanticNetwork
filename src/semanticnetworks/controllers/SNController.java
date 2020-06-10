/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SNController implements Initializable {
    
    @FXML private Label labelThree,labelFour,labelFive;
    @FXML private JFXComboBox<Node> nodeOne,nodeTwo,nodeThree,nodeFour,nodeFive;
    @FXML private JFXComboBox<Relation> selectedRel;
    @FXML private JFXToggleButton toggleThree,toggleFour,toggleFive;
    @FXML private JFXButton startBtn;

    SemanticNetwork sn = new SemanticNetwork();
    String strone,strtwo,strthree,strfour,strfive;
    SpecialAlert alert = new SpecialAlert();
    
    public void initNetwork(ObservableList<Node> nodes, ObservableList<Relation> relation){
        
        int cpt = 0;
        
        sn.setNodes(nodes);
        sn.setRelations(relation);
        
        sn.setNodeChildren();
        sn.setNodeParents();
                
        nodeOne.setItems(sn.getNodes());
        nodeTwo.setItems(sn.getNodes());
        nodeThree.setItems(sn.getNodes());
        nodeFour.setItems(sn.getNodes());
        nodeFive.setItems(sn.getNodes());           
        
        selectedRel.setItems(sn.getRelations());     
        
        //sn.MarkPropagationInference();
        
        //resultArea.setText(sn.sol);
        
    }
    
    public void selectNode(JFXComboBox<Node> node, String str){
        
            if(sn.getNodeByLabel(str) != null){
            sn.getNodeByLabel(str).setIsMarked(false);
            }
            sn.getNodeByLabel(node.getValue().getLabel()).setIsMarked(true);
            
            sn.getNodes().forEach((nd) -> {
                System.out.println("Name = " + nd.getLabel() + "\t" + "Marked? = " + nd.getIsMarked());
            });
        
    }
    
    public void toggleAction(JFXToggleButton toggle, JFXComboBox<Node> node, Label label){
        
            if(toggle.isSelected()){
                node.setDisable(false);
                label.setDisable(false);
                if(!node.getSelectionModel().isEmpty()){
                    sn.getNodeByLabel(node.getValue().getLabel()).setIsMarked(true);
                }
            }
            else{
                node.setDisable(true);
                label.setDisable(true);
                if(!node.getSelectionModel().isEmpty()){
                    sn.getNodeByLabel(node.getValue().getLabel()).setIsMarked(false);
                }                
            }        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*ObservableList<Node> nodes = FXCollections.observableArrayList();
        ObservableList<Relation> relations = FXCollections.observableArrayList();
        
        nodes.add(new Node("Reiter"));
        nodes.add(new Node("Modes de Représentations des connaissances"));
        nodes.add(new Node("Axe-IA"));
        nodes.add(new Node("Modes Logiques"));
        nodes.add(new Node("Modes Graphiques"));
        nodes.add(new Node("Logiques Classiques"));
        nodes.add(new Node("Logiques Non classiques"));
        nodes.add(new Node("Logique D’ordre 1"));
        nodes.add(new Node("Logique D’ordre 0"));
        nodes.add(new Node("Logique Modale"));
        nodes.add(new Node("Logique Des défauts"));
        nodes.add(new Node("Logiques De description"));
        nodes.add(new Node("Axiome A4"));
        nodes.add(new Node("Système T"));
        nodes.add(new Node("Système D"));
        nodes.add(new Node("Système S5"));
        nodes.add(new Node("Axiome A7"));
        nodes.add(new Node("a>a"));
        
        relations.add(new Relation(nodes.get(1),nodes.get(0), "dev"));
        relations.add(new Relation(nodes.get(2),nodes.get(1), "is_a"));
        relations.add(new Relation(nodes.get(1),nodes.get(3), "is_a"));
        relations.add(new Relation(nodes.get(1),nodes.get(4), "is_a"));
        relations.add(new Relation(nodes.get(3),nodes.get(5), "is_a"));
        relations.add(new Relation(nodes.get(3),nodes.get(6), "is_a"));
        relations.add(new Relation(nodes.get(5),nodes.get(7), "is_a"));
        relations.add(new Relation(nodes.get(5),nodes.get(8), "is_a"));
        relations.add(new Relation(nodes.get(6),nodes.get(9), "is_a"));
        relations.add(new Relation(nodes.get(6),nodes.get(10), "is_a"));
        relations.add(new Relation(nodes.get(6),nodes.get(11), "is_a"));
        relations.add(new Relation(nodes.get(12),nodes.get(7), "contient"));
        relations.add(new Relation(nodes.get(9),nodes.get(13), "is_a"));
        relations.add(new Relation(nodes.get(9),nodes.get(14), "is_a"));
        relations.add(new Relation(nodes.get(9),nodes.get(15), "is_a"));
        relations.add(new Relation(nodes.get(16),nodes.get(13), "contient"));
        relations.add(new Relation(nodes.get(16),nodes.get(15), "contient"));
        relations.add(new Relation(nodes.get(16),nodes.get(17), "is_a"));
        
        initNetwork(nodes, relations);*/
        
        strone = "";
        strtwo = "";
        strthree = "";
        strfour = "";
        strfive = "";
        
        nodeOne.setOnAction(Action -> {
            
            selectNode(nodeOne, strone);
            strone = nodeOne.getValue().getLabel();
        
        });
        
        nodeTwo.setOnAction(Action -> {
            
            selectNode(nodeTwo, strtwo);
            strtwo = nodeTwo.getValue().getLabel();
        
        });
        
        nodeThree.setOnAction(Action -> {
            
            selectNode(nodeThree, strthree);
            strthree = nodeThree.getValue().getLabel();
        
        });
        
        nodeFour.setOnAction(Action -> {
            
            selectNode(nodeFour, strfour);
            strfour = nodeFour.getValue().getLabel();
        
        });
        
        nodeFive.setOnAction(Action -> {
            
            selectNode(nodeFive, strfive);
            strfive = nodeFive.getValue().getLabel();
        
        });

        toggleThree.setOnAction(Action -> {
            toggleAction(toggleThree, nodeThree, labelThree);
        });
       
        toggleFour.setOnAction(Action -> {
            toggleAction(toggleFour, nodeFour, labelFour);
        });

        toggleFive.setOnAction(Action -> {
            toggleAction(toggleFive, nodeFive, labelFive);
        });

        startBtn.setOnAction(Action -> {
            
            sn.sol = "";
            
            int cpt = 0;

            if(toggleThree.isSelected())
                cpt++;
            if(toggleFour.isSelected())
                cpt++;
            if(toggleFive.isSelected())
                cpt++;            
            
            String msg = sn.sol;
            switch(cpt){
                case 0: sn.MarkPropagationInference(nodeOne.getValue(), nodeTwo.getValue(), selectedRel.getValue().toString());  
                        break;
                case 1: sn.MarkPropagationInference(nodeOne.getValue(), nodeTwo.getValue(), selectedRel.getValue().toString());
                        sn.MarkPropagationInference(nodeOne.getValue(), nodeThree.getValue(), selectedRel.getValue().toString());
                        break;
                case 2: sn.MarkPropagationInference(nodeOne.getValue(), nodeTwo.getValue(), selectedRel.getValue().toString());
                        sn.MarkPropagationInference(nodeOne.getValue(), nodeThree.getValue(), selectedRel.getValue().toString());
                        sn.MarkPropagationInference(nodeOne.getValue(), nodeFour.getValue(), selectedRel.getValue().toString());
                        break;
                case 3: sn.MarkPropagationInference(nodeOne.getValue(), nodeTwo.getValue(), selectedRel.getValue().toString());
                        sn.MarkPropagationInference(nodeOne.getValue(), nodeThree.getValue(), selectedRel.getValue().toString());
                        sn.MarkPropagationInference(nodeOne.getValue(), nodeFour.getValue(), selectedRel.getValue().toString());
                        sn.MarkPropagationInference(nodeOne.getValue(), nodeFive.getValue(), selectedRel.getValue().toString());
                        break;
            }
            alert.show("RESULT", sn.sol, Alert.AlertType.INFORMATION, false);
            
        });
        
        
        
        
        
       
    }    
    
}
