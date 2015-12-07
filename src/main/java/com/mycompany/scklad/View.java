package com.mycompany.scklad;

import com.mycompany.scklad.dao.CategoryDAO;
import com.mycompany.scklad.domain.Category;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Georgiy Popov
 */
public class View {
    
    CategoryDAO categoryDAO;
    
    public View(CategoryDAO categoryDAO){
        this.categoryDAO = categoryDAO;
    }
    
    public void showAll(){
        List<Category> categoryList = categoryDAO.findAll();
        
        System.out.println("****** All Category *****");
        for( Category c : categoryList){
            System.out.println(c.getId()+ ": " + c.getName());
        }
    }
    
    public void showById(){
        Scanner inp = new Scanner( System.in ); 
        int id = inp.nextInt();
        Long idL = new Long(id);
        Category category = categoryDAO.getById(idL);
        System.out.println("*** Category by requset ***");
        System.out.println(category.getId() + ": " + category.getName());
    }
    
    public void addCategory(){
        Scanner inp = new Scanner( System.in ); 
        System.out.println("*** Add new category ***");
        System.out.println("*Please input name of category*");
        String name = (String) inp.next();
        Category category = new Category(name);
        categoryDAO.add(category);  
    }
    
    public void deleteCatergory(){
        Scanner inp = new Scanner( System.in ); 
        System.out.println("*** Delete category ***");
        System.out.println("*Please input id of category*");
        int id = inp.nextInt();
        Long idL = new Long(id);
        
        categoryDAO.delete(idL);
    }
    
}
