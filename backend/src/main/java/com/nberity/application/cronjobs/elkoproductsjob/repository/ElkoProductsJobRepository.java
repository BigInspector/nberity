package com.nberity.application.cronjobs.elkoproductsjob.repository;

import com.nberity.application.cronjobs.elkoproductsjob.entity.ElkoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ElkoProductsJobRepository extends JpaRepository<ElkoProduct, Long> {
}
