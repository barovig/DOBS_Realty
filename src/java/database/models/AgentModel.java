/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.models;

import database.config.DBConfig;
import database.entities.Agent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author K00191419
 */
public class AgentModel {
    public static Agent getAgentByName(String agentName){
        EntityManager em =  DBConfig.getEmf().createEntityManager();
        
        //create tq and use named query from accounts class
        TypedQuery<Agent> tq = em.createNamedQuery("Agent.findByUsername", Agent.class);
		tq.setParameter("username", agentName);
        Agent agent = tq.getSingleResult();
        em.close();

        return agent;
        
    }    

	public static void updateAgent(Agent agent) {
		EntityManager em = DBConfig.getEmf().createEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try{
			et.begin();
			em.merge(agent);
			et.commit();
			// refresh from cache
			em.getEntityManagerFactory().getCache().evictAll();
		}
		catch(Exception e){
			et.rollback();
		}
		finally{
			em.close();
		}
	}
}
