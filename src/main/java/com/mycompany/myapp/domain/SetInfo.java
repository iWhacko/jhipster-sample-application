package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SetInfo.
 */
@Entity
@Table(name = "set_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SetInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "geslacht")
    private String geslacht;

    @Column(name = "naam")
    private String naam;

    @Column(name = "achternaam")
    private String achternaam;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "huis_nummer")
    private String huisNummer;

    @Column(name = "straat")
    private String straat;

    @Column(name = "plaats")
    private String plaats;

    @Column(name = "telefoon")
    private String telefoon;

    @Column(name = "email")
    private String email;

    @Column(name = "contract")
    private String contract;

    @Column(name = "telefonisch")
    private Boolean telefonisch;

    @Column(name = "taal")
    private String taal;

    @Column(name = "verzend_methode")
    private String verzendMethode;

    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(unique = true)
    private Medewerker medewerker;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public SetInfo geslacht(String geslacht) {
        this.geslacht = geslacht;
        return this;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public String getNaam() {
        return naam;
    }

    public SetInfo naam(String naam) {
        this.naam = naam;
        return this;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public SetInfo achternaam(String achternaam) {
        this.achternaam = achternaam;
        return this;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getPostCode() {
        return postCode;
    }

    public SetInfo postCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getHuisNummer() {
        return huisNummer;
    }

    public SetInfo huisNummer(String huisNummer) {
        this.huisNummer = huisNummer;
        return this;
    }

    public void setHuisNummer(String huisNummer) {
        this.huisNummer = huisNummer;
    }

    public String getStraat() {
        return straat;
    }

    public SetInfo straat(String straat) {
        this.straat = straat;
        return this;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getPlaats() {
        return plaats;
    }

    public SetInfo plaats(String plaats) {
        this.plaats = plaats;
        return this;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public SetInfo telefoon(String telefoon) {
        this.telefoon = telefoon;
        return this;
    }

    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }

    public String getEmail() {
        return email;
    }

    public SetInfo email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContract() {
        return contract;
    }

    public SetInfo contract(String contract) {
        this.contract = contract;
        return this;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Boolean isTelefonisch() {
        return telefonisch;
    }

    public SetInfo telefonisch(Boolean telefonisch) {
        this.telefonisch = telefonisch;
        return this;
    }

    public void setTelefonisch(Boolean telefonisch) {
        this.telefonisch = telefonisch;
    }

    public String getTaal() {
        return taal;
    }

    public SetInfo taal(String taal) {
        this.taal = taal;
        return this;
    }

    public void setTaal(String taal) {
        this.taal = taal;
    }

    public String getVerzendMethode() {
        return verzendMethode;
    }

    public SetInfo verzendMethode(String verzendMethode) {
        this.verzendMethode = verzendMethode;
        return this;
    }

    public void setVerzendMethode(String verzendMethode) {
        this.verzendMethode = verzendMethode;
    }

    public Medewerker getMedewerker() {
        return medewerker;
    }

    public SetInfo medewerker(Medewerker medewerker) {
        this.medewerker = medewerker;
        return this;
    }

    public void setMedewerker(Medewerker medewerker) {
        this.medewerker = medewerker;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SetInfo)) {
            return false;
        }
        return id != null && id.equals(((SetInfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SetInfo{" +
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
            "}";
    }
}
