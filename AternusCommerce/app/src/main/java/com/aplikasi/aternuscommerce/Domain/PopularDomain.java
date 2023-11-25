package com.aplikasi.aternuscommerce.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {
    private String title;
    private String description;
    private String picUrl;
    private double price;
    private String type;
    private int review;
    private double score;
    private int numberInCart;

    public PopularDomain(String title, String description, String picUrl, double price, String type, int review, double score, int numberInCart) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.price = price;
        this.type = type;
        this.review = review;
        this.score = score;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
