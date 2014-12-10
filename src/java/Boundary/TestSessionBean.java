/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import interceptors.LoggingInterceptor;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

/**
 *
 * @author Saeed Ahmadi
 */
@Stateless
public class TestSessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   @Interceptors(LoggingInterceptor.class)

    public void print(){
    }
}
