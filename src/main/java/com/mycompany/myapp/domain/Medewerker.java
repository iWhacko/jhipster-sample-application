package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Medewerker.
 */
@Entity
@Table(name = "medewerker")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Medewerker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "naam")
    private String naam;

    @Column(name = "achter_naam")
    private String achterNaam;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public Medewerker naam(String naam) {
        this.naam = naam;
        return this;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public Medewerker achterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
        return this;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Medewerker)) {
            return false;
        }
        return id != null && id.equals(((Medewerker) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Medewerker{" +
            "id=" + getId() +
            ", naam='" + getNaam() + "'" +
            ", achterNaam='" + getAchterNaam() + "'" +
            "}";
    }
}
