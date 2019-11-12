package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.SetInfo} entity.
 */
public class SetInfoDTO implements Serializable {

    private Long id;

    private String geslacht;

    private String naam;

    private String achternaam;

    private String postCode;

    private String huisNummer;

    private String straat;

    private String plaats;

    private String telefoon;

    private String email;

    private String contract;

    private Boolean telefonisch;

    private String taal;

    private String verzendMethode;


    private Long medewerkerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getHuisNummer() {
        return huisNummer;
    }

    public void setHuisNummer(String huisNummer) {
        this.huisNummer = huisNummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Boolean isTelefonisch() {
        return telefonisch;
    }

    public void setTelefonisch(Boolean telefonisch) {
        this.telefonisch = telefonisch;
    }

    public String getTaal() {
        return taal;
    }

    public void setTaal(String taal) {
        this.taal = taal;
    }

    public String getVerzendMethode() {
        return verzendMethode;
    }

    public void setVerzendMethode(String verzendMethode) {
        this.verzendMethode = verzendMethode;
    }

    public Long getMedewerkerId() {
        return medewerkerId;
    }

    public void setMedewerkerId(Long medewerkerId) {
        this.medewerkerId = medewerkerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SetInfoDTO setInfoDTO = (SetInfoDTO) o;
        if (setInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), setInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SetInfoDTO{" +
            "id=" + getId() +
            ", geslacht='" + getGeslacht() + "'" +
            ", naam='" + getNaam() + "'" +
            ", achternaam='" + getAchternaam() + "'" +
            ", postCode='" + getPostCode() + "'" +
            ", huisNummer='" + getHuisNummer() + "'" +
            ", straat='" + getStraat() + "'" +
            ", plaats='" + getPlaats() + "'" +
            ", telefoon='" + getTelefoon() + "'" +
            ", email='" + getEmail() + "'" +
            ", contract='" + getContract() + "'" +
            ", telefonisch='" + isTelefonisch() + "'" +
            ", taal='" + getTaal() + "'" +
            ", verzendMethode='" + getVerzendMethode() + "'" +
            ", medewerker=" + getMedewerkerId() +
            "}";
    }
}
