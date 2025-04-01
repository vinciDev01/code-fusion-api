package ipnet.gl.code_fusion_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ipnet.gl.code_fusion_api.entity.Role;
import ipnet.gl.code_fusion_api.repository.RoleRepository;
import ipnet.gl.code_fusion_api.utils.GlobalResponse;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Gestion des rôles", description = "API pour la gestion des rôles utilisateurs")
public class RoleController {
    
    private final RoleRepository roleRepository;
    
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @GetMapping
    @Operation(summary = "Liste tous les rôles", description = "Permet d'obtenir la liste de tous les rôles")
    public ResponseEntity<GlobalResponse<List<Role>>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(GlobalResponse.success("Liste des rôles récupérée avec succès", roles));
    }
    
    @GetMapping("/{trackingId}")
    @Operation(summary = "Récupère un rôle par son trackingId", description = "Permet d'obtenir les détails d'un rôle spécifique")
    @PreAuthorize("hasRole('DIRECTEUR')")
    public ResponseEntity<GlobalResponse<Role>> getRoleByTrackingId(@PathVariable UUID trackingId) {
        Role role = roleRepository.findByTrackingId(trackingId)
            .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
        return ResponseEntity.ok(GlobalResponse.success("Rôle récupéré avec succès", role));
    }
    
    @PostMapping
    @Operation(summary = "Créer un nouveau rôle", description = "Permet de créer un nouveau rôle (réservé au DIRECTEUR)")
    @PreAuthorize("hasRole('DIRECTEUR')")
    public ResponseEntity<GlobalResponse<Role>> createRole(@RequestBody Role role) {
        if (role.getTrackingId() == null) {
            role.setTrackingId(UUID.randomUUID());
        }
        Role savedRole = roleRepository.save(role);
        return ResponseEntity.ok(GlobalResponse.success("Rôle créé avec succès", savedRole));
    }
    
    @PutMapping("/{trackingId}")
    @Operation(summary = "Mettre à jour un rôle", description = "Permet de modifier un rôle existant (réservé au DIRECTEUR)")
    @PreAuthorize("hasRole('DIRECTEUR')")
    public ResponseEntity<GlobalResponse<Role>> updateRole(@PathVariable UUID trackingId, @RequestBody Role roleDetails) {
        Role role = roleRepository.findByTrackingId(trackingId)
            .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
        
        role.setNom(roleDetails.getNom());
        role.setPermission(roleDetails.getPermission());
        
        Role updatedRole = roleRepository.save(role);
        return ResponseEntity.ok(GlobalResponse.success("Rôle mis à jour avec succès", updatedRole));
    }
    
    @DeleteMapping("/{trackingId}")
    @Operation(summary = "Supprimer un rôle", description = "Permet de supprimer un rôle existant (réservé au DIRECTEUR)")
    @PreAuthorize("hasRole('DIRECTEUR')")
    public ResponseEntity<GlobalResponse<Void>> deleteRole(@PathVariable UUID trackingId) {
        Role role = roleRepository.findByTrackingId(trackingId)
            .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
        
        roleRepository.delete(role);
        return ResponseEntity.ok(GlobalResponse.success("Rôle supprimé avec succès", null));
    }
} 