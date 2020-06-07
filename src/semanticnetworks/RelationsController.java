/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semanticnetworks;

import com.brunomnsilva.smartgraph.graph.Graph;
import com.brunomnsilva.smartgraph.graph.GraphEdgeList;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import inc.Node;
import inc.Relation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RelationsController implements Initializable {

    @FXML JFXComboBox<Node> child,parent;
    @FXML JFXButton addBtn,nextBtn;
    @FXML JFXTextField nameField;
    @FXML TableView<Relation> relationTable;
    @FXML TableColumn<Relation, String> childCol,parentCol,nameCol;
    @FXML TableColumn actionCol;
    
    private ObservableList<Node> nodeList = FXCollections.observableArrayList();
    private ObservableList<Relation> relationList = FXCollections.observableArrayList();
    
    public void setNodes(ObservableList nodes){
        
        this.nodeList = nodes;
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
                    delete.setStyle("-fx-background-color : red; -fx-text-fill: white; -fx-background-radius: 30;fx-background-insets: 0; -fx-cursor: hand;");                    
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

        Relation relation = new Relation(parent.getValue(), child.getValue());
        
        relationList.add(relation);        
        
    }
    
    public void initSemanticNetwork(ActionEvent Action){
        
            try {
                ((javafx.scene.Node)Action.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SN.fxml"));
                Pane root = (Pane)loader.load();
                SNController snControl = (SNController)loader.getController();
                snControl.initNetwork(nodeList, relationList);
                Scene scene = new Scene(root);
                stage.setScene(scene);              
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initTable();
        
        
        addBtn.setOnAction(Action->{
            addRelation();
        });
        
        nextBtn.setOnAction(Action->{
            
            Graph<Node, Integer> g = new GraphEdgeList<>();
            int cpt=0;
            
            nodeList.forEach((node) -> {
                g.insertVertex(node);
            });
            
            for(Relation relation : relationList){
                cpt++;
               g.insertEdge(relation.getParent(), relation.getChild(), cpt);
            }
            
            SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
            SmartGraphPanel<Node, Integer> graphView = new SmartGraphPanel<>(g, strategy);
            graphView.setVertexDoubleClickAction(graphVertex -> {
                System.out.println("Vertex contains element: " + graphVertex.getUnderlyingVertex().element());
            });

            graphView.setEdgeDoubleClickAction(graphEdge -> {
                System.out.println("Edge contains element: " + graphEdge.getUnderlyingEdge().element());
                //dynamically change the style, can also be done for a vertex
                graphEdge.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");
            });
            Scene scene = new Scene(graphView, 1024, 768);
            scene.getStylesheets().add(getClass().getResource("custom.css").toExternalForm());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Graph");
            stage.setScene(scene);
            stage.show();

            //IMPORTANT - Called after scene is displayed so we can have width and height values
            graphView.init();

            initSemanticNetwork(Action);
        });        
    }    
    
}
