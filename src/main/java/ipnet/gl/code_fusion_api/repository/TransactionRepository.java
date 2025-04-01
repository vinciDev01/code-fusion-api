package ipnet.gl.code_fusion_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    Optional<Transaction> findByTrackingId(UUID trackingId);
    
    boolean existsByTrackingId(UUID trackingId);
    
    
    List<Transaction> findAllByStationServiceTrackingId(UUID stationserviceTrackingId);
    List<Transaction> findAllByRestaurantTrackingId(UUID restaurantTrackingId);
    List<Transaction> findAllByBoutiqueTrackingId(UUID boutiqueTrackingId);
    List<Transaction> findAllByMarqueteurTrackingId(UUID marqueteurTrackingId);
    
    @Query("SELECT e FROM Transaction e WHERE LOWER(e.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY e.id DESC")
    List<Transaction> search(@Param("searchTerm") String searchTerm);

}