package entities;

import entities.Address;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-01T11:31:41")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, Date> expiryDate;
    public static volatile SingularAttribute<Account, Address> address;
    public static volatile SingularAttribute<Account, Long> id;
    public static volatile SingularAttribute<Account, String> accountNumber;

}