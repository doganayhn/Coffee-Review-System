package com.example.coffeereview.repo;

import com.example.coffeereview.entity.Coffee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

  @Override
  @EntityGraph(attributePaths = {"reviews", "category"})
  List<Coffee> findAll();

  @Override
  @EntityGraph(attributePaths = {"reviews", "category"})
  Optional<Coffee> findById(Long id);
}
