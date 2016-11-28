/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author K00191419
 */
@Entity
@Table(name = "properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Properties.findAll", query = "SELECT p FROM Properties p"),
    @NamedQuery(name = "Properties.findById", query = "SELECT p FROM Properties p WHERE p.id = :id"),
    @NamedQuery(name = "Properties.findByStreet", query = "SELECT p FROM Properties p WHERE p.street = :street"),
    @NamedQuery(name = "Properties.findByCity", query = "SELECT p FROM Properties p WHERE p.city = :city"),
    @NamedQuery(name = "Properties.findByListingNum", query = "SELECT p FROM Properties p WHERE p.listingNum = :listingNum"),
    @NamedQuery(name = "Properties.findByStyleId", query = "SELECT p FROM Properties p WHERE p.styleId = :styleId"),
    @NamedQuery(name = "Properties.findByTypeId", query = "SELECT p FROM Properties p WHERE p.typeId = :typeId"),
    @NamedQuery(name = "Properties.findByBedrooms", query = "SELECT p FROM Properties p WHERE p.bedrooms = :bedrooms"),
    @NamedQuery(name = "Properties.findByBathrooms", query = "SELECT p FROM Properties p WHERE p.bathrooms = :bathrooms"),
    @NamedQuery(name = "Properties.findBySquarefeet", query = "SELECT p FROM Properties p WHERE p.squarefeet = :squarefeet"),
    @NamedQuery(name = "Properties.findByLotsize", query = "SELECT p FROM Properties p WHERE p.lotsize = :lotsize"),
    @NamedQuery(name = "Properties.findByGaragesize", query = "SELECT p FROM Properties p WHERE p.garagesize = :garagesize"),
    @NamedQuery(name = "Properties.findByGarageId", query = "SELECT p FROM Properties p WHERE p.garageId = :garageId"),
    @NamedQuery(name = "Properties.findByAgentId", query = "SELECT p FROM Properties p WHERE p.agentId = :agentId"),
    @NamedQuery(name = "Properties.findByPhoto", query = "SELECT p FROM Properties p WHERE p.photo = :photo"),
    @NamedQuery(name = "Properties.findByPrice", query = "SELECT p FROM Properties p WHERE p.price = :price")})
public class Property implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "listingNum")
    private Integer listingNum;
    @Column(name = "styleId")
    private Integer styleId;
    @Column(name = "typeId")
    private Integer typeId;
    @Column(name = "bedrooms")
    private Integer bedrooms;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bathrooms")
    private Float bathrooms;
    @Column(name = "squarefeet")
    private Integer squarefeet;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "lotsize")
    private String lotsize;
    @Column(name = "garagesize")
    private Short garagesize;
    @Column(name = "garageId")
    private Integer garageId;
    @Column(name = "agentId")
    private Integer agentId;
    @Column(name = "photo")
    private String photo;
    @Column(name = "price")
    private Double price;

    public Property() {
    }

    public Property(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getListingNum() {
        return listingNum;
    }

    public void setListingNum(Integer listingNum) {
        this.listingNum = listingNum;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Float getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Float bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getSquarefeet() {
        return squarefeet;
    }

    public void setSquarefeet(Integer squarefeet) {
        this.squarefeet = squarefeet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLotsize() {
        return lotsize;
    }

    public void setLotsize(String lotsize) {
        this.lotsize = lotsize;
    }

    public Short getGaragesize() {
        return garagesize;
    }

    public void setGaragesize(Short garagesize) {
        this.garagesize = garagesize;
    }

    public Integer getGarageId() {
        return garageId;
    }

    public void setGarageId(Integer garageId) {
        this.garageId = garageId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Property)) {
            return false;
        }
        Property other = (Property) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.entities.Properties[ id=" + id + " ]";
    }
    
}
