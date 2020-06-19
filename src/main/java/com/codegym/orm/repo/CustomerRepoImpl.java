package com.codegym.orm.repo;

import com.codegym.orm.model.Customer;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
public class CustomerRepoImpl implements CustomerRepo {
    @PersistenceContext
    private EntityManager entityManager;


    //region STATIC QUERY
    //description: Static Queries are declared in Model class.
    public List<Customer> getCustomerByName(String name) {
        TypedQuery<Customer> query = entityManager.createNamedQuery("findCustomerByName", Customer.class);
        if(name.trim().equals("")) name = "%";
        query.setParameter("name", name + "%");
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return  null;
        }
    }
    //endregion

    //region DYNAMIC QUERY
    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c where c.id = :id", Customer.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e)
        {
            return null;
        }
    }
    //endregion

    //region METHOD QUERY
    @Override
    public boolean saveCustomer(Customer customer) {
        if(findById(customer.getId()) == null) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
        return true;
    }

    @Override
    public boolean removeCustomer(Customer customer) {
        entityManager.remove(findById(customer.getId()));
        return true;
    }
    //endregion
}
