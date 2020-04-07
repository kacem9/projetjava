/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;


import Entity.Produit;
import Services.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;


/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLAddProduitController implements Initializable {

    @FXML
    private TextField model;
    @FXML
    private TextField type;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private Button image;
    @FXML
    private Button ajouter;
private String listview, Path;
    @FXML
    private Text selector;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button afficher;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        image.setOnAction((event) -> {
            FileChooser fc = new FileChooser();
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"),
               
                        new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
                        new FileChooser.ExtensionFilter("All files", "*.*")
                );
                File selectedFiles = fc.showOpenDialog(null);
                if (selectedFiles != null) {

                    listview = selectedFiles.getName();
                    Path = selectedFiles.getAbsolutePath();
                    System.out.println(listview);
                    selector.setText("... " + listview);
                    
                } else {
                    System.out.println("file is not valid");
                }
        });
        
   
    }    
 public void imageadd(){
        try {
        String PathTo= Paths.get("").toAbsolutePath().toString()+"\\src\\image\\"+listview;
        Files.copy(Paths.get(Path), Paths.get(PathTo), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            ex.getMessage();
        }}
    @FXML
    private void ajout(ActionEvent event) throws IOException {
        ProduitService ps=new ProduitService();
        Produit T=new Produit();    
        T.setModel(model.getText());
        T.setType(type.getText());
        T.setPhoto(listview);
        T.setPrice(price.getText());
        T.setQuantity(quantity.getText());
       
        ps.add(T);
         imageadd();
           AnchorPane root = FXMLLoader.load(getClass().getResource("FXML_Product.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(root);
    }

    @FXML
    private void print(ActionEvent event) throws IOException {
                   AnchorPane root = FXMLLoader.load(getClass().getResource("FXML_Product.fxml"));
        pane.getChildren().removeAll();
        pane.getChildren().setAll(root);
    }
    
    
}
