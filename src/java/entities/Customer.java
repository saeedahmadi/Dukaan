/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import commons.PasswordEncrypt;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Saeed Ahmadi
 */
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private WebUser user;
    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private List<Address> address;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private List<Account> account;
    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private List<Order> orders;
    @OneToOne(cascade = CascadeType.ALL)
    private Affiliate affilate;
    
    
    private String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    private String email;
    private String phone;

    public Customer() {
        this.user=new WebUser();
        this.address=new ArrayList<Address>();
        this.orders=new ArrayList<Order>();
        this.shoppingCart = new ShoppingCart();
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public WebUser getUser() {
        return user;
    }

    public void setUser(WebUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "entities.Customer[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }



    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Affiliate getAffilate() {
        return affilate;
    }

    public void setAffilate(Affiliate affilate) {
        this.affilate = affilate;
    }
    
}
