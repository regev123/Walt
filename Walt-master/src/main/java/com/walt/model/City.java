package com.walt.model;

import javax.persistence.Entity;

@Entity
public class City extends NamedEntity{

    public City(){}

    public City(String name){
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return this.getName()==city.getName();
    }
}
