package br.com.fiap.monitoramentoambiental.config.security;

import br.com.fiap.monitoramentoambiental.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class VerificarToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            String token = authorizationHeader.replace("Bearer ", "").trim();

            String login = tokenService.validarToken(token);

            if (login != null) {
                UserDetails usuario = usuarioRepository.findByEmail(login);

                if (usuario != null) {

                    if (usuario.isEnabled()) {

                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        usuario,
                                        null,
                                        usuario.getAuthorities()
                                );
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    } else {

                        throw new DisabledException("Usuário desativado: " + login);
                    }
                } else {

                    throw new UsernameNotFoundException("Usuário não encontrado com o email: " + login);
                }
            } else {

                throw new BadCredentialsException("Token inválido ou expirado");
            }
        }
        filterChain.doFilter(request, response);
    }

} //FIM
