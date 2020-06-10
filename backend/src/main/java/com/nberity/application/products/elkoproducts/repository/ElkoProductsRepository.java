package com.nberity.application.products.elkoproducts.repository;

import com.nberity.application.products.elkoproducts.entity.ElkoProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ElkoProductsRepository extends JpaRepository<ElkoProduct, Long> {

    @Query(value = "SELECT elpr.version_nr FROM elko_product elpr ORDER BY elpr.version_nr DESC LIMIT 1", nativeQuery = true)
    Integer getTopVersionNumber();

    @Query(value = "DELETE FROM elko_product elpr WHERE elpr.version_nr < ( SELECT MAX(version_nr) FROM " +
            "elko_product ) - 1", nativeQuery = true)
    void deleteOldVersionNumbers();

    @Query(value = "SELECT elko_code FROM elko_product elpr WHERE elpr.version_nr = (SELECT MAX(version_nr) FROM " +
            "elko_product)", nativeQuery = true)
    List<Long> getAllLatestProductsElkoCodes();

    @Query(value = "SELECT 1", nativeQuery = true)
    int getTestNumber();

    @Modifying
    @Query(value = "UPDATE elko_product SET unit_net_weight = :unitNetWeight, unit_net_weight_measure_unit = :unitNetWeightMeasureUnit" +
            " WHERE elko_code = :elkoCode", nativeQuery = true)
    int updateProductNetWeight(@Param("unitNetWeight") String unitNetWeight, @Param("unitNetWeightMeasureUnit") String unitNetWeightMeasureUnit,
                                @Param("elkoCode") Long elkoCode);

    @Modifying
    @Query(value = "UPDATE elko_product SET unit_gross_weight = :unitGrossWeight, unit_gross_weight_measure_unit = :unitGrossWeightMeasureUnit" +
            " WHERE elko_code = :elkoCode", nativeQuery = true)
    int updateProductGrossWeight(@Param("unitGrossWeight") String unitGrossWeight, @Param("unitGrossWeightMeasureUnit") String unitGrossWeightMeasureUnit,
                                @Param("elkoCode") Long elkoCode);

    @Modifying
    @Query(value = "UPDATE elko_product SET shipping_box_height = :shippingBoxHeight, shipping_box_height_measure_unit = :shippingBoxHeightMeasureUnit" +
            " WHERE elko_code = :elkoCode", nativeQuery = true)
    int updateShippingBoxHeight(@Param("shippingBoxHeight") String shippingBoxHeight, @Param("shippingBoxHeightMeasureUnit") String shippingBoxHeightMeasureUnit,
                                  @Param("elkoCode") Long elkoCode);

    @Modifying
    @Query(value = "UPDATE elko_product SET shipping_box_width = :shippingBoxWidth, shipping_box_width_measure_unit = :shippingBoxWidthMeasureUnit" +
            " WHERE elko_code = :elkoCode", nativeQuery = true)
    int updateShippingBoxWidth(@Param("shippingBoxWidth") String shippingBoxWidth, @Param("shippingBoxWidthMeasureUnit") String shippingBoxWidthMeasureUnit,
                                 @Param("elkoCode") Long elkoCode);

    @Modifying
    @Query(value = "UPDATE elko_product SET shipping_box_depth = :shippingBoxDepth, shipping_box_depth_measure_unit = :shippingBoxDepthMeasureUnit" +
            " WHERE elko_code = :elkoCode", nativeQuery = true)
    int updateShippingBoxDepth(@Param("shippingBoxDepth") String shippingBoxDepth, @Param("shippingBoxDepthMeasureUnit") String shippingBoxDepthMeasureUnit,
                                @Param("elkoCode") Long elkoCode);

    @Modifying
    @Query(value = "UPDATE elko_product SET shipping_box_weight = :shippingBoxWeight, shipping_box_weight_measure_unit = :shippingBoxWeightMeasureUnit" +
            " WHERE elko_code = :elkoCode", nativeQuery = true)
    int updateShippingBoxWeight(@Param("shippingBoxWeight") String shippingBoxWeight, @Param("shippingBoxWeightMeasureUnit") String shippingBoxWeightMeasureUnit,
                                @Param("elkoCode") Long elkoCode);

}
