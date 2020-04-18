/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Velo;

import Entity.Produit;
import Services.ProduitService;
import entities.user;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXML_wishlistController implements Initializable {

    @FXML
    private AnchorPane panelle;
    @FXML
    private TextField search;
    @FXML
    private ScrollPane scroll;
public ObservableList<Produit>lst=null;
  public static int SelectedElement;
    ProduitService sb=new ProduitService();
    @FXML
    private MenuItem afficher;
    @FXML
    private MenuItem ajouterProd;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Session conUser=new Session();
         afficherGrid(sb.MywishList(conUser.getUser()));
    }    

    @FXML
    private void recherc(KeyEvent event) {
    }

  void afficherGrid(ObservableList<Produit>lst){
    Session conUser=new Session();
                       user Usr = conUser.getUser(); 
                       ProduitService K=new ProduitService();
        int elements=lst.size();

          GridPane grid = new GridPane();

    grid.setPadding(new javafx.geometry.Insets(10,10,10,10));

 grid.setHgap(10);
 grid.setVgap(10);
 int cols=4, colCnt = 0, rowCnt = 0;

    for (int i=0; i<elements; i++) {
FileInputStream input = null;
              try {
                  input = new FileInputStream("C:\\Users\\user\\Desktop\\PIFX4\\src\\image\\"+lst.get(i).getPhoto());
        
                  Image image = new Image(input);
                  ImageView imageView = new ImageView(image);
                  imageView.setFitHeight(100);
                  imageView.setFitWidth(100);
                  Label label1 = new Label(lst.get(i).getModel());
                  Label label2 = new Label(lst.get(i).getType());
                  Label label3 = new Label(lst.get(i).getQuantity());
                  Label label4 = new Label(lst.get(i).getPrice()+" $");
                 
             
                  VBox root = new VBox(label1,imageView,label2,label3,label4);
                 
                  grid.add(root,colCnt,rowCnt);
                  colCnt+=1;
                  if (colCnt>cols) {
                      rowCnt++;
                      colCnt=0;
                  }     } catch (FileNotFoundException ex) {
              } 
    }
    scroll.setContent(grid);

}

    
    public void changePage(String emplacement) throws IOException{
         AnchorPane root = FXMLLoader.load(getClass().getResource(emplacement));
        panelle.getChildren().removeAll();
        panelle.getChildren().setAll(root);
    }


    @FXML
    private void CloseAction(ActionEvent event) {
    }

    @FXML
    private void add(ActionEvent event) {
    }

    @FXML
    private void LocationAction(ActionEvent event) {
    }

    @FXML
    private void get(ActionEvent event) throws IOException {
        changePage("FXML_Product.fxml");
    }

    @FXML
    private void add(Event event) throws IOException {
        changePage("FXMLAddProduit.fxml");
    }

}
  
