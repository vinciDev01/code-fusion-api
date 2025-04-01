package ipnet.gl.code_fusion_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.StationService;

@Repository
public interface StationServiceRepository extends JpaRepository<StationService, Long> {
    
    Optional<StationService> findByTrackingId(UUID trackingId);
    
    boolean existsByTrackingId(UUID trackingId);
    
    
    
    // Méthode de recherche par défaut si aucun champ recherchable n'est défini
    default List<StationService> search(String searchTerm) {
        return findAll();
    }

}