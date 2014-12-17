
package webserviceclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CreditCardService", targetNamespace = "http://creditcardwebservice.mycompany.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CreditCardService {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validateCreditCard", targetNamespace = "http://creditcardwebservice.mycompany.com/", className = "webserviceclient.ValidateCreditCard")
    @ResponseWrapper(localName = "validateCreditCardResponse", targetNamespace = "http://creditcardwebservice.mycompany.com/", className = "webserviceclient.ValidateCreditCardResponse")
    @Action(input = "http://creditcardwebservice.mycompany.com/CreditCardService/validateCreditCardRequest", output = "http://creditcardwebservice.mycompany.com/CreditCardService/validateCreditCardResponse")
    public boolean validateCreditCard(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}