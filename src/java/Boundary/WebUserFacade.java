/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import entities.WebUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Saeed Ahmadi
 */
@Stateless
public class WebUserFacade extends AbstractFacade<WebUser> {
    @PersistenceContext(unitName = "DukaanPU")
    private EntityManager em;
    
   
    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    public WebUserFacade() {
        super(WebUser.class);
    }
    
    public WebUser find(String username, String password){
        Query q = em.createQuery("Select u from WebUser u where u.username = :username and u.password = :password",WebUser.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        WebUser user = (WebUser) q.getSingleResult();
        em.refresh(user);
        return user;
    }
    
}
