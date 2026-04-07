package com.example.sentinel.controller;

import com.example.sentinel.model.Product;
import com.example.sentinel.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@Valid @RequestBody Product produto) {
        if (repository.existsBySku(produto.getSku())) {
            return ResponseEntity.status(409).body("Erro: Já existe um produto com o SKU " + produto.getSku());
        }
        return ResponseEntity.status(201).body(repository.save(produto));
    }

    @GetMapping
    public List<Product> listarProdutos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarProdutoPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Product produtoAtualizado) {
        return repository.findById(id)
                .map(produtoExistente -> {
                    if (!produtoExistente.getSku().equals(produtoAtualizado.getSku()) && repository.existsBySku(produtoAtualizado.getSku())) {
                        return ResponseEntity.status(409).body("Erro: SKU já em uso.");
                    }
                    produtoExistente.setName(produtoAtualizado.getName());
                    produtoExistente.setPrice(produtoAtualizado.getPrice());
                    produtoExistente.setQuantity(produtoAtualizado.getQuantity());
                    produtoExistente.setSku(produtoAtualizado.getSku());
                    return ResponseEntity.ok(repository.save(produtoExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}