/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import entities.WebUser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        String encryptedPassword = encrypt(password);

        Query q = em.createQuery("Select u from WebUser u where u.username = :username and u.password = :password",WebUser.class);
        q.setParameter("username", username);
        q.setParameter("password", encryptedPassword);
        List<WebUser> users = q.getResultList();
        if(!users.isEmpty()){
            em.refresh(users.get(0));
            return users.get(0);
        }
        //WebUser user = (WebUser) q.getSingleResult();
        //em.refresh(user);
        return null;
    }
    
    private String encrypt(String password){
        String passwordToHash = password;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
        
        return generatedPassword;

    }

    
}
