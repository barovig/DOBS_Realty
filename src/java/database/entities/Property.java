/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author semargl
 */
@Entity
@Table(name = "properties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Property.findAll", query = "SELECT p FROM Property p"),
    @NamedQuery(name = "Property.findById", query = "SELECT p FROM Property p WHERE p.id = :id"),
    @NamedQuery(name = "Property.findByStreet", query = "SELECT p FROM Property p WHERE p.street = :street"),
    @NamedQuery(name = "Property.findByCity", query = "SELECT p FROM Property p WHERE p.city = :city"),
    @NamedQuery(name = "Property.findByListingNum", query = "SELECT p FROM Property p WHERE p.listingNum = :listingNum"),
    @NamedQuery(name = "Property.findByBedrooms", query = "SELECT p FROM Property p WHERE p.bedrooms = :bedrooms"),
    @NamedQuery(name = "Property.findByBathrooms", query = "SELECT p FROM Property p WHERE p.bathrooms = :bathrooms"),
    @NamedQuery(name = "Property.findBySquarefeet", query = "SELECT p FROM Property p WHERE p.squarefeet = :squarefeet"),
    @NamedQuery(name = "Property.findByBerRating", query = "SELECT p FROM Property p WHERE p.berRating = :berRating"),
    @NamedQuery(name = "Property.findByLotsize", query = "SELECT p FROM Property p WHERE p.lotsize = :lotsize"),
    @NamedQuery(name = "Property.findByGaragesize", query = "SELECT p FROM Property p WHERE p.garagesize = :garagesize"),
    @NamedQuery(name = "Property.findByPhoto", query = "SELECT p FROM Property p WHERE p.photo = :photo"),
    @NamedQuery(name = "Property.findByPrice", query = "SELECT p FROM Property p WHERE p.price = :price"),
    @NamedQuery(name = "Property.findByDateAdded", query = "SELECT p FROM Property p WHERE p.dateAdded = :dateAdded"),
    @NamedQuery(name = "Property.getDistinctCities", query = "SELECT DISTINCT p.city FROM Property p"),
    @NamedQuery(name = "Property.getByPriceAndCity", query = "SELECT p FROM Property p WHERE p.city LIKE :city AND p.price BETWEEN :min AND :max")})
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
    @Column(name = "bedrooms")
    private Integer bedrooms;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bathrooms")
    private Float bathrooms;
    @Column(name = "squarefeet")
    private Integer squarefeet;
    @Basic(optional = false)
    @Column(name = "berRating")
    private String berRating;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "lotsize")
    private String lotsize;
    @Column(name = "garagesize")
    private Short garagesize;
    @Column(name = "photo")
    private String photo;
    @Column(name = "price")
    private Double price;
    @Basic(optional = false)
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @JoinColumn(name = "agentId", referencedColumnName = "agentId")
    @ManyToOne
    private Agent agentId;
    @JoinColumn(name = "garageId", referencedColumnName = "garageId")
    @ManyToOne
    private Garage garageId;
    @JoinColumn(name = "typeId", referencedColumnName = "typeId")
    @ManyToOne
    private PropertyType typeId;
    @JoinColumn(name = "styleId", referencedColumnName = "styleId")
    @ManyToOne
    private Style styleId;

    public Property() {
    }

    public Property(Integer id) {
        this.id = id;
    }

    public Property(Integer id, String berRating, Date dateAdded) {
        this.id = id;
        this.berRating = berRating;
        this.dateAdded = dateAdded;
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

    public String getBerRating() {
        return berRating;
    }

    public void setBerRating(String berRating) {
        this.berRating = berRating;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Agent getAgentId() {
        return agentId;
    }

    public void setAgentId(Agent agentId) {
        this.agentId = agentId;
    }

    public Garage getGarageId() {
        return garageId;
    }

    public void setGarageId(Garage garageId) {
        this.garageId = garageId;
    }

    public PropertyType getTypeId() {
        return typeId;
    }

    public void setTypeId(PropertyType typeId) {
        this.typeId = typeId;
    }

    public Style getStyleId() {
        return styleId;
    }

    public void setStyleId(Style styleId) {
        this.styleId = styleId;
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
        return "database.entities.Property[ id=" + id + " ]";
    }
    
}
