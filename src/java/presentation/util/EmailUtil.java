/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import restwebservices.Email;

/**
 *
 * @author Saeed Ahmadi
 */
public class EmailUtil {
    
    public static String getJSON(String fromEmail, String msg, String subject, String toEmail ){
         Email email = new Email();
         email.setFrom(fromEmail);
         email.setMsg(msg);
         email.setSubject(subject);
         email.setTo(toEmail);
         GsonBuilder builder = new GsonBuilder();
         Gson gson = builder.create();
         System.out.println(gson.toJson(email));
         return gson.toJson(email);
    }
    
}
