package com.comdata.factory.app.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * relationship OneToOne {
 * Bus{vehicle} to Vehicle{bus},
 * CityBus{bus} to Bus{cityBus},
 * InterCityBus{bus} to Bus{interCityBus}
 * }
 */
@ApiModel(description = "relationship OneToOne { Bus{vehicle} to Vehicle{bus}, CityBus{bus} to Bus{cityBus}, InterCityBus{bus} to Bus{interCityBus} }")
@Entity
@Table(name = "truck")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Truck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 2)
    @Max(value = 4)
    @Column(name = "number_of_axles")
    private Integer numberOfAxles;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfAxles() {
        return numberOfAxles;
    }

    public Truck numberOfAxles(Integer numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
        return this;
    }

    public void setNumberOfAxles(Integer numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        if (truck.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), truck.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Truck{" +
            "id=" + getId() +
            ", numberOfAxles='" + getNumberOfAxles() + "'" +
            "}";
    }
}
