package com.nberity.application.cronjobs.elkoproductsjob.repository;

import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ElkoProductsJobRepository extends JpaRepository<ElkoProduct, Long> {
}
