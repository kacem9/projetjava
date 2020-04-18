/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import connection.ConnectionDB;
import entities.user;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.userService;
import java.text.DateFormat;
import java.time.LocalDate;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
/**
 *
 * @author Asus
 */
public class home extends Application{
public static void main(String[] args)  {
     //ConnectionDB db = ConnectionDB.getinstance();
     //System.out.println(db);
    //Timestamp lastlogin= new Timestamp(System.currentTimeMillis());
    
    //user u2 = new user(31,"nejiba",  "n@gmail.com", " hjgjg", "df");
    
   // userService  us=new userService();
    
    
  // us.ajouterUser(u2);
   //   us.ajouterUser(u3);
   // us.afficherUser((ObservableList<user>) u1);
   //us.delete(u2);
   //us.update("emnanejiba",  "fhf", " email", "hjggj",31);
   //System.out.println(us.Afficheruser().toString());    
     launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader
                .load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
    } }
  

