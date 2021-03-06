package presentation;

import Boundary.CustomerFacade;
import entities.Product;
import presentation.util.JsfUtil;
import presentation.util.PaginationHelper;
import Boundary.ProductFacade;
import Boundary.ShoppingCartFacade;
import entities.Customer;
import entities.LineItem;
import entities.ShoppingCart;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "productController")
@SessionScoped
public class ProductController implements Serializable {

    private Product current;
    private DataModel items = null;
    @EJB
    private Boundary.ProductFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProductController() {
    }

    public Product getSelected() {
        if (current == null) {
            current = new Product();
            selectedItemIndex = -1;
        }
        return current;
    }
    Product currentProduct;

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }
    public String find(String id){
       
       currentProduct = ejbFacade.find(Long.parseLong(id));
       return "/user/category/ViewProduct";
    }

    private ProductFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Product) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Product();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Product) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Product) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProductDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Product.class)
    public static class ProductControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProductController controller = (ProductController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "productController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Product) {
                Product o = (Product) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Product.class.getName());
            }
        }

    }
    @EJB
    ShoppingCartFacade scFacade;
    LineItem lineItem = new LineItem();
    
    @EJB
    CustomerFacade customerFacade;
    public String addToCart(){
        FacesContext context = FacesContext.getCurrentInstance();
        ShoppingCart sc;
        if(!context.getExternalContext().getSessionMap().containsKey("shoppingCart")){
            sc = new ShoppingCart();
            sc =  (ShoppingCart) context.getExternalContext().getSessionMap().put("shoppingCart", sc);
        }
        
        sc =(ShoppingCart) context.getExternalContext().getSessionMap().get("shoppingCart");
        
        LineItem lineItem1 = new LineItem();
        lineItem1.setProduct(this.currentProduct);
        lineItem1.setQuantity(lineItem.getQuantity());
        sc.getLineItems().add(lineItem1);
        //sc = scFacade.edit(sc);
        Customer customer = (Customer) context.getExternalContext().getSessionMap().get("customer");
        if(customer!=null){
            sc = scFacade.edit(sc);
            customer.setShoppingCart(sc);
         }

        context.getExternalContext().getSessionMap().put("shoppingCart", sc);

    return "/home";
    }
    
    public String checkOut(){
        FacesContext context = FacesContext.getCurrentInstance();

        if(!context.getExternalContext().getSessionMap().containsKey("customer")){
            return "/user/customer/CreateCustomer";
        }
        
        return "/user/order/OrderConfirm";
    }

    public LineItem getLineItem() {
        return lineItem;
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
    }
    
}
