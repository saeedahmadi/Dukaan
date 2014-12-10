package entities;

import entities.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-27T11:10:32")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, String> description;
    public static volatile SingularAttribute<Category, Long> id;
    public static volatile SingularAttribute<Category, String> title;
    public static volatile ListAttribute<Category, Product> products;

}