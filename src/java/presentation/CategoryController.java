package presentation;

import Boundary.AffiliateFacade;
import entities.Category;
import presentation.util.JsfUtil;
import presentation.util.PaginationHelper;
import Boundary.CategoryFacade;
import entities.Affiliate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
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

@ManagedBean(name = "categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    private Category current;
    private DataModel items = null;
    @EJB
    private Boundary.CategoryFacade categoryFacade;
    
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CategoryController() {
        
    }

    public Category getSelected() {
        if (current == null) {
            current = new Category();
            recreateModel();
            getItems().setRowIndex(0);
            current = (Category) getItems().getRowData();
            selectedItemIndex = -1;
            //current=defaultCategory();
            
        }
        return current;
    }

    private CategoryFacade getFacade() {
        return categoryFacade;
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
                    return new ListDataModel(getFacade().findAll());
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }
    public String prepareCat() {
        recreateModel();
        return "List";
    }
    public String prepareView() {
        current = (Category) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    public String prepareView1() {
        current = (Category) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        System.out.println(selectedItemIndex);
        
        return null;
    }
    Category testCategory;
    
    public String selectCategory(Object id){
       
        testCategory = getFacade().find(id);
        //return testCategory;
        return "/user/category/ViewCategory";
    }
    
    @EJB
    AffiliateFacade affiliateFacade;
    public List<Category> getCategories(){
       addPoints();
       return  getFacade().findAll();
         
    }
    private void addPoints(){
        boolean p = FacesContext.getCurrentInstance().
            getExternalContext().getRequestParameterMap().containsKey("tid");
        
        if(p){ 
            if(!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("tid")){
                Map<String, String> params =FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
                String tid = params.get("tid");
                
                    
                    Affiliate temp = affiliateFacade.find(tid);
                    if(temp!=null){
                    temp.setPoints(temp.getPoints()+1);
                    affiliateFacade.edit(temp);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tid", tid);
                    }
                
            }
        }
        
    }
    public String prepareCreate() {
        current = new Category();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoryCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }


    public String prepareEdit() {
        current = (Category) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoryUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Category) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CategoryDeleted"));
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
        return JsfUtil.getSelectItems(categoryFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(categoryFacade.findAll(), true);
    }

    @FacesConverter(forClass = Category.class)
    public static class CategoryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategoryController controller = (CategoryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categoryController");
            return controller.categoryFacade.find(getKey(value));
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
            if (object instanceof Category) {
                Category o = (Category) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Category.class.getName());
            }
        }

    }

    public Category getCurrent() {
        return current;
    }

    public void setCurrent(Category current) {
        this.current = current;
    }

    public Category getTestCategory() {
        if(testCategory==null){
            selectCategory(new Long(1));
        }
        return testCategory;
    }

    public void setTestCategory(Category testCategory) {
        this.testCategory = testCategory;
    }
    

}
