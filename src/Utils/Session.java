
package utils;
//
///**


import entities.user;
import service.userService;
import java.util.HashMap;
import java.util.Map;

// *
// * @author USER
// */
public class Session {
     private static final userService fs = new userService();
    
    private static Session instance = null;
    private static user user = null;


      private final Map<Integer, Integer> panier   = new HashMap<>();
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

    public Session() {
    }



    


    public static void setUser(user user) {
        Session.user = user;
    }

    public Map<Integer, Integer> getPanier() {
        return panier;
    } 
  public void addToPanier(Integer id, Integer quantity) {
        if (panier.containsKey(id)) {
            panier.replace(id, panier.get(id) + quantity);
        } else {
            panier.put(id, quantity);
        }
    }   

}