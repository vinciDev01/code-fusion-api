package ipnet.gl.code_fusion_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByTrackingId(UUID trackingId);
    
    boolean existsByTrackingId(UUID trackingId);
    
    @Query("SELECT e FROM Role e WHERE e.nom = :roleName")
    Optional<Role> findByRole(@Param("roleName") String roleName);
    
    Optional<Role> findByNom(String nom);
    
    @Query("SELECT e FROM Role e WHERE e.trackingId = :roleTrackingId")
    List<Role> findAllByRoleTrackingId(UUID roleTrackingId);
    
    @Query("SELECT e FROM Role e WHERE LOWER(e.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY e.id DESC")
    List<Role> search(@Param("searchTerm") String searchTerm);

}