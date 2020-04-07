/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.*;

import test.Velo;
import Utils.*;

import entities.validrendezvous;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.Validrendezvous_service;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class ValidrendezvousController implements Initializable {
    
  @FXML
    private Button btn_back;

    @FXML
    private TableView<validrendezvous> tableValidRendezvous;
    @FXML
    private TableColumn<validrendezvous, String> col_message;
    @FXML
    private TableColumn<validrendezvous, String> col_etat;
    @FXML
    private TableColumn<validrendezvous, Date> col_date;
    @FXML
    private TableColumn<validrendezvous, String> col_prix;
    @FXML
    private TableColumn<validrendezvous, String> col_promo;
     private TableColumn<validrendezvous, String> action;

     ObservableList<validrendezvous> oblist ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Validrendezvous_service cs = new Validrendezvous_service ();
          LocalDateTime myDateObj = LocalDateTime.now();

  

 
      
        oblist = cs.getAll();
       
        
        col_message.setCellValueFactory(new PropertyValueFactory<>("message"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateheure"));
         col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          col_promo.setCellValueFactory(new PropertyValueFactory<>("promo"));
           action= new TableColumn("DELETE");
     
        javafx.util.Callback<TableColumn<validrendezvous, String>, TableCell<validrendezvous, String>> cellFactory
                = 
                new Callback<TableColumn<validrendezvous, String>, TableCell<validrendezvous, String>>() {
                    @Override
                    public TableCell call(final TableColumn<validrendezvous, String> param) {
                        final TableCell<validrendezvous, String> cell = new TableCell<validrendezvous, String>() {

                            final Button btn = new Button("supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    
                                    btn.setOnAction(event-> {
                                        validrendezvous cn = getTableView().getItems().get(getIndex());
                                        System.out.println(cn.getEmailR()
                                                );
                                        Validrendezvous_service cs = new Validrendezvous_service();
                                        cs.delete(cn);

                                        Parent root;
                                        try {
                                            
                                            root= FXMLLoader.load(getClass().getResource("/GUI/Validrendezvous.fxml"));
                                             
        
        
                                            btn.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ValidrendezvousController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                                    
                                    
                                   
          
                                    
                                    
                                    
                            tableValidRendezvous.setEditable(true);         
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
         btn_back.setOnAction(event -> {
                Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("/GUI/Client.fxml"));
                                            btn_back.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                 action.setCellFactory(cellFactory);
//      
        tableValidRendezvous.getColumns().add(action);
         tableValidRendezvous.setItems(oblist);
    }    
  
    
}
