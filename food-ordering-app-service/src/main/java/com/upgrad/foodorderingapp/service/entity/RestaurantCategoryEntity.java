package com.upgrad.foodorderingapp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "restaurant_category")
@NamedQueries(
        {
                @NamedQuery(name = "getAllRestaurantCategories",
                        query = "select re from RestaurantCategoryEntity re"),
                @NamedQuery(name = "getAllCategoryItemsByRestaurantuuid", query = "select re from RestaurantCategoryEntity re where re.restaurantEntity.uuid=:uuid"),
                @NamedQuery(name = "getRestaurantsByCategory", query = "select rc from RestaurantCategoryEntity rc where rc.categoryEntity.uuid =:categoryUuid")

        }
)
public class RestaurantCategoryEntity implements Serializable {


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="RESTAURANT_ID")
    @NotNull
    private RestaurantEntity restaurantEntity;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    @NotNull
    private CategoryEntity categoryEntity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RestaurantEntity getRestaurantEntity() {
        return restaurantEntity;
    }

    public void setRestaurantEntity(RestaurantEntity restaurantEntity) {
        this.restaurantEntity = restaurantEntity;
    }
    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }


}
