/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restricted;

import commons.Role;
import entities.Customer;
import java.io.IOException;
import java.util.logging.LogRecord;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Saeed Ahmadi
 */
@WebFilter("/faces/shoppingCart/*")
public class AuthenticationFilter implements Filter {
  private FilterConfig config;
  
  public void doFilter(ServletRequest req, ServletResponse resp,
    FilterChain chain) throws IOException, ServletException {
     
    if (((HttpServletRequest) req).getSession().getAttribute(
       "customer") == null) {
        
        
        ((HttpServletResponse) resp).sendRedirect("/Dukaan/faces/login.xhtml");
        
    }else if(((HttpServletRequest) req).getSession().getAttribute(
       "customer") != null){
        Customer customer = (Customer) ((HttpServletRequest) req).getSession().getAttribute(
       "customer");
        customer.getUser().getRole();
        if(customer.getUser().getRole()!=Role.Admin){
        ((HttpServletResponse) resp).sendRedirect("/Dukaan/faces/login.xhtml");
        }
        else {
             chain.doFilter(req, resp);
           System.out.println("testing");

            }
    } 
    
    
  }

  public void init(FilterConfig config) throws ServletException {
    this.config = config;
  }

  public void destroy() {
    config = null;
  }

   
}