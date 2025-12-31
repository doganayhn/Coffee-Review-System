package com.example.coffeereview.controller;

import com.example.coffeereview.dto.CoffeeDto;
import com.example.coffeereview.dto.CreateReviewRequest;
import com.example.coffeereview.service.CoffeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

  private final CoffeeService coffeeService;

  public ReviewController(CoffeeService coffeeService) {
    this.coffeeService = coffeeService;
  }

  @PostMapping
  public CoffeeDto create(@Valid @RequestBody CreateReviewRequest req) {
    return coffeeService.addReview(req);
  }
}
