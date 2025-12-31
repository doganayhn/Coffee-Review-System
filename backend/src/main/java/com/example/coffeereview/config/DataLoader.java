package com.example.coffeereview.config;

import com.example.coffeereview.entity.Category;
import com.example.coffeereview.entity.Coffee;
import com.example.coffeereview.entity.Review;
import com.example.coffeereview.repo.CategoryRepository;
import com.example.coffeereview.repo.CoffeeRepository;
import com.example.coffeereview.repo.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataLoader {

  @Bean
  CommandLineRunner seed(
          CoffeeRepository coffeeRepository,
          ReviewRepository reviewRepository,
          CategoryRepository categoryRepository
  ) {
    return args -> {
      if (coffeeRepository.count() > 0) return;

      // ---- CATEGORIES ----
      Category classic = categoryRepository.save(new Category("Classic"));
      Category milk = categoryRepository.save(new Category("Milk Based"));
      Category chocolate = categoryRepository.save(new Category("Chocolate"));
      Category traditional = categoryRepository.save(new Category("Traditional"));

      // ---- COFFEES ----
      Coffee espresso = coffeeRepository.save(new Coffee(
              "Espresso",
              "Strong and intense Italian coffee",
              new BigDecimal("25.00"),
              "https://images.unsplash.com/photo-1510591509098-f4fdc6d0ff04?w=400&q=80",
              classic
      ));

      Coffee cappuccino = coffeeRepository.save(new Coffee(
              "Cappuccino",
              "Espresso blended with milk foam",
              new BigDecimal("30.00"),
              "https://images.unsplash.com/photo-1561882468-9110e03e0f78?w=400&q=80",
              milk
      ));

      Coffee latte = coffeeRepository.save(new Coffee(
              "Latte",
              "Smooth and creamy milk coffee",
              new BigDecimal("35.00"),
              "https://images.unsplash.com/photo-1572442388796-11668a67e53d?w=400&q=80",
              milk
      ));

      Coffee americano = coffeeRepository.save(new Coffee(
              "Americano",
              "Espresso diluted with water",
              new BigDecimal("20.00"),
              "https://images.unsplash.com/photo-1551030173-122aabc4489c?w=400&q=80",
              classic
      ));

      Coffee mocha = coffeeRepository.save(new Coffee(
              "Mocha",
              "Chocolate and cream coffee",
              new BigDecimal("40.00"),
              "https://images.unsplash.com/photo-1607260550778-aa9d29444ce1?w=400&q=80",
              chocolate
      ));

      Coffee turk = coffeeRepository.save(new Coffee(
              "Turkish Coffee",
              "Traditional Turkish coffee",
              new BigDecimal("15.00"),
              "https://images.unsplash.com/photo-1610889556528-9a770e32642f?w=400&q=80",
              traditional
      ));

      // ---- REVIEWS ----
      reviewRepository.save(new Review("Ahmet Yılmaz", 5, "Harika bir kahve, çok lezzetli!", LocalDate.of(2024, 12, 20), espresso));
      reviewRepository.save(new Review("Zeynep Kaya", 4, "Güzel ama biraz sert geldi.", LocalDate.of(2024, 12, 22), espresso));
      reviewRepository.save(new Review("Mehmet Demir", 5, "Mükemmel köpük, süper lezzet!", LocalDate.of(2024, 12, 21), cappuccino));
      reviewRepository.save(new Review("Ayşe Çelik", 5, "En sevdiğim kahve, her zaman tercihim!", LocalDate.of(2024, 12, 23), latte));
      reviewRepository.save(new Review("Can Öztürk", 4, "Güzel ama biraz daha sıcak olabilirdi.", LocalDate.of(2024, 12, 24), latte));
      reviewRepository.save(new Review("Elif Yıldız", 5, "Çikolata seven herkes denemeli!", LocalDate.of(2024, 12, 25), mocha));
      reviewRepository.save(new Review("Mustafa Arslan", 5, "Geleneksel lezzet, kesinlikle deneyin!", LocalDate.of(2024, 12, 26), turk));
      reviewRepository.save(new Review("Fatma Şahin", 5, "Tek kelimeyle mükemmel!", LocalDate.of(2024, 12, 27), turk));
    };
  }
}
