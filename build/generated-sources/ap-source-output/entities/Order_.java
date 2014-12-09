package entities;

import entities.LineItem;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-02T22:00:55")
@StaticMetamodel(Order.class)
public class Order_ { 

    public static volatile ListAttribute<Order, LineItem> lineItems;
    public static volatile SingularAttribute<Order, Double> totalPrice;
    public static volatile SingularAttribute<Order, Long> id;
    public static volatile SingularAttribute<Order, Double> salesTax;
    public static volatile SingularAttribute<Order, Date> orderDate;

}