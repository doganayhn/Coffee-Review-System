package com.example.coffeereview.service;

import com.example.coffeereview.dto.CoffeeDto;
import com.example.coffeereview.dto.CreateReviewRequest;
import com.example.coffeereview.dto.ReviewDto;
import com.example.coffeereview.entity.Coffee;
import com.example.coffeereview.entity.Review;
import com.example.coffeereview.repo.CoffeeRepository;
import com.example.coffeereview.repo.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CoffeeServiceImpl implements CoffeeService {

  private final CoffeeRepository coffeeRepository;
  private final ReviewRepository reviewRepository;

  public CoffeeServiceImpl(CoffeeRepository coffeeRepository, ReviewRepository reviewRepository) {
    this.coffeeRepository = coffeeRepository;
    this.reviewRepository = reviewRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public List<CoffeeDto> getAllCoffees() {
    List<Coffee> coffees = coffeeRepository.findAll();
    // keep deterministic order for demos
    coffees.sort(Comparator.comparing(Coffee::getId));
    List<CoffeeDto> out = new ArrayList<>();
    for (Coffee c : coffees) out.add(toDto(c));
    return out;
  }

  @Override
  @Transactional
  public CoffeeDto addReview(CreateReviewRequest req) {
    Coffee coffee = coffeeRepository.findById(req.coffeeId)
        .orElseThrow(() -> new IllegalArgumentException("Coffee not found: " + req.coffeeId));

    Review review = new Review(req.author.trim(), req.rating, req.comment.trim(), LocalDate.now(), coffee);
    reviewRepository.save(review);

    // refresh coffee with reviews
    Coffee refreshed = coffeeRepository.findById(coffee.getId()).orElseThrow();
    return toDto(refreshed);
  }

  private CoffeeDto toDto(Coffee c) {
    CoffeeDto dto = new CoffeeDto();
    dto.id = c.getId();
    dto.name = c.getName();
    dto.description = c.getDescription();
    dto.price = c.getPrice();
    dto.imageUrl = c.getImageUrl();
    dto.averageRating = c.getAverageRating();

    List<ReviewDto> reviews = new ArrayList<>();
    if (c.getReviews() != null) {
      // stable order (by id)
      c.getReviews().sort(Comparator.comparing(Review::getId));
      for (Review r : c.getReviews()) {
        ReviewDto rd = new ReviewDto();
        rd.id = r.getId();
        rd.author = r.getAuthor();
        rd.rating = r.getRating();
        rd.comment = r.getComment();
        rd.date = r.getDate() != null ? r.getDate().toString() : null;
        reviews.add(rd);
      }
    }
    dto.reviews = reviews;
    return dto;
  }
}
