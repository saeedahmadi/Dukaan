/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import entities.Customer;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Saeed Ahmadi
 */
public class AuthInterceptor {
    @AroundInvoke
    
    public Object checkRole(InvocationContext context) throws Exception{
         FacesContext fc = FacesContext.getCurrentInstance();

         Customer c = (Customer) fc.getExternalContext().getSessionMap().get("customer");
         if(c==null)
             return null;
        Object obj =  context.proceed();
        return obj;
    }

}
