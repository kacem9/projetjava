/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparateur;

import service.ValidateRendezvous_service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class StatistiqueController implements Initializable {

    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private Pane statpane;
    @FXML
    private PieChart statPie;
    @FXML
    private PieChart statPies;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            ValidateRendezvous_service crc =new  ValidateRendezvous_service();

        crc.countRendezvous(pieChartData, statPie);
        crc.countRendezvousValid(pieChartData, statPies);
       
        // TODO
    }    
    
}
