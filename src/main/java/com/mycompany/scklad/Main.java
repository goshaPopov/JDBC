/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scklad;

import com.mycompany.scklad.dao.CategoryDAO;
import com.mycompany.scklad.dao.CategoryDAOImpl;
import java.util.Scanner;

/**
 *
 * @author gosha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        View view = new View(categoryDAO);
        Scanner inp = new Scanner( System.in ); 
        
        int choice = 0;
        boolean iter = true;
        while(iter){
            System.out.println("1) Показать все записи;");
            System.out.println("2) Добавить запись;");
            System.out.println("3) Показать запись по id;");
            System.out.println("4) Удалить запись;");
            System.out.println("5) Выход.");
            
            choice = inp.nextInt();
            
            switch (choice){
                case 1: view.showAll();
                        break;
                case 2: view.addCategory();
                        break;
                case 3: view.showById();
                        break;
                case 4: view.deleteCatergory();
                        break;
                case 5: iter = false;
                        break;
                        
            }
            
        }
        
    }
    
}
