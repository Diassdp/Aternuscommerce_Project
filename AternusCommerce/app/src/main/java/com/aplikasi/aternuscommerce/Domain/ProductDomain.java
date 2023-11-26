package com.aplikasi.aternuscommerce.Domain;

import com.aplikasi.aternuscommerce.Activity.Product.ChairsProduct;

public class ProductDomain {
    int id;
    String title, description, category,price,review,score,poster;

    public ProductDomain(String title, String description, String poster, String price, String category, String review, String score, int numberInCart) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.review = review;
        this.score = score;
        this.poster = poster;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}