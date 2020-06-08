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
    public String sol = "";

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
    
    public ObservableList<Node> inferChildren(Node input, Node target, String relName){
        
        ObservableList<Node> results = FXCollections.observableArrayList();
        ObservableList<Node> temp = FXCollections.observableArrayList();
        System.out.println(input.getLabel());
        if(input.getParent().getLabel().equals(target.getLabel()) && relExist(target, input, relName)){
            temp.add(input);
            return temp;
        }
        else{
            for(Node node : input.getChildrens()){
                temp = inferChildren(node, target, relName);
                if(temp != null){
                    results.addAll(temp);
                }
            }
        }
        
        if(results != null){
            return results;
        }
        return null;
            
    }
    
    public void MarkPropagationInference(String relName){
        
        Node node1 = getMarkedNodes().get(0);
        Node node2 = getMarkedNodes().get(1);
        ObservableList<Node> solution = FXCollections.observableArrayList();
        
        
        sol += "-------------------- Infering started --------------------";
        sol += "\n\n> M1(0) = " + node1.getLabel();
        sol += "\n> M2(0) = " + node2.getLabel();
        
        
        if(node1.HasChildren()){
            
            node1.getChildrens().stream().filter((node) -> (inferChildren(node, node2, relName) != null)).forEachOrdered((node) -> {
                solution.addAll(inferChildren(node, node2, relName));
            });

                if(!solution.isEmpty()){
                    sol += "\n\n> Solution was found:\n" ;
                    int  cpt=0;
                    for(Node nd : solution){
                        cpt++;
                        sol += "\n\tSol num" + cpt + ": " + nd.getLabel();
                    }
                    sol += "\n\n> Total number of solutions = " + solution.size();
                    sol += "\n\n-------------------- Infering ended --------------------";
                }
                else
                    sol += "\n\n> No solution was found 1";
            

        }
        else
            sol += "\n\n >No solution was found 2";
        
        relations.forEach((relation) -> {
            System.out.println(relation.getChild() + " => " + relation.getParent());
        });
        
    }
    
    public Node getNodeByLabel(String label){
        
        for(Node node : this.getNodes()){
            if(node.getLabel().equals(label))
                return node;
        }
        return null;
    }
    
    public boolean relExist(Node nodeOne, Node nodeTwo, String relation){
        
        return this.getRelations().stream().anyMatch((rel) -> (rel.getParent().getLabel().equals(nodeOne.getLabel()) && rel.getChild().getLabel().equals(nodeTwo.getLabel()) && rel.getName().equals(relation)));
    }
    
    
    
}
