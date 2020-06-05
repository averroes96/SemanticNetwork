/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class SemanticNetwork {
    
    private ObservableList<Node> nodes ;
    private ObservableList<Relation> relations ;

    public SemanticNetwork(ObservableList nodes) {
        this.nodes = nodes;
    }

    public ObservableList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ObservableList<Node> nodes) {
        this.nodes = nodes;
    }

    public ObservableList<Relation> getRelations() {
        return relations;
    }

    public void setRelations(ObservableList<Relation> relations) {
        this.relations = relations;
    }
    
    public ObservableList<Node> getMarkedNodes(){
        
        ObservableList<Node> markedNodes = FXCollections.observableArrayList();
        
        nodes.stream().filter((node) -> (node.getIsMarked())).forEachOrdered((node) -> {
            markedNodes.add(node);
        });
        
        return markedNodes;
    }
    
    public int getNumberOfMarkedNodes(){
        return getMarkedNodes().size();
    }
    
    public void setNodeChildren(){
        
        this.nodes.forEach((node) -> {
            this.relations.stream().filter((relation) -> (relation.getParent().equals(node))).forEachOrdered((relation) -> {
                node.getChildrens().add(relation.getChild());
            });
        });
    }
    
    public void setNodeParents(){
        
        this.nodes.forEach((node) -> {
            this.relations.stream().filter((relation) -> (relation.getChild().equals(node))).forEachOrdered((relation) -> {
                node.setParent(relation.getParent());
            });
        });        
        
    }
    
    public void inferInstances(){
        Node node1 = getMarkedNodes().get(0);
        

    }
    
    
    
}
