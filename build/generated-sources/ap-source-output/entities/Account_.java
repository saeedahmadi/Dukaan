package entities;

import entities.Address;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-12-02T22:00:55")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Date> expiryDate;
    public static volatile SingularAttribute<Account, Address> address;
    public static volatile SingularAttribute<Account, Long> id;
    public static volatile SingularAttribute<Account, String> accountNumber;

}