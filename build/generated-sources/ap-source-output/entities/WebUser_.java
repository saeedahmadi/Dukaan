package entities;

import commons.Role;
import entities.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-16T22:54:49")
@StaticMetamodel(WebUser.class)
public class WebUser_ { 

    public static volatile SingularAttribute<WebUser, String> password;
    public static volatile SingularAttribute<WebUser, Role> role;
    public static volatile SingularAttribute<WebUser, Long> id;
    public static volatile SingularAttribute<WebUser, String> username;
    public static volatile SingularAttribute<WebUser, Customer> customer;

}