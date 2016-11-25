/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author semargl
 */
public class DBConfig {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("DOBS_RealtyPU");

    public static EntityManagerFactory getEmf() {
        return emf;
    }
    
}
