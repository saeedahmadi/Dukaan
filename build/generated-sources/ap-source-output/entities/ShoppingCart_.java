package entities;

import entities.LineItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-27T11:10:32")
@StaticMetamodel(ShoppingCart.class)
public class ShoppingCart_ { 

    public static volatile ListAttribute<ShoppingCart, LineItem> lineItems;
    public static volatile SingularAttribute<ShoppingCart, Double> totalPrice;
    public static volatile SingularAttribute<ShoppingCart, Long> id;
    public static volatile SingularAttribute<ShoppingCart, Date> creationDate;

}