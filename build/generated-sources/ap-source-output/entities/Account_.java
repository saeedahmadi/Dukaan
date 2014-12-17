package entities;

import entities.Address;
import entities.Customer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-16T22:54:49")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Date> expiryDate;
    public static volatile SingularAttribute<Account, Address> address;
    public static volatile SingularAttribute<Account, Long> id;
    public static volatile SingularAttribute<Account, String> accountNumber;
    public static volatile SingularAttribute<Account, Customer> customer;

}