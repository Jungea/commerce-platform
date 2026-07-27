package org.example.commerceplatform.product.application;

import lombok.RequiredArgsConstructor;
import org.example.commerceplatform.common.exception.InvalidRequestException;
import org.example.commerceplatform.common.exception.NotFoundException;
import org.example.commerceplatform.product.domain.Product;
import org.example.commerceplatform.product.domain.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product register(String name, Long price, Integer stockQuantity) {
        if (stockQuantity < 0) {
            throw new InvalidRequestException("재고 수량은 0 이상이어야 합니다.");
        }
        return productRepository.save(new Product(name, price, stockQuantity));
    }

    public List<Product> getList() {
        return productRepository.findAll();
    }

    public Product getOne(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("상품을 찾을 수 없습니다. id=" + id));
    }
}
