package ipnet.gl.code_fusion_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    
    Optional<Restaurant> findByTrackingId(UUID trackingId);
    
    boolean existsByTrackingId(UUID trackingId);
    
    
    
    // Méthode de recherche par défaut si aucun champ recherchable n'est défini
    default List<Restaurant> search(String searchTerm) {
        return findAll();
    }

    //Les points de vente par transaction, par type de transaction, classés par montant
    @Query("SELECT r, SUM(t.montant) FROM Transaction t " +
            "JOIN t.restaurant r " +
            "WHERE t.type = :type " +
            "GROUP BY r " +
            "ORDER BY SUM(t.montant) DESC")
    List<Restaurant> findSalesPointsByRestaurantOrderedByAmount(@Param("type") String type);


}