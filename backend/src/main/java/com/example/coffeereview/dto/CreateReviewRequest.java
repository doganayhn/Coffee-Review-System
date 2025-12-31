package com.example.coffeereview.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateReviewRequest {

  @NotNull
  public Long coffeeId;

  @NotBlank
  @Size(max = 60)
  public String author;

  @Min(1)
  @Max(5)
  public int rating;

  @NotBlank
  @Size(max = 2000)
  public String comment;
}
