package com.aplikasi.aternuscommerce.Domain;

import com.aplikasi.aternuscommerce.Activity.Product.ChairsProduct;

public class ProductDomain {
    int id;
    String title, description, type, poster, price, score,review;

    public ProductDomain(String title, String description, String picUrl, String price, String type, String review, String score, int numberInCart) {
        this.title = title;
        this.description = description;
        this.poster = poster;
        this.price = price;
        this.type = type;
        this.review = review;
        this.score = score;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}