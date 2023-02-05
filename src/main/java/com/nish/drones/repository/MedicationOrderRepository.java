package com.nish.drones.repository;

import com.nish.drones.repository.model.MedicationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, Long> {

    @Query(
            value = "SELECT * FROM medication_order m WHERE m.delivery_id = :deliveryId",
            nativeQuery = true)
    List<MedicationOrder> findByDeliveryId(Long deliveryId);

}


//@Query("SELECT DISTINCT(r) FROM JpaPerformanceReview r WHERE r.contractId IN :contractIds ORDER BY r.contractId")
//    List<JpaPerformanceReview> findByContractIds(final Collection<Long> contractIds);