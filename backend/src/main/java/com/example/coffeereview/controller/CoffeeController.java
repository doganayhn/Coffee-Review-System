package com.example.coffeereview.controller;

import com.example.coffeereview.dto.CoffeeDto;
import com.example.coffeereview.service.CoffeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {

  private final CoffeeService coffeeService;

  public CoffeeController(CoffeeService coffeeService) {
    this.coffeeService = coffeeService;
  }

  @GetMapping
  public List<CoffeeDto> getAll() {
    return coffeeService.getAllCoffees();
  }
}
