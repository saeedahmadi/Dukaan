/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restwebservices;

import com.sun.mail.util.MailSSLSocketFactory;
import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Saeed Ahmadi
 */

@Path("email")
public class EmailRestFul {
    
    @GET
    @Produces({"application/xml", "application/json"})
    public Email print() {
        System.out.println("Testing Web Service");
        Email e = new Email();
        e.setTo("saeed_csd@yahoo.com");e.setFrom("mumyogastudio@gmail.com");e.setMsg("texting");e.setSubject("too");
        return  e;
    }
    
    @POST
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml","application/json"})
    public void sendEmail(Email email)
    throws Exception
  {
    String toEmails[] = email.getTo().split(",");
    System.out.println("TO:::FROM:::SUBJ:::BODY:::" +
            email.getTo() + "-" + email.getFrom() + "-" +
            email.getSubject() + "-" + email.getMsg());

    Session session = setSessionAuthentication();
    InternetAddress from = new InternetAddress(email.getFrom());
    InternetAddress to[] = new InternetAddress[toEmails.length];
    for (int c = 0; c < toEmails.length; c++)
    {
      to[c] = new InternetAddress(toEmails[c]);
    }
    MimeMessage message = new MimeMessage(session);
    message.setFrom(from);
    message.addRecipients(Message.RecipientType.TO, to);
    message.setSubject(email.getSubject());
    message.setText(email.getMsg());
    Transport.send(message);
    // msg="OK Msg Posted Successfully";
    //return "EMail Sent Successfully";
  }

  /**
   *
   * @return
   * @throws Exception
   */
  public Session setSessionAuthentication()
    throws Exception
  {
    final String username = "mumyogastudio@gmail.com";
    final String password = "Yoga123456";
    //Using SSL
    //Properties props = new Properties();
    //props.put("mail.smtp.host", "smtp.gmail.com");
    //props.put("mail.smtp.socketFactory.port", "465");
    //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    //props.put("mail.smtp.auth", "true");
    //props.put("mail.smtp.port", "465");
    // USING TLS
          Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.starttls.enable", "true");
           props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.port", "587");
    props.put("mail.debug", "true");
    MailSSLSocketFactory sf = null;
    try
    {
      sf = new MailSSLSocketFactory();
    }
    catch (GeneralSecurityException e1)
    {
      e1.printStackTrace();
    }
    sf.setTrustAllHosts(true);
    props.put("mail.smtp.ssl.socketFactory", sf);
    Session session = Session.getInstance(props, new javax.mail.Authenticator()
      {
        protected PasswordAuthentication getPasswordAuthentication()
        {
          return new PasswordAuthentication(username, password);
        }
      });
    return session;
  }
}
