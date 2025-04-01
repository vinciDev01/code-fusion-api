package ipnet.gl.code_fusion_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

import ipnet.gl.code_fusion_api.entity.User;
import ipnet.gl.code_fusion_api.entity.VerificationCode;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    
    Optional<VerificationCode> findByCode(String code);
    
    Optional<VerificationCode> findByTrackingId(UUID trackingId);
    
    Optional<VerificationCode> findByUserAndVerifiedFalse(User user);
    
    Optional<VerificationCode> findByCodeAndUserEmail(String code, String email);
    
    boolean existsByUserAndVerifiedFalse(User user);
    
    void deleteByUser(User user);
} 