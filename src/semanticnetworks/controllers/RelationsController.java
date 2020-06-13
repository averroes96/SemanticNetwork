/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks.controllers;

import com.brunomnsilva.smartgraph.graph.Digraph;
import com.brunomnsilva.smartgraph.graph.DigraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import inc.EdgeLabel;
import inc.Init;
import static inc.Init.APP_ICON;
import static inc.Init.FXMLS_PATH;
import static inc.Init.IO_ERROR;
import inc.Node;
import inc.Relation;
import inc.SpecialAlert;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RelationsController implements Initializable, Init {

    @FXML JFXComboBox<Node> child,parent;
    @FXML JFXButton addBtn,nextBtn,returnBtn;
    @FXML JFXTextField nameField;
    @FXML TableView<Relation> relationTable;
    @FXML TableColumn<Relation, String> childCol,parentCol,nameCol;
    @FXML TableColumn actionCol;
    
    private ObservableList<Node> nodeList = FXCollections.observableArrayList();
    private final ObservableList<Relation> relationList = FXCollections.observableArrayList();
    String choice;
    SpecialAlert alert = new SpecialAlert();
    
    public void setNodes(ObservableList nodes, String choice){
        
        this.nodeList = nodes;
        this.choice = choice;
        setNodeNames();
        
    }
    
    public void setNodeNames(){
        
        child.setItems(nodeList);
        parent.setItems(nodeList);
        
        child.getSelectionModel().selectFirst();
        parent.getSelectionModel().selectLast();
    }
    
    public void initTable(){
        
        relationTable.setItems(relationList);
        
        childCol.setCellValueFactory(new PropertyValueFactory<>("childName"));
        parentCol.setCellValueFactory(new PropertyValueFactory<>("parentName"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        actionCol.setCellValueFactory(new PropertyValueFactory<>("actionCol"));        
                   
        Callback<TableColumn<Relation, String>, TableCell<Relation, String>> cellFactory
                =                 //
    (final TableColumn<Relation, String> param) -> {
        final TableCell<Relation, String> cell = new TableCell<Relation, String>() {
            
            final Button delete = new Button("Delete");
            
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    delete.setOnAction(event -> {
                        Relation relation = getTableView().getItems().get(getIndex());
                        deleteRelation(relation);
                    });
                    delete.setStyle(DELETE_BTN_CSS);                    
                    setGraphic(delete);
                    setText(null);               
                    
                }
            }

            private void deleteRelation(Relation relation) {
                relationList.remove(relation);
            }
        };
        return cell;
    };

    actionCol.setCellFactory(cellFactory);            
    }
    
    public void addRelation(){
        
        if(relationExist()){
            alert.show(REL_EXIST, REL_EXIST_MSG, Alert.AlertType.ERROR, false);
        }
        else if(nameField.getText().trim().equals(""))
            alert.show(EMPTY_NAME, EMPTY_NAME_MSG2, Alert.AlertType.ERROR, false);
        else{
            Relation relation = new Relation(parent.getValue(), child.getValue(), nameField.getText());
            relationList.add(relation);        
        }
        
    }
    
    public void initSemanticNetwork(ActionEvent Action, String choice){
        
            try {
                ((javafx.scene.Node)Action.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                stage.getIcons().add(new Image(RelationsController.class.getResourceAsStream(APP_ICON)));
                AnchorPane root ;
                
                if(choice.equals(MARK_PROP)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLS_PATH + "MP.fxml"));
                    root = (AnchorPane)loader.load();
                    MPController snControl = (MPController)loader.getController();
                    snControl.initNetwork(nodeList, relationList);
                }
                else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLS_PATH + "Inherit.fxml"));
                    root = (AnchorPane)loader.load();
                    InheritController iControl = (InheritController)loader.getController();
                    iControl.initNetwork(nodeList, relationList);                    
                }
                
                Scene scene = new Scene(root);
                stage.setScene(scene);              
                stage.show();
            } catch (IOException ex) {
                alert.show(IO_ERROR, ex.getMessage(), Alert.AlertType.ERROR, true);
            }
        
    }
    
    public boolean relationExist(){
        
        return 
                this.relationList.
                    stream().
                        anyMatch((rel) -> 
                            ( 
                                rel.getName().equalsIgnoreCase(nameField.getText()) 
                                        && rel.getChild().equals(child.getValue()) 
                                            && rel.getParent().equals(parent.getValue())
                            )
                                );
    }     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initTable();
        
        
        addBtn.setOnAction(Action->{
            addRelation();
        });
        
        nextBtn.setOnAction(Action->{
            
            Digraph<Node, EdgeLabel> g = new DigraphEdgeList<>();
            int cpt=0;
            
            nodeList.forEach((node) -> {
                g.insertVertex(node);
            });
            
            for(Relation relation : relationList){
                cpt++;
               g.insertEdge(relation.getChild(), relation.getParent(), new EdgeLabel(relation.getParent(), relation.getChild(), relation.getName()));
            }
            
            SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
            SmartGraphPanel<Node, EdgeLabel> graphView = new SmartGraphPanel<>(g, strategy);
            
            graphView.setVertexDoubleClickAction(graphVertex -> {
                System.out.println("Vertex contains element: " + graphVertex.getUnderlyingVertex().element());
            });

            graphView.setEdgeDoubleClickAction(graphEdge -> {
                System.out.println("Edge contains element: " + graphEdge.getUnderlyingEdge().element());
                //dynamically change the style, can also be done for a vertex
                graphEdge.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            });
            
            Scene scene = new Scene(graphView, 800, 600);
            scene.getStylesheets().add(getClass().getResource("/semanticnetworks/custom.css").toExternalForm());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.getIcons().add(new Image(RelationsController.class.getResourceAsStream(APP_GRAPH)));
            stage.setTitle("Graph");
            stage.setScene(scene);
            stage.show();

            //IMPORTANT - Called after scene is displayed so we can have width and height values
            graphView.init();

            initSemanticNetwork(Action, choice);
        });

        nextBtn.disableProperty().bind(Bindings.size(relationTable.getItems()).isEqualTo(0));
        
        returnBtn.setOnAction(Action -> {
            ((javafx.scene.Node)Action.getSource()).getScene().getWindow().hide();
            try {
                ((javafx.scene.Node)Action.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                stage.getIcons().add(new Image(RelationsController.class.getResourceAsStream(APP_ICON)));
                FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLS_PATH + "Nodes.fxml"));
                AnchorPane root = (AnchorPane)loader.load();
                NodesController nControl = (NodesController)loader.getController();
                nControl.setNodes(nodeList);
                Scene scene = new Scene(root);
                stage.setScene(scene);              
                stage.show();
            } catch (IOException ex) {
                alert.show(IO_ERROR, ex.getMessage(), Alert.AlertType.ERROR, true);
            }      
            
        });
        
        
    }    
    
}
