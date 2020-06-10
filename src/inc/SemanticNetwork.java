/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc;

import java.util.ArrayList;
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
        sol = "" ;
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
                node.getParent().add(relation.getParent());
            });
        });        
        
    }
    
    public ObservableList<Node> inferChildren(Node input, Node target, String relName){
        
        ObservableList<Node> results = FXCollections.observableArrayList();
        ObservableList<Node> temp = FXCollections.observableArrayList();
        
        if(input.parentExist(target.getLabel())&& relExist(target, input, relName)){
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
    
    public void MarkPropagationInference(Node one, Node two, String relName){
              

        ObservableList<Node> solution = FXCollections.observableArrayList();
        
        
        sol += "\n-------------------- Infering started --------------------";
        sol += "\n\n> M1(0) = " + one.getLabel();
        sol += "\n> M2(0) = " + two.getLabel();
        
        
        if(one.hasChildren()){
            
            one.getChildrens().stream().filter((node) -> (inferChildren(node, two, relName) != null)).forEachOrdered((node) -> {
                solution.addAll(inferChildren(node, two, relName));
            });

                if(!solution.isEmpty()){
                    sol += "\n\n> Solution was found:\n" ;
                    int  cpt2=0;
                    for(Node nd : solution){
                        cpt2++;
                        sol += "\n\tSol num" + cpt2 + ": " + nd.getLabel();
                    }
                    sol += "\n\n> Total number of solutions = " + solution.size();
                    sol += "\n\n-------------------- Infering ended --------------------";
                }
                else
                    sol += "\n\n> No solution was found";
            

        }
        else
            sol += "\n\n >No solution was found for";        
        
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
    
    public ObservableList<Node> getRoots(){
        
        ObservableList<Node> temp = FXCollections.observableArrayList();
        
        this.nodes.stream().filter((node) -> (node.hasNoParent())).forEachOrdered((node) -> {
            temp.add(node);
        });
        return temp;
    }
    
/*    public ArrayList<String> inferChildren(Node input){
        
        ArrayList<String> results = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        
        
        if(!input.hasChildren()){
            temp.add("#");
            return temp;
        }
        else
        {
            
            for(Node node : input.getChildrens()){
                
                results.add(node.getLabel() + " " + getRelation(input, node) + " " + input.getLabel());
                temp = inferChildren(node);
                if(temp != null){
                    results.addAll(temp);
                }
                
            }
        }
        
        return results;
            
    }    
    
    public void inheritanceInference(){
        
         ObservableList<Node> temp = getRoots();
         ArrayList<String> results = new ArrayList<>();
         
         if(temp != null){
             
             temp.forEach((node) -> {
                 results.addAll(inferChildren(node));
             });
             
         }
         
         
         results.forEach((str) -> {
             sol += str + "\n";
        });
        
        
    }

*/
    
    public String getRelation(Node parent, Node children){
        
        for(Relation relation : this.relations){
            if(relation.getParent().getLabel().equals(parent.getLabel())
                    && relation.getChild().getLabel().equals(children.getLabel()))
                return relation.getName();
        }
        
        return null;
    }
    
    public ObservableList<Relation> inheritChilds(Node input, Node root, String relName){
        
        ObservableList<Relation> results = FXCollections.observableArrayList();
        ObservableList<Relation> temp = FXCollections.observableArrayList();
       
        
        if(!input.hasChildren() ){
            if(!relExist(root, input, relName)){
                temp.add(new Relation(root, input, relName));
            }
            return temp;
        }
        else
        {
            if(!relExist(root, input, relName)){
                    results.add(new Relation(root, input, relName));
            }            
            for(Node node : input.getChildrens()){
                temp = inheritChilds(node, root, relName);
                
                if(temp != null){
                results.addAll(temp);
                }
                
                temp = inheritChilds(node, input, relName);
                if(temp != null){
                results.addAll(temp);
                }                
                
            }
        }
        
        return results;
        
    }
    
    public void Inheritance(){
        
        ObservableList<Node> temp = getRoots();
        
        temp.forEach((one) -> {
            one.getChildrens().forEach((two) -> {
                inheritChilds(two, one, getRelation(one, two)).stream().filter((rel) -> (!this.relations.contains(rel))).forEachOrdered((rel) -> {
                    this.relations.add(rel);
                });
            });
        });
        
        sol += relations.size() + " relation(s) were found \n\n";
        this.relations.forEach((relation) -> {
            sol += relation.getChild() + " " + relation.getName() +" " + relation.getParent() + "\n";
        });
        
    }
    
    public String getNodeProperties(Node node){
        
        String str = "";
        
        str = this.relations.stream().filter((rel) -> (rel.getChild().equals(node))).map((rel) -> "> " + rel.getChild() + " " + rel.getName() + " " + rel.getParent() + "\n").reduce(str, String::concat);
        
        return str;
    }
    
}
