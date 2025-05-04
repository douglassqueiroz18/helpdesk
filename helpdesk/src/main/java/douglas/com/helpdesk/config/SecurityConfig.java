package douglas.com.helpdesk.config;

import java.util.Arrays;

import org.springframework.core.env.Environment;
import douglas.com.helpdesk.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// Removed import for WebSecurityConfigurerAdapter as it is no longer used
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import douglas.com.helpdesk.security.JWTAuthenticationFilter;
import douglas.com.helpdesk.security.JWTAuthorizationFilter;
import douglas.com.helpdesk.security.JWTUtil;
@EnableWebSecurity
@Configuration // Define essa classe como uma classe de configuração
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    // Injeta o ambiente do Spring para verificar perfis ativos (ex: "test")
    @Lazy
    @Autowired
    private Environment env;

    // Utilitário para manipulação de JWT (token)
    @Lazy
    @Autowired
    private JWTUtil jwtUtil;

    // Serviço responsável por buscar os detalhes do usuário (usado na autenticação)
    @Autowired
    private UserDetailsService userDetailsService;


    SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = 
        http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());

    return authenticationManagerBuilder.build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        // Configuração do AuthenticationManager já foi gerada automaticamente pelo Spring
        // Configurações do http
        boolean isTestEnv = Arrays.asList(env.getActiveProfiles()).contains("test");
        if (isTestEnv) {
            http.headers(headers -> headers.frameOptions().disable());
        }

        // Configurações principais
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .addFilter(new JWTAuthenticationFilter(authManager, jwtUtil))
            .addFilter(new JWTAuthorizationFilter(authManager, jwtUtil, userDetailsService));
        // Apenas uma chamada para build()
        return http.build();
    }
    // Removed redundant authenticationManager bean to avoid multiple instances
    

    // Método alternativo de configuração global da autenticação
    /* 
      @Autowired
      public void configureGlobal(AuthenticationManagerBuilder auth) throws
      Exception {
      System.out.println("Entrou no método configureGlobal");
      
      auth.userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder());
      }
     */

    // Configura as permissões de CORS para a aplicação (permite qualquer origem,
    // cabeçalho e método)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowCredentials(true);// Permite envio de credenciais
        config.addAllowedOrigin("*"); // Permite qualquer origem
        config.addAllowedHeader("*"); // Permite qualquer cabeçalho
        config.addAllowedMethod("*"); // Permite qualquer método (GET, POST, etc)
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}