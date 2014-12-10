/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import entities.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Saeed Ahmadi
 */
@Stateless
public class AddressFacade extends AbstractFacade<Address> {
    @PersistenceContext(unitName = "DukaanPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }
    
}
