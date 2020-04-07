/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author root
 */
public interface IEvent 
{
    public void AjouterEvent(Event e) throws SQLException;
    public void ModifierEvent(Event e) throws SQLException;
    public void SupprimerEvent(Event e) throws SQLException;
    public Event RechercherEvent(String Nom) throws SQLException;
    public List<Event> AfficherEvent() throws SQLException;
}
