/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import entities.Affiliate;
import java.util.UUID;

import javax.persistence.PrePersist;

/**
 *
 * @author Saeed Ahmadi
 */

public class TrackingIdGenerator {
    
    //String URL = "http://localhost:8080/Dukaan/faces/home.xhtml?tid=";
    @PrePersist
    public void generateId(Affiliate affiliate){
        String url = affiliate.getWebsite();
        UUID uniqueId = UUID.randomUUID();
        //URL+=uniqueId.toString();
        affiliate.setTrackingid(uniqueId.toString());
    }
}
