package com.example.coffeereview.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 80)
  private String author;

  @Column(nullable = false)
  private int rating;

  @Column(nullable = false, length = 500)
  private String comment;

  @Column(nullable = false)
  private LocalDate date;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "coffee_id", nullable = false)
  private Coffee coffee;

  public Review() {
  }

  public Review(String author, int rating, String comment, LocalDate date, Coffee coffee) {
    this.author = author;
    this.rating = rating;
    this.comment = comment;
    this.date = date;
    this.coffee = coffee;
  }

  // --- getters/setters ---

  public Long getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public int getRating() {
    return rating;
  }

  public String getComment() {
    return comment;
  }

  public LocalDate getDate() {
    return date;
  }

  public Coffee getCoffee() {
    return coffee;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public void setCoffee(Coffee coffee) {
    this.coffee = coffee;
  }
}
