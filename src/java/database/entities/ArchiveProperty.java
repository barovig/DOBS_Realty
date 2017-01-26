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
import javax.persistence.Lob;
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
@Table(name = "archive_properties")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ArchiveProperty.findAll", query = "SELECT a FROM ArchiveProperty a")
	, @NamedQuery(name = "ArchiveProperty.findById", query = "SELECT a FROM ArchiveProperty a WHERE a.id = :id")
	, @NamedQuery(name = "ArchiveProperty.findByStreet", query = "SELECT a FROM ArchiveProperty a WHERE a.street = :street")
	, @NamedQuery(name = "ArchiveProperty.findByCity", query = "SELECT a FROM ArchiveProperty a WHERE a.city = :city")
	, @NamedQuery(name = "ArchiveProperty.findByListingNum", query = "SELECT a FROM ArchiveProperty a WHERE a.listingNum = :listingNum")
	, @NamedQuery(name = "ArchiveProperty.findByStyleId", query = "SELECT a FROM ArchiveProperty a WHERE a.styleId = :styleId")
	, @NamedQuery(name = "ArchiveProperty.findByTypeId", query = "SELECT a FROM ArchiveProperty a WHERE a.typeId = :typeId")
	, @NamedQuery(name = "ArchiveProperty.findByBedrooms", query = "SELECT a FROM ArchiveProperty a WHERE a.bedrooms = :bedrooms")
	, @NamedQuery(name = "ArchiveProperty.findByBathrooms", query = "SELECT a FROM ArchiveProperty a WHERE a.bathrooms = :bathrooms")
	, @NamedQuery(name = "ArchiveProperty.findBySquarefeet", query = "SELECT a FROM ArchiveProperty a WHERE a.squarefeet = :squarefeet")
	, @NamedQuery(name = "ArchiveProperty.findByBerRating", query = "SELECT a FROM ArchiveProperty a WHERE a.berRating = :berRating")
	, @NamedQuery(name = "ArchiveProperty.findByLotsize", query = "SELECT a FROM ArchiveProperty a WHERE a.lotsize = :lotsize")
	, @NamedQuery(name = "ArchiveProperty.findByGaragesize", query = "SELECT a FROM ArchiveProperty a WHERE a.garagesize = :garagesize")
	, @NamedQuery(name = "ArchiveProperty.findByGarageId", query = "SELECT a FROM ArchiveProperty a WHERE a.garageId = :garageId")
	, @NamedQuery(name = "ArchiveProperty.findByAgentId", query = "SELECT a FROM ArchiveProperty a WHERE a.agentId = :agentId")
	, @NamedQuery(name = "ArchiveProperty.findByPhoto", query = "SELECT a FROM ArchiveProperty a WHERE a.photo = :photo")
	, @NamedQuery(name = "ArchiveProperty.findByPrice", query = "SELECT a FROM ArchiveProperty a WHERE a.price = :price")
	, @NamedQuery(name = "ArchiveProperty.findByDateAdded", query = "SELECT a FROM ArchiveProperty a WHERE a.dateAdded = :dateAdded")})
public class ArchiveProperty implements Serializable {

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
	@Column(name = "garageId")
	private Integer garageId;
	@Column(name = "agentId")
	private Integer agentId;
	@Column(name = "photo")
	private String photo;
	@Column(name = "price")
	private Double price;
	@Basic(optional = false)
    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
	private Date dateAdded;

	public ArchiveProperty() {
	}

	public ArchiveProperty(Integer id) {
		this.id = id;
	}

	public ArchiveProperty(Integer id, String berRating, Date dateAdded) {
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

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
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
		if (!(object instanceof ArchiveProperty)) {
			return false;
		}
		ArchiveProperty other = (ArchiveProperty) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "database.entities.ArchiveProperty[ id=" + id + " ]";
	}
	
}
