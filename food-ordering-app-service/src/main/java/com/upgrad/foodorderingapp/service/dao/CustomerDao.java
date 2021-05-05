package com.upgrad.foodorderingapp.service.dao;

import com.upgrad.foodorderingapp.service.entity.CustomerAuthEntity;
import com.upgrad.foodorderingapp.service.entity.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerDao {

    private final Logger log = LoggerFactory.getLogger(CustomerDao.class);
    @PersistenceContext
    private EntityManager entityManager;

    public CustomerEntity createUser(CustomerEntity customerEntity) {
        log.info("create a new customer in the database");
        entityManager.persist(customerEntity);
        log.info("customer successfully created");
        return customerEntity;
    }

    /**
     * Get Customer based on contact number
     *
     * @param contactNumber
     * @return
     */
    public CustomerEntity getCustomerByContactNo(String contactNumber) {
        try {
            return entityManager.createNamedQuery("customerByContactNumber", CustomerEntity.class)
                    .setParameter("contactNo", contactNumber)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    /**
     * Method to persist CustomerAuthEntity in the database
     *
     * @param customerAuth - CustomerAuthEntity to be persisted in the database
     *
     */
    public void createAuthToken(CustomerAuthEntity customerAuth) {
        entityManager.persist(customerAuth);
    }
}
