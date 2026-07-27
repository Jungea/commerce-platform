package org.example.commerceplatform.product.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.product.api.dto.ProductRegisterRequest;
import org.example.commerceplatform.product.api.dto.ProductResponse;
import org.example.commerceplatform.product.application.ProductService;
import org.example.commerceplatform.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> register(@Valid @RequestBody ProductRegisterRequest request) {
        Product product = productService.register(request.name(), request.price(), request.stockQuantity());
        return ResponseEntity
                .created(URI.create("/products/" + product.getId()))
                .body(ProductResponse.from(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getList() {
        List<ProductResponse> products = productService.getList().stream()
                .map(ProductResponse::from)
                .toList();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(ProductResponse.from(productService.getOne(id)));
    }
}
