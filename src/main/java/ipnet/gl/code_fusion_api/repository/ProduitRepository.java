package ipnet.gl.code_fusion_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    
    Optional<Produit> findByTrackingId(UUID trackingId);
    
    boolean existsByTrackingId(UUID trackingId);
    
    
    List<Produit> findAllByMarqueterTrackingId(UUID marqueterTrackingId);
    List<Produit> findAllByCategorieTrackingId(UUID categorieTrackingId);
    
    @Query("SELECT e FROM Produit e WHERE LOWER(e.libelle) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY e.id DESC")
    List<Produit> search(@Param("searchTerm") String searchTerm);

}