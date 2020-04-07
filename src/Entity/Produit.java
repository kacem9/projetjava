/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Produit {
    private int id;
    private int userid;
    private String model;
    private String type;
    private String price;
    private String quantity;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", userid=" + userid + ", model=" + model + ", type=" + type + ", price=" + price + ", quantity=" + quantity + ", photo=" + photo + '}';
    }

    public Produit() {
    }

    public Produit(String model, String type, String price, String quantity, String photo) {
        this.model = model;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.photo = photo;
    }
    
}
