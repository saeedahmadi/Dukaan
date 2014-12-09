package entities;

import entities.LineItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-02T22:00:55")
@StaticMetamodel(ShoppingCart.class)
public class ShoppingCart_ { 

    public static volatile ListAttribute<ShoppingCart, LineItem> lineItems;
    public static volatile SingularAttribute<ShoppingCart, Double> totalPrice;
    public static volatile SingularAttribute<ShoppingCart, Long> id;
    public static volatile SingularAttribute<ShoppingCart, Date> creationDate;

}