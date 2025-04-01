package ipnet.gl.code_fusion_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    @Query("SELECT t FROM Transaction t WHERE t.createdBy =:x")
    List<Transaction> findTransactionByUser(@Param("x") String create);

    //montant total par type de transaction
    @Query("SELECT t.type, SUM(t.montant) FROM Transaction t GROUP BY t.type")
    List<Object[]> findTotalAmountByTransactionType();

    // Les transactions par type et selon des dates précises
    @Query("SELECT t FROM Transaction t WHERE t.type = :type AND t.date BETWEEN :startDate AND :endDate ORDER BY t.date DESC")
    List<Transaction> findTransactionsByTypeAndDateRange(@Param("type") String type,
                                                         @Param("startDate") LocalDateTime startDate,
                                                         @Param("endDate") LocalDateTime endDate);




}