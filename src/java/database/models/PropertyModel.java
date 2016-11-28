/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.models;

import database.config.DBConfig;
import java.util.List;
import database.entities.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
/**
 *
 * @author K00191419
 */
public class PropertyModel {
    public static List<Property> getAllProperties(){
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        
        //create tq and use named query from accounts class
        TypedQuery<Property> tq = em.createNamedQuery("Property.findAll", Property.class);

        List<Property> propList = tq.getResultList();
        em.close();

        return propList;
        
    }
}
