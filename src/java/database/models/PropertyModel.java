/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.models;

import database.config.DBConfig;
import java.util.List;
import database.entities.*;
import java.util.Collections;
import java.util.Map;
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

    public static Property getPropertyById(String id) {
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        
        //create tq and use named query from accounts class
        TypedQuery<Property> tq = em.createNamedQuery("Property.findById", Property.class);
        int numId = Integer.parseInt(id);
        tq.setParameter("id", numId);
        Property prop = tq.getSingleResult();
        em.close();

        return prop;   
    }
    
    public static List<String> getAllCities(){
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        TypedQuery<String> tq = em.createNamedQuery("Property.getDistinctCities", String.class);
        List<String> res = tq.getResultList();
        em.close();
        return res;
    }
    
    public static List<Property> getSearchProperties(String city, Double min, Double max){
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        TypedQuery<Property> tq = em.createNamedQuery("Property.getByPriceAndCity", Property.class);
        tq.setParameter("city", city);
        tq.setParameter("min", min);
        tq.setParameter("max", max);
        List<Property> res = tq.getResultList();
        em.close();
        return res;
    }
	
	public static List<String> getBERs(){
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        TypedQuery<String> tq = em.createNamedQuery("Property.getDistinctBERs", String.class);
        List<String> res = tq.getResultList();
        em.close();
		Collections.sort(res);
        return res;		
	}

	public static List<Style> getStyles(){
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        TypedQuery<Style> tq = em.createNamedQuery("Style.findAll", Style.class);
        List<Style> res = tq.getResultList();
        em.close();
        return res;		
	}
	
	public static List<PropertyType> getPTypes(){
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        TypedQuery<PropertyType> tq = em.createNamedQuery("PropertyType.findAll", PropertyType.class);
        List<PropertyType> res = tq.getResultList();
        em.close();
        return res;			
	}	
	
	public static List<Garage> getGarages(){
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        TypedQuery<Garage> tq = em.createNamedQuery("Garage.findAll", Garage.class);
        List<Garage> res = tq.getResultList();
        em.close();
        return res;			
	}
}
