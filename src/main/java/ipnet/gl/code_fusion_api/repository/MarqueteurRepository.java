package ipnet.gl.code_fusion_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.Marqueteur;

@Repository
public interface MarqueteurRepository extends JpaRepository<Marqueteur, Long> {
    
    Optional<Marqueteur> findByTrackingId(UUID trackingId);
    
    boolean existsByTrackingId(UUID trackingId);
    
    
    
    @Query("SELECT e FROM Marqueteur e WHERE LOWER(e.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY e.id DESC")
    List<Marqueteur> search(@Param("searchTerm") String searchTerm);

}