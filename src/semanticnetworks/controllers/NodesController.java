/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import inc.Init;
import static inc.Init.FXMLS_PATH;
import static inc.Init.IO_ERROR;
import inc.Node;
import inc.SpecialAlert;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author user
 */
public class NodesController implements Initializable,Init {
    
    @FXML JFXButton addBtn,nextBtn,returnBtn;
    @FXML JFXTextField nameField;
    @FXML TableView<Node> nodeTable;
    @FXML TableColumn<Node,String> nameCol;
    @FXML TableColumn<Node,Boolean> isMarkedCol;
    @FXML TableColumn actionCol;
    
    private ObservableList<Node> nodesList = FXCollections.observableArrayList();
    String choice;
    SpecialAlert alert = new SpecialAlert();
    
    public void setChoice(String choice){
        this.choice = choice;
    }
    
    public void setNodes(ObservableList<Node> nodes){
        this.nodesList = nodes;
        initTable();
        nextBtn.disableProperty().bind(Bindings.size(nodeTable.getItems()).lessThan(2));        
    }
    
    public void initTable(){
        
        nodeTable.setItems(nodesList);
        
        nameCol.setCellValueFactory(new PropertyValueFactory<>("label"));

        actionCol.setCellValueFactory(new PropertyValueFactory<>("actionCol"));        
                   
        Callback<TableColumn<Node, String>, TableCell<Node, String>> cellFactory
                =                 //
    (final TableColumn<Node, String> param) -> {
        final TableCell<Node, String> cell = new TableCell<Node, String>() {
            
            final Button delete = new Button("Delete");
            
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    delete.setOnAction(event -> {
                        Node Node = getTableView().getItems().get(getIndex());
                        deleteNode(Node);
                    });
                    delete.setStyle(DELETE_BTN_CSS);                    
                    setGraphic(delete);
                    setText(null);               
                    
                }
            }

            private void deleteNode(Node Node) {
                nodesList.remove(Node);
            }
        };
        return cell;
    };

    actionCol.setCellFactory(cellFactory);         
        
    }
    
    public void addNode(){
        
        if(nameExist()){
            alert.show(NAME_EXIST, NAME_EXIST_MSG, Alert.AlertType.ERROR, false);
        }
        else if(nameField.getText().trim().equals(""))
            alert.show(EMPTY_NAME, EMPTY_NAME_MSG, Alert.AlertType.ERROR, false);
        else{
            Node node = new Node(nameField.getText());
            nodesList.add(node);
        }
        
    }
    
    public boolean nameExist(){
        
        return this.nodesList.stream().anyMatch((node) -> (node.getLabel().equalsIgnoreCase(nameField.getText())));
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initTable();
        
        addBtn.setOnAction(Action -> {
            addNode();
        });
        
        nextBtn.setOnAction(Action -> {
            
            try {
                ((javafx.scene.Node)Action.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                stage.getIcons().add(new Image(NodesController.class.getResourceAsStream(APP_ICON)));
                FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLS_PATH + "Relations.fxml"));
                Pane root = (Pane)loader.load();
                RelationsController rControl = (RelationsController)loader.getController();
                rControl.setNodes(nodesList, choice);
                Scene scene = new Scene(root);
                stage.setScene(scene);              
                stage.show();
            } catch (IOException ex) {
                alert.show(IO_ERROR, ex.getMessage(), Alert.AlertType.ERROR, true);
            }
        });
        
        returnBtn.setOnAction(Action -> {
            ((javafx.scene.Node)Action.getSource()).getScene().getWindow().hide();
            try {
                Stage stage = new Stage();
                stage.getIcons().add(new Image(NodesController.class.getResourceAsStream(APP_ICON)));
                Parent root = FXMLLoader.load(getClass().getResource(FXMLS_PATH + "Choice.fxml"));
                
                Scene scene = new Scene(root);
                
                stage.setScene(scene);            
                stage.show();
            } catch (IOException ex) {
                alert.show(IO_ERROR, ex.getMessage(), Alert.AlertType.ERROR, true);
            }
        });
        
        nextBtn.disableProperty().bind(Bindings.size(nodeTable.getItems()).lessThan(2));
        
    }    
    
}
