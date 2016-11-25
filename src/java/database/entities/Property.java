/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.entities;

import java.util.Date;

/**
 *
 * @author semargl
 */
public class Property {
    
    private int id;
    private String street;
    private String city;
    private int listingNum;
    private int styleId;
    private int typeId;
    private int bedrooms;
    private double bathrooms;
    private int squarefeet;
    private String berRating;
    private String description;
    private String lotsize;
    private int garagesize;
    private int garageId;
    private int agentId;
    private String photo;
    private double price;
    private Date dateAdded;

    public Property(String street, String city, int listingNum, int styleId, int typeId, int bedrooms, double bathrooms, int squarefeet, String berRating, String description, String lotsize, int garagesize, int garageId, int agentId, String photo, double price, Date dateAdded) {
        this.street = street;
        this.city = city;
        this.listingNum = listingNum;
        this.styleId = styleId;
        this.typeId = typeId;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squarefeet = squarefeet;
        this.berRating = berRating;
        this.description = description;
        this.lotsize = lotsize;
        this.garagesize = garagesize;
        this.garageId = garageId;
        this.agentId = agentId;
        this.photo = photo;
        this.price = price;
        this.dateAdded = dateAdded;
    }

    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the listingNum
     */
    public int getListingNum() {
        return listingNum;
    }

    /**
     * @param listingNum the listingNum to set
     */
    public void setListingNum(int listingNum) {
        this.listingNum = listingNum;
    }

    /**
     * @return the styleId
     */
    public int getStyleId() {
        return styleId;
    }

    /**
     * @param styleId the styleId to set
     */
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    /**
     * @return the typeId
     */
    public int getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    /**
     * @return the bedrooms
     */
    public int getBedrooms() {
        return bedrooms;
    }

    /**
     * @param bedrooms the bedrooms to set
     */
    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    /**
     * @return the bathrooms
     */
    public double getBathrooms() {
        return bathrooms;
    }

    /**
     * @param bathrooms the bathrooms to set
     */
    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    /**
     * @return the squarefeet
     */
    public int getSquarefeet() {
        return squarefeet;
    }

    /**
     * @param squarefeet the squarefeet to set
     */
    public void setSquarefeet(int squarefeet) {
        this.squarefeet = squarefeet;
    }

    /**
     * @return the berRating
     */
    public String getBerRating() {
        return berRating;
    }

    /**
     * @param berRating the berRating to set
     */
    public void setBerRating(String berRating) {
        this.berRating = berRating;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the lotsize
     */
    public String getLotsize() {
        return lotsize;
    }

    /**
     * @param lotsize the lotsize to set
     */
    public void setLotsize(String lotsize) {
        this.lotsize = lotsize;
    }

    /**
     * @return the garagesize
     */
    public int getGaragesize() {
        return garagesize;
    }

    /**
     * @param garagesize the garagesize to set
     */
    public void setGaragesize(int garagesize) {
        this.garagesize = garagesize;
    }

    /**
     * @return the garageId
     */
    public int getGarageId() {
        return garageId;
    }

    /**
     * @param garageId the garageId to set
     */
    public void setGarageId(int garageId) {
        this.garageId = garageId;
    }

    /**
     * @return the agentId
     */
    public int getAgentId() {
        return agentId;
    }

    /**
     * @param agentId the agentId to set
     */
    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the dateAdded
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * @param dateAdded the dateAdded to set
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
}
