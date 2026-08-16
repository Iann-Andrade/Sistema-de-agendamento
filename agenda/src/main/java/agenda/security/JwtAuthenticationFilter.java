package agenda.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import agenda.model.Usuario;
import agenda.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Libera requisições OPTIONS (Preflight do CORS enviado pelo navegador antes do DELETE)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }

        String authorization = request.getHeader("Authorization");
        System.out.println("Entrou no filtro: " + request.getMethod() + " " + request.getRequestURI());

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            if (jwtService.validarToken(token)) {
                try {
                    String usuarioId = jwtService.extrairUsuarioId(token);
                    System.out.println("Usuário autenticado ID: " + usuarioId);

                    Usuario usuario = usuarioRepository
                        .findById(Integer.valueOf(usuarioId))
                        .orElse(null);

                    if (usuario != null) {
                        UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                Collections.emptyList()
                            );

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar autenticação do usuário: " + e.getMessage());
                }
            } else {
                System.out.println("Token inválido para a requisição: " + request.getRequestURI());
            }
        }

        
        filterChain.doFilter(request, response);
        
        System.out.println("STATUS DEPOIS DO FILTER CHAIN: " + response.getStatus());
    }
}