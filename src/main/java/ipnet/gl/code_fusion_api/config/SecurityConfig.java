package ipnet.gl.code_fusion_api.config;

import ipnet.gl.code_fusion_api.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                // Endpoints publics
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/","/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                
                // Endpoints réservés au DIRECTEUR
                .requestMatchers("/api/users/**").hasRole("DIRECTEUR")
                .requestMatchers("/api/roles/**").hasRole("DIRECTEUR")
                
                // Gestion des produits
                .requestMatchers(HttpMethod.GET, "/api/produits/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/produits/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/produits/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/produits/**").hasAnyRole("DIRECTEUR", "ADMIN")
                
                // Gestion des catégories
                .requestMatchers(HttpMethod.GET, "/api/categories/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/categories/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasAnyRole("DIRECTEUR", "ADMIN")
                
                // Gestion des marqueteurs
                .requestMatchers(HttpMethod.GET, "/api/marqueteurs/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/marqueteurs/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/marqueteurs/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/marqueteurs/**").hasAnyRole("DIRECTEUR", "ADMIN")
                
                // Gestion des boutiques
                .requestMatchers(HttpMethod.GET, "/api/boutiques/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/boutiques/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/boutiques/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/boutiques/**").hasAnyRole("DIRECTEUR", "ADMIN")
                
                // Gestion des stations service
                .requestMatchers(HttpMethod.GET, "/api/stationservices/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/stationservices/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/stationservices/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/stationservices/**").hasAnyRole("DIRECTEUR", "ADMIN")
                
                // Gestion des restaurants
                .requestMatchers(HttpMethod.GET, "/api/restaurants/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/restaurants/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/restaurants/**").hasAnyRole("DIRECTEUR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/restaurants/**").hasAnyRole("DIRECTEUR", "ADMIN")
                
                // Transactions - lecture pour tous les utilisateurs authentifiés
                .requestMatchers(HttpMethod.GET, "/api/transactions/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/transactions/**").hasAnyRole("DIRECTEUR", "ADMIN", "GERANT")
                .requestMatchers(HttpMethod.PUT, "/api/transactions/**").hasAnyRole("DIRECTEUR", "ADMIN", "GERANT")
                .requestMatchers(HttpMethod.DELETE, "/api/transactions/**").hasRole("DIRECTEUR")
                
                // Par défaut, toute autre requête nécessite une authentification
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
