/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soapwebservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Saeed Ahmadi
 */
@WebService(serviceName = "CreditCardService")
@Stateless()
public class CreditCardService {

    /**
     * This is a sample web service operation
     */
   
    
    public boolean validateCreditCard(String creditCardNumber){
        int lastDigit = creditCardNumber.charAt(creditCardNumber.length()-1);
        if((lastDigit%2)==0){
            return true;
        }
        return false;
    }
}
