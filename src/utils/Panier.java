/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Entity.Produit;

/**
 *
 * @author user
 */
public class Panier {
    private Produit[] cart;
    private int itemCount;      // total number of items in the cart
    private double totalPrice;  // total price of items in the cart
    private int capacity;       // current cart capacity

    // -----------------------------------------------------------
    //  Creates an empty shopping cart with a capacity of 5 items.
    // -----------------------------------------------------------
    public Panier()
    {

      capacity = 10;
      cart = new Produit[capacity];
      itemCount = 0;
      totalPrice = 0.0;
    }
      public void addToCart(String model, String type, String price, String quantity, String photo)
    {    
       

        Produit temp = new Produit(model, type, price,quantity,photo);
        totalPrice += Integer.parseInt(price) * Integer.parseInt(quantity);
        itemCount += Integer.parseInt(quantity);
        cart[itemCount] = temp;
        if(itemCount==capacity)
        {
            increaseSize();
        }
    }
 private void increaseSize()
    {
        Produit[] temp = new Produit[capacity+3];
        for(int i=0; i < capacity; i++)
        {
            temp[i] = cart[i];
        }
        cart = temp; 
        temp = null;
        capacity = cart.length;
    }
}
