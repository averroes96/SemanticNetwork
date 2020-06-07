/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import inc.Node;
import inc.Relation;
import inc.SemanticNetwork;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
    
    @FXML private JFXTextArea resultArea;
    @FXML private Label labelThree,labelFour,labelFive;
    @FXML private JFXComboBox nodeOne,nodeTwo,nodeThree,nodeFour,nodeFive;
    @FXML private JFXToggleButton toggleThree,toggleFour,toggleFive;
    @FXML private JFXButton startBtn;

    SemanticNetwork sn = new SemanticNetwork();
    
    public void initNetwork(ObservableList<Node> nodes, ObservableList<Relation> relation){
        
        sn.setNodes(nodes);
        sn.setRelations(relation);
        
        sn.setNodeChildren();
        sn.setNodeParents();
        sn.MarkPropagationInference();
        
        resultArea.setText(sn.sol);
        
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
       
    }    
    
}
