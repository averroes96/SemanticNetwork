/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import inc.Node;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author user
 */
public class MainController implements Initializable {
    
    @FXML JFXButton addBtn,nextBtn;
    @FXML JFXTextField nameField;
    @FXML TableView<Node> nodeTable;
    @FXML TableColumn<Node,String> nameCol;
    @FXML TableColumn<Node,Boolean> isMarkedCol;
    @FXML TableColumn actionCol;
    
    private final ObservableList nodesList = FXCollections.observableArrayList();
    
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
                    delete.setStyle("-fx-background-color : red; -fx-text-fill: white; -fx-background-radius: 30;fx-background-insets: 0; -fx-cursor: hand;");                    
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
        
        Node node = new Node(nameField.getText());
        
        nodesList.add(node);
        
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/semanticnetworks/fxmls/Relations.fxml"));
                Pane root = (Pane)loader.load();
                RelationsController rControl = (RelationsController)loader.getController();
                rControl.setNodes(nodesList);
                Scene scene = new Scene(root);
                stage.setScene(scene);              
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
}
