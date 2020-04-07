/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import test.Velo;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author HP
 */
public class FxmlLoader {
    private Pane view;
    
    
    
    
    public Pane getPage(String fileName)
    {
    
    try {
    URL fileUrl = Velo.class.getResource("/GUI/"+ fileName +".fxml");
    if(fileUrl == null)
    {
       
            throw new java.io.FileNotFoundException("fxml file can't be found");
        } 
    view = new FXMLLoader().load(fileUrl);
    }
    
    catch (Exception e) {
            System.out.println("No page"+fileName+"please check fxmlLoader");
            
        }
    return view;
    }
    
    
    
}
    
    
    
    
    
    
    

    
 
