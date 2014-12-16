/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import entities.Affiliate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Saeed Ahmadi
 */
@Stateless
public class AffiliateFacade extends AbstractFacade<Affiliate> {
    @PersistenceContext(unitName = "DukaanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AffiliateFacade() {
        super(Affiliate.class);
    }
    
    public Affiliate find(String tid){
    
        Query q = em.createQuery("Select a from Affiliate a where a.trackingid = :trackingid",Affiliate.class);
        q.setParameter("trackingid", tid);
        List<Affiliate> affiliates = q.getResultList();
        if(!affiliates.isEmpty()){
            em.refresh(affiliates.get(0));
            return affiliates.get(0);
        }
        //WebUser user = (WebUser) q.getSingleResult();
        //em.refresh(user);
        return null;
    }
    
}
