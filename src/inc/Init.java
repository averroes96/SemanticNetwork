/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inc;

/**
 *
 * @author user
 */
public interface Init {
    
    String FXMLS_PATH = "/semanticnetworks/fxmls/";
    
    String APP_ICON = "/img/app.png";
    
    String APP_GRAPH = "/img/graph.png";
    
    String DELETE_BTN_CSS = "-fx-background-color : #7a2048; -fx-text-fill: white; -fx-background-radius: 30;fx-background-insets: 0; -fx-cursor: hand;";
    
    // Messages
    
    String IO_ERROR = "IO ERROR !";
    
    String RESULT = "RESULT";
    
    String INHERITANCE = "Inherit";
    
    String MARK_PROP = "mark-prop";
    
    String NO_PROP_FOUND = "No properties were found !";
    
    String NAME_EXIST = "NAME EXIST !";
    
    String NAME_EXIST_MSG = "This name is already taken by another node";    
    
    String EMPTY_NAME = "EMPTY NAME !";
    
    String EMPTY_NAME_MSG = "Node name cannot be empty";    

    String EMPTY_NAME_MSG2 = "Relation name cannot be empty"; 
    
    String REL_EXIST = "RELATION EXIST !";
    
    String REL_EXIST_MSG = "This relation already exist";
    
    String EMPTY_FIELDS = "EMPTY FIELDS !";
    
    String EMPTY_FIELDS_MSG = "Please make sure all the required fields are selected";    
    
}
