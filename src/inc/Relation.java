/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc;

import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class Relation {
    
    private Node parent;
    private Node child;
    private SimpleStringProperty name;
    private SimpleStringProperty parentName;
    private SimpleStringProperty childName;

    public Relation() {
        this.parent = new Node();
        this.child = new Node();
        this.name = new SimpleStringProperty("");
        this.parentName = new SimpleStringProperty("");
        this.childName = new SimpleStringProperty("");        
    }

    public Relation(Node parent, Node child, String name) {
        this.parent = parent;
        this.child = child;
        this.name = new SimpleStringProperty(name);
        this.parentName = new SimpleStringProperty(parent.getLabel());
        this.childName = new SimpleStringProperty(child.getLabel());
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public String getParentName() {
        return parentName.getValue();
    }

    public void setParentName(String parentName) {
        this.parentName = new SimpleStringProperty(parentName);
    }

    public String getChildName() {
        return childName.getValue();
    }

    public void setChildName(String childName) {
        this.childName = new SimpleStringProperty(childName);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.parent);
        hash = 79 * hash + Objects.hashCode(this.child);
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
        final Relation other = (Relation) obj;
        if (!Objects.equals(this.parent, other.parent)) {
            return false;
        }
        return Objects.equals(this.child, other.child);
    }

    @Override
    public String toString() {
        return name.getValue();
    }
    
    
    
  
    
    
}
