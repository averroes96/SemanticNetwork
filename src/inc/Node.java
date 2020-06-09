/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc;

import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author user
 */
public class Node {
    
    private SimpleStringProperty label;
    private SimpleBooleanProperty isMarked;
    private ObservableList<Node> childrens;
    private ObservableList<Node> parent;
    
    public Node(String label) {
        
        this.label = new SimpleStringProperty(label);
        this.isMarked = new SimpleBooleanProperty(false);
        childrens = FXCollections.observableArrayList();
        parent = FXCollections.observableArrayList();
    }

    public Node() {
        
        this.label = new SimpleStringProperty("");
        this.isMarked = new SimpleBooleanProperty(false);
        childrens = FXCollections.observableArrayList();
        
    }


    public String getLabel() {
        return label.getValue();
    }

    public void setLabel(String label) {
        this.label = new SimpleStringProperty(label);
    }

    public boolean getIsMarked() {
        return isMarked.getValue();
    }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = new SimpleBooleanProperty(isMarked);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.label);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        return Objects.equals(this.label, other.label);
    }

    @Override
    public String toString() {
        return label.getValue() ;
    }

    public ObservableList<Node> getChildrens() {
        return childrens;
    }

    public void setChildrens(ObservableList<Node> childrens) {
        this.childrens = childrens;
    }

    public ObservableList<Node> getParent() {
        return parent;
    }

    public void setParent(ObservableList<Node> parent) {
        this.parent = parent;
    }
    
    public boolean hasChildren(){
        return childrens.size() > 0;
    }
    
    public boolean hasParent(){
        return parent.size() > 0;
    }
    
    public Node getParentByName(String name){
        
        for(Node node : parent){
            if(node.getLabel().equals(name))
                return node;
        }
        return null;
    }
    
    public boolean parentExist(String name){
        return getParentByName(name) != null;
    }

    /*public ObservableList<Node> parrentChilds(){
        
        ObservableList<Node> noChilds = FXCollections.observableArrayList();
        
        parent.stream().filter((node) -> (!node.hasChildren())).forEachOrdered((node) -> {
            noChilds.add(node);
        });     
        
    }*/
    
    public boolean hasNoParent(){
        return this.getParent().isEmpty();
    }
    
}
