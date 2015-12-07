
package com.mycompany.scklad.dao;

import java.util.List;

/**
 *
 * @author Georgiy Popov
 */
public interface GeneralizedDAO<Type,PK extends Number> {
    
    public List<Type> findAll();
    
    public void add(Type t);
    
    public Type getById(PK id);
    
    public void update(Type t);
    
    public void delete(PK id);

}
