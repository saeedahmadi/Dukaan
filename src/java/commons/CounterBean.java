/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import Boundary.WebUserFacade;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.faces.context.FacesContext;

/**
 *
 * @author Saeed Ahmadi
 */
@Singleton
public class CounterBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    WebUserFacade facade = new WebUserFacade();
    
    ///@Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void doWork(){
        //FacesContext context = FacesContext.getCurrentInstance();
        //context.getExternalContext().getSessionMap().put("count", 10);
        System.out.println(facade.count());
        System.out.println("HI");

    } 
    
}
