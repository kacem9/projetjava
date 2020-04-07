
package Service;
import Utils.ConnexionBD;
import entities.fos_user;
import entities.rendezvous;
import entities.validrendezvous;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
/**
 *
 * @author HP
 */
public class ValidateRendezvous_service {

         Connection c= ConnexionBD.getInstance().getCnx();
    private Connection connection;
    private Statement st ;
    private PreparedStatement pst ;
    private ResultSet rs ;
     int res1;
 int res ;
    public ValidateRendezvous_service() {
        connection=ConnexionBD.getInstance().getCnx();
    }
    
    
    
    
       public ObservableList<rendezvous> getAll() {
        ObservableList<rendezvous>list = FXCollections.observableArrayList();
        String requete = "select * from rendezvous";
        try {
           PreparedStatement pt1 = c.prepareStatement("select Cin, nom, prenom, email, adresse, message ,typepanne ,numtel ,user from rendezvous ");
            ResultSet rs = pt1.executeQuery();
            while(rs.next()){
                list.add(new rendezvous(rs.getInt("Cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),rs.getString("message"),rs.getString("typepanne"),rs.getInt("numtel"),rs.getInt("user")));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list ;
    }
    
        
       public void afficherRendezvous(ObservableList<rendezvous> oblist) throws IOException{
        try {
           // oblist = FXCollections.observableArrayList();
            PreparedStatement pt1 = c.prepareStatement("select Cin, nom, prenom, email, adresse, message ,typepanne ,numtel ,user from rendezvous ");
            ResultSet rs = pt1.executeQuery();
            
            while (rs.next()) {
  
          oblist.add(new rendezvous(rs.getInt("Cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),rs.getString("message"),rs.getString("typepanne"),rs.getInt("numtel"),rs.getInt("user")));
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(ValidateRendezvous_service.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
       
       
       
       
       
       
               public void insertPStatement(validrendezvous s) throws ParseException {
        try {
         
            System.out.println(s.toString());
            
            String requete =
                    "INSERT INTO validrendezvous( emailR, dateheure,prix,promo,etat,message,user ) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement st =connection.prepareStatement(requete);
            st.setString(1,(s.getEmailR()));
             st.setString(2,(s.getDateheure()));
            st.setString(3,(s.getPrix()));
            st.setString(4,(s.getPromo()));
            st.setString(5,(s.getEtat()));
             st.setString(6,(s.getMessage()));
          
           st.setInt(7,(s.getUser()));
            
           
           
           
            st.executeUpdate();
            System.out.println("validate");
        }
        catch (SQLException ex){
                System.out.println(ex.getMessage());
        }    
    }
}
