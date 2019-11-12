package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Medewerker} entity.
 */
public class MedewerkerDTO implements Serializable {

    private Long id;

    private String naam;

    private String achterNaam;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MedewerkerDTO medewerkerDTO = (MedewerkerDTO) o;
        if (medewerkerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), medewerkerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MedewerkerDTO{" +
            "id=" + getId() +
            ", naam='" + getNaam() + "'" +
            ", achterNaam='" + getAchterNaam() + "'" +
            "}";
    }
}
