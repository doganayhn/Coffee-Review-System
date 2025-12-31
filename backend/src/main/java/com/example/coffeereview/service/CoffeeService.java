package com.example.coffeereview.service;

import com.example.coffeereview.dto.CoffeeDto;
import com.example.coffeereview.dto.CreateReviewRequest;

import java.util.List;

public interface CoffeeService {
  List<CoffeeDto> getAllCoffees();
  CoffeeDto addReview(CreateReviewRequest req);
}
