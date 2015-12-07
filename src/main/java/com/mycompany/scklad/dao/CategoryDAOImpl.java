package com.mycompany.scklad.dao;


import com.mycompany.scklad.domain.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Georgiy Popov
 */
public class CategoryDAOImpl implements CategoryDAO{
    
    private ConnectionFactory connectionFactory;
    private Connection con;
    
    public CategoryDAOImpl(){
        try {
            this.connectionFactory = ConnectionFactory.getInstance();
            this.con = connectionFactory.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public  List<Category> findAll(){
        
        List<Category> list = new ArrayList<Category>();      
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id,name FROM category";
            
        try { 
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Category cate = new Category(rs.getString(2));
                cate.setId(rs.getLong(1));
                list.add(cate);
            }
            
            rs.close();
            stmt.close();
            con.commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    @Override
    public void add(Category t) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO category"
		+ "(name) VALUES"
		+ "( ? );";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1,t.getName());
            stmt.execute();
            
            stmt.close();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Category getById(Long id) {
        Category categ = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id,name FROM category WHERE id=?";
        try{
            
            stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            
            rs = stmt.executeQuery();
            if (rs.next()){
                categ = new Category();
                categ.setId(rs.getLong(1));
                categ.setName(rs.getString(2));
            }
            
            rs.close();
            stmt.close();
            con.commit();
            
            return categ;
            
        } catch (SQLException ex){
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void update(Category t) {
        PreparedStatement stmt = null;
        String sql = "UPDATE category SET name=? WHERE id=?";
        try{
            stmt = con.prepareStatement(sql);
            stmt.setString(1,t.getName());
            stmt.setLong(2, t.getId());
            stmt.execute();
                
            stmt.close();
            con.commit();
        } catch (SQLException ex){
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Long id) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM category WHERE id=?";
        try{
            stmt = con.prepareCall(sql);
            stmt.setLong(1,id);
            stmt.execute();
            
            stmt.close();
            con.commit();
        } catch (SQLException ex){
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
