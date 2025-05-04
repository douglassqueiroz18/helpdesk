package douglas.com.helpdesk.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Classe responsável por interceptar as requisições HTTP e verificar a validade do token JWT
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    
    // Instância da classe responsável por manipular tokens JWT
    private JWTUtil jwtUtil;
    // Instância do serviço que carrega os detalhes do usuário
    // (usado para autenticação e autorização)
    private UserDetailsService userDetailsService;
    
    // Construtor que recebe o AuthenticationManager, a classe utilitária do JWT e o serviço de usuários
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // Método sobrescrito que intercepta todas as requisições e aplica o filtro de autenticação
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        
        // Recupera o valor do cabeçalho "Authorization" da requisição
        String header = request.getHeader("Authorization");
        
        // Verifica se o cabeçalho está presente e começa com "Bearer "
        if (header != null && header.startsWith("Bearer ")) {
            
            // Remove o prefixo "Bearer " do token e tenta autenticar o usuário
            UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));
            
            // Se o token for válido, define a autenticação no contexto de segurança do Spring
            if (authToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continua a execução da cadeia de filtros normalmente
        chain.doFilter(request, response);

    }
    // Método auxiliar para validar o token e retornar um objeto de autenticação do Spring
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        // Verifica se o token é válido
        if(jwtUtil.isTokenValid(token)) {
            // Extrai o nome de usuário do token
            String username = jwtUtil.getUsername(token);
            // Carrega os detalhes do usuário a partir do nome extraído
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Cria um objeto de autenticação contendo os detalhes e permissões do usuário
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        // Retorna null caso o token seja inválido
        return null;
    }

}
