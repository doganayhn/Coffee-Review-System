package com.example.coffeereview.dto;

import java.math.BigDecimal;
import java.util.List;

public class CoffeeDto {
  public Long id;
  public String name;
  public String description;
  public BigDecimal price;
  public String imageUrl;
  public double averageRating;
  public List<ReviewDto> reviews;
}
