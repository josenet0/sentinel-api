package com.example.sentinel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O SKU não pode estar em branco") // Trava de texto
    private String sku;

    @Column(nullable = false)
    @NotBlank(message = "O Nome não pode estar em branco")
    private String name;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero") // Trava matemática
    private Double price;

    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    private Integer quantity;
}