package com.example.coffeereview.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coffees")
public class Coffee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 80)
  private String name;

  @Column(nullable = false, length = 200)
  private String description;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(nullable = false, length = 500)
  private String imageUrl;

  // ✅ NEW: Category relation (Coffee -> Category)
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  // Existing relationship: Coffee -> Reviews
  @OneToMany(
          mappedBy = "coffee",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @OrderBy("id ASC")
  private List<Review> reviews = new ArrayList<>();

  public Coffee() {
  }

  @Transient
  public double getAverageRating() {
    if (reviews == null || reviews.isEmpty()) {
      return 0.0;
    }

    return reviews.stream()
            .mapToInt(Review::getRating)
            .average()
            .orElse(0.0);
  }


  // ✅ NEW constructor including category
  public Coffee(String name, String description, BigDecimal price, String imageUrl, Category category) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.imageUrl = imageUrl;
    this.category = category;
  }

  // (İstersen eskisini de bırakabilirsin — ama DB'de category_id zorunluysa bunu kullanma)
  // public Coffee(String name, String description, BigDecimal price, String imageUrl) { ... }

  // --- getters/setters ---

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Category getCategory() {
    return category;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }
}
