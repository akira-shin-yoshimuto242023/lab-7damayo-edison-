package com.damayo.lab_7;

<<<<<<< HEAD
import jakarta.persistence.*;
=======
>>>>>>> 3a1a4503e4a3b982f5248a8d9d40b628ed7643be
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
=======
public class Product {
    private Long id;
    private String name;
>>>>>>> 3a1a4503e4a3b982f5248a8d9d40b628ed7643be
    private Double price;
}