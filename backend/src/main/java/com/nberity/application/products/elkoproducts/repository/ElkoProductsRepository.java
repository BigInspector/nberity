package com.nberity.application.products.elkoproducts.repository;

import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ElkoProductsRepository extends JpaRepository<ElkoProduct, Long> {

}
