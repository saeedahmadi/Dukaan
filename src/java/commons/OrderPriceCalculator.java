/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import entities.LineItem;
import entities.Order;
import entities.ShoppingCart;
import java.util.ArrayList;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author Saeed Ahmadi
 */

public class OrderPriceCalculator {
    
    
    @PrePersist
    @PreUpdate
    public void calculatePrice(Order order){
        double totalPrice = 0;       
        for(LineItem li : order.getLineItems()){
            totalPrice+=li.getPrice();
        }
        totalPrice+=(totalPrice*order.getSalesTax())/100;
        order.setTotalPrice(totalPrice);
    }
}
