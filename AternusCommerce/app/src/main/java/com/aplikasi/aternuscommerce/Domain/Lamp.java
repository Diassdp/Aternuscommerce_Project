package com.aplikasi.aternuscommerce.Domain;

public class Lamp {
    private String tittle, description, category, price, review, score,  poster;

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getReview() {
        return review;
    }

    public String getScore() {
        return score;
    }

    public String getPoster() {
        return poster;
    }

    public Lamp(String tittle, String description, String category, String price, String review, String score, String poster){
        this.tittle = tittle;
        this.description = description;
        this.category = category;
        this.price = price;
        this.review = review;
        this.score = score;
        this.poster = poster;
    }
}
