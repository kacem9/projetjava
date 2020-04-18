/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Velo;
import connection.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class ProductServices {
     Connection con =ConnectionDB.getinstance().getCnx();


    public ObservableList<Velo> findAll() {
        ObservableList<Velo> ls = FXCollections.observableArrayList();

        try {
            PreparedStatement pt = con.prepareStatement("select * from Velo");
            ResultSet         rs = pt.executeQuery();

            while (rs.next()) {
                Velo p;
                p = new Velo(rs.getInt(1),rs.getString(8), rs.getInt(11), rs.getInt(12));

                ls.add(p);
            }
            

            return ls;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

            return ls;
        }
    }

    public Velo getP(int id) {
        Velo p = null;

        try {
            PreparedStatement pt = con.prepareStatement("select * from Velo where id=?");

            pt.setInt(1, id);

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
               p= new Velo(rs.getInt(1), rs.getString(8), rs.getInt(11), rs.getInt(12));

            }

            return p;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return p;
    }
}

