/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commons;

import entities.LineItem;
import entities.ShoppingCart;
import java.util.ArrayList;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;

/**
 *
 * @author Saeed Ahmadi
 */

public class ShoppingCartPriceCalculator {
    
    @PostLoad
    @PreUpdate
    @PostUpdate
    public void calculatePrice(ShoppingCart shoppingCart){
        double totalPrice = 0;       
        for(LineItem li : shoppingCart.getLineItems()){
            totalPrice+=li.getPrice();
        }
        shoppingCart.setTotalPrice(totalPrice);
    }
}
