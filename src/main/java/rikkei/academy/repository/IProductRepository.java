package rikkei.academy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import rikkei.academy.model.Product;

public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
}
