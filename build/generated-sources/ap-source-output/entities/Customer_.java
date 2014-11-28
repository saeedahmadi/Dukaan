package entities;

import entities.Account;
import entities.Address;
import entities.Affiliate;
import entities.Order;
import entities.ShoppingCart;
import entities.WebUser;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-27T11:10:32")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, Address> address;
    public static volatile SingularAttribute<Customer, String> phone;
    public static volatile SingularAttribute<Customer, Date> dob;
    public static volatile SingularAttribute<Customer, ShoppingCart> shoppingCart;
    public static volatile SingularAttribute<Customer, Affiliate> affilate;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile ListAttribute<Customer, Order> orders;
    public static volatile SingularAttribute<Customer, Long> id;
    public static volatile SingularAttribute<Customer, WebUser> user;
    public static volatile ListAttribute<Customer, Account> account;
    public static volatile SingularAttribute<Customer, String> email;

}