///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package utils;
//
///**

import entities.user;
import service.userService;

// *
// * @author USER
// */
public class Session {
     private static final userService fs = new userService();
    
    private static Session instance = null;
    private static user user = null;
    
    private Session(user userConnected) {
        super();
        Session.user = userConnected;
        
    }
    
    private Session(user userConnected, Boolean b) {
        super();
        Session.user = userConnected;
    }
    
    public final static Session getInstance(user userConnected) {

        if (Session.instance == null) {
            Session.instance = new Session(userConnected);
        }
        return Session.instance;
    }
    
    public final static Session getFirstInstance(user userConnected) {

        if (Session.instance == null) {

            Session.instance = new Session(userConnected,false);
      
        }
        return Session.instance;
    }

    public static userService getFs() {
        return fs;
    }

    public static Session getInstance() {
        return instance;
    }

    public static user getUser() {
        return user;
    }

   

    public static void setUser(user user) {
        Session.user = user;
    }

  
    

}