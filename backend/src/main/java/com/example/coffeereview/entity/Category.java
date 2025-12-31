package com.example.coffeereview.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Coffee> coffees = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    // --- getters/setters ---

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoffees(List<Coffee> coffees) {
        this.coffees = coffees;
    }
}
