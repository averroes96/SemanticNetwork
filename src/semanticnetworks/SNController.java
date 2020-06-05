/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks;

import inc.Node;
import inc.Relation;
import inc.SemanticNetwork;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SNController implements Initializable {

    SemanticNetwork sn = new SemanticNetwork();
    
    public void initNetwork(ObservableList<Node> nodes, ObservableList<Relation> relation){
        
        sn.setNodes(nodes);
        sn.setRelations(relation);
        
        sn.setNodeChildren();
        sn.setNodeParents();
        
        sn.inferInstances();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Node> nodes = FXCollections.observableArrayList();
        ObservableList<Relation> relations = FXCollections.observableArrayList();
        
        nodes.add(new Node("mrc",true));
        nodes.add(new Node("ml",false));
        nodes.add(new Node("lnc",false));
        nodes.add(new Node("lm",false));
        nodes.add(new Node("ld",false));
        nodes.add(new Node("ldd",false));
        nodes.add(new Node("syst",false));
        nodes.add(new Node("sysd",false));
        nodes.add(new Node("sys5",false));
        nodes.add(new Node("axe-7",true));
        
        relations.add(new Relation(nodes.get(0),nodes.get(1)));
        relations.add(new Relation(nodes.get(1),nodes.get(2)));
        relations.add(new Relation(nodes.get(2),nodes.get(3)));
        relations.add(new Relation(nodes.get(2),nodes.get(4)));
        relations.add(new Relation(nodes.get(2),nodes.get(5)));
        relations.add(new Relation(nodes.get(3),nodes.get(6)));
        relations.add(new Relation(nodes.get(3),nodes.get(7)));
        relations.add(new Relation(nodes.get(3),nodes.get(8)));
        relations.add(new Relation(nodes.get(9),nodes.get(6)));
        relations.add(new Relation(nodes.get(9),nodes.get(8)));
        
        initNetwork(nodes, relations);
       
    }    
    
}
