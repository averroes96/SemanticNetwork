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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        
        nodes.add(new Node("reiter",false));
        nodes.add(new Node("mrc",true));
        nodes.add(new Node("axe-ia",false));
        nodes.add(new Node("ml",false));
        nodes.add(new Node("mg",false));
        nodes.add(new Node("lc",false));
        nodes.add(new Node("lnc",false));
        nodes.add(new Node("lo1",false));
        nodes.add(new Node("lo0",false));
        nodes.add(new Node("lm",false));
        nodes.add(new Node("ld",false));
        nodes.add(new Node("ldd",false));
        nodes.add(new Node("axe-a4",false));
        nodes.add(new Node("syst",false));
        nodes.add(new Node("sysd",false));
        nodes.add(new Node("sys5",false));
        nodes.add(new Node("axe-7",true));
        nodes.add(new Node("a>a",false));
        
        relations.add(new Relation(nodes.get(1),nodes.get(0)));
        relations.add(new Relation(nodes.get(2),nodes.get(1)));
        relations.add(new Relation(nodes.get(1),nodes.get(3)));
        relations.add(new Relation(nodes.get(1),nodes.get(4)));
        relations.add(new Relation(nodes.get(3),nodes.get(5)));
        relations.add(new Relation(nodes.get(3),nodes.get(6)));
        relations.add(new Relation(nodes.get(5),nodes.get(7)));
        relations.add(new Relation(nodes.get(5),nodes.get(8)));
        relations.add(new Relation(nodes.get(6),nodes.get(9)));
        relations.add(new Relation(nodes.get(6),nodes.get(10)));
        relations.add(new Relation(nodes.get(6),nodes.get(11)));
        relations.add(new Relation(nodes.get(7),nodes.get(12)));
        relations.add(new Relation(nodes.get(9),nodes.get(13)));
        relations.add(new Relation(nodes.get(9),nodes.get(14)));
        relations.add(new Relation(nodes.get(9),nodes.get(15)));
        relations.add(new Relation(nodes.get(16),nodes.get(13)));
        relations.add(new Relation(nodes.get(16),nodes.get(15)));
        relations.add(new Relation(nodes.get(16),nodes.get(17)));
        
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
       
    }    
    
}
