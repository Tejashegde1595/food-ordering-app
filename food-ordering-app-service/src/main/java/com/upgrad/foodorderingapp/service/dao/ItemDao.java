package com.upgrad.foodorderingapp.service.dao;

import com.upgrad.foodorderingapp.service.entity.ItemEntity;
import com.upgrad.foodorderingapp.service.entity.OrderItemEntity;
import com.upgrad.foodorderingapp.service.entity.RestaurantItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemDao {
    private final Logger log = LoggerFactory.getLogger(CustomerDao.class);
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Get  item details using restuarnt uuid
     *
     * @param restaurantId - String represents item uuid
     * @return - item details using restaurant uuid
     */
    public List<RestaurantItemEntity> getItemForRestaurantUUID(final String restaurantId){
        log.info("start getting all restaurant items from the database");
        List<RestaurantItemEntity> restaurantItemEntityList = entityManager.createNamedQuery("getAllRestaurantItemsByRestaurantId",RestaurantItemEntity.class)
                .setParameter("uuid",restaurantId).getResultList();
        log.info("end getting all restaurant items from the database");
        return restaurantItemEntityList;
    }

    /**
     * Retrieves the Item with the matched uuid
     *
     * @param itemUUID The uuid of the Item to be searched for
     * @return The Item Entity present in the Database if found, null otherwise
     */
    public ItemEntity getItemByUUID(String itemUUID) {
        try {
            return entityManager.createNamedQuery("itemByUUID", ItemEntity.class).setParameter("uuid", itemUUID).getSingleResult();
        }
        catch (NoResultException nre) {
            return null;
        }
    }

    public List<OrderItemEntity> getItemByRestaurantUUID(String restaurantId){
        return entityManager.createNamedQuery("itemsByRestaurantId",OrderItemEntity.class).setParameter("id",restaurantId).getResultList();
    }
}
