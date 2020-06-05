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

    public SemanticNetwork() {
        this.nodes = FXCollections.observableArrayList();
        this.relations = FXCollections.observableArrayList();
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
    
    public ObservableList<Node> inferChildren(ObservableList<Node> input, Node target){
        
        
        for(Node node : input){
            
            System.out.println(node.getLabel());
            System.out.println(node.getParent());
            System.out.println("Target = " + target.getLabel());
            
            if(node.getParent().getLabel().equals(target.getLabel()))
                return node.getParent().getChildrens();
            if(node.HasChildren())
                return inferChildren(node.getChildrens(),target);
        }
        return null;
    }
    
    public void inferInstances(){
        
        Node node1 = getMarkedNodes().get(0);
        Node node2 = getMarkedNodes().get(1);
        
        
        if(node1.HasChildren()){
            ObservableList<Node> sol = inferChildren(node1.getChildrens(),node2);
            
            if(sol != null){
                sol.forEach((node) -> {
                    System.out.println(node);
                });
            }
            else
                System.out.println("No solution was found 1");
        }
        else
            System.out.println("No solution was found 2");
    }
    
    
    
}
