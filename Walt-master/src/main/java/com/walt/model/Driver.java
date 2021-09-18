package com.walt.model;

import javax.persistence.*;

@Entity
public class Driver extends NamedEntity implements DriverDistance{

    @ManyToOne
    City city;

    private Long totalDistance;

    private int deliveriesCount;

    public Driver(){}

    public Driver(String name, City city){
        super(name);
        this.city = city;
        this.totalDistance=0L;
        this.deliveriesCount=0;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public Long getTotalDistance() {
        return totalDistance;
    }

    public void addToTotalDistance(Long deliveryDistance){
        this.totalDistance+=deliveryDistance;
    }

    public void incrementDeliveriesCount(){
        deliveriesCount++;
    }

    public int getDeliveriesCount(){
        return deliveriesCount;
    }
}
