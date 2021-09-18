package com.walt.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    Driver driver;

    @ManyToOne
    Restaurant restaurant;

    @ManyToOne
    Customer customer;

    Date deliveryTime;

    Random rand;

    double distance;

    public Delivery() {
    }

    public Delivery(Driver driver, Restaurant restaurant, Customer customer, Date deliveryTime) {
        this.driver = driver;
        this.restaurant = restaurant;
        this.customer = customer;
        this.deliveryTime = deliveryTime;
        this.rand = new Random();
        this.setDistance(rand.nextDouble() * 20);
        this.driver.incrementDeliveriesCount();
    }

    public Long getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
        this.driver.addToTotalDistance(Long.valueOf((long) distance));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Delivery))
            return false;

        Delivery c = (Delivery) o;

        return  this.driver.getId() == c.driver.getId()
                && this.deliveryTime.compareTo( c.getDeliveryTime() ) == 0
                && this.restaurant.getId() == c.restaurant.getId()
                && this.customer.getId() == c.customer.getId();
    }
}


