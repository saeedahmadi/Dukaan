/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 *
 * @author Saeed Ahmadi
 */
@Stateless

public class EmailRSClient {
    @Asynchronous
    public  void sendEmail (String fromEmail, String msg, String subject, String toEmail){
        
        URL url;
        try {
            url = new URL("http://localhost:8080/Dukaan/service/email");
       
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            //String input = EmailUtil.getJSON(fromEmail, msg, subject, toEmail);
            String input = "{\"from\":\""+fromEmail+"\",\"msg\":\""+msg+"\",\"subject\":\""+subject+"\",\"to\":\""+toEmail+"\"}";
            System.out.println(input);
            OutputStream os = con.getOutputStream();
		os.write(input.getBytes());
		os.flush();
           if (con.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ con.getResponseCode());
		}
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;
            while((output=br.readLine())!=null){
                System.out.println(output);
            }
            con.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(EmailRSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
