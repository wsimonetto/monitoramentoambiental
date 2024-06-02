package br.com.fiap.monitoramentoambiental.config.security;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(
            HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //USUÁRIO REGISTRO/LOGIN
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        //USUÁRIO
                        .requestMatchers(HttpMethod.GET, "/api/usuarios").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios").hasRole("ADMIN")
                        //ALERTAS
                        .requestMatchers(HttpMethod.GET, "/api/alertas").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/alertas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/alertas").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/alertas").hasRole("ADMIN")
                        //CONTROLES DE IRRIGAÇÃO
                        .requestMatchers(HttpMethod.GET, "/api/controle-irrigacao").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/controle-irrigacao").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/controle-irrigacao").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/controle-irrigacao").hasRole("ADMIN")
                        //LEITURAS
                        .requestMatchers(HttpMethod.GET, "/api/leituras").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/leituras").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/leituras").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/leituras").hasRole("ADMIN")
                        //PREVISÕES DE CHUVA
                        .requestMatchers(HttpMethod.GET, "/api/previsoes-chuva").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/previsoes-chuva").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/previsoes-chuva").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/previsoes-chuva").hasRole("ADMIN")
                        //SENSORES
                        .requestMatchers(HttpMethod.GET, "/api/sensores").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/sensores").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/sensores").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/sensores").hasRole("ADMIN")

                        .anyRequest()
                        .authenticated())
                .addFilterBefore(
                        (Filter) verificarToken, UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

} //FIM