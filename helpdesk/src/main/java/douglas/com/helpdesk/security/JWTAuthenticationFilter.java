package douglas.com.helpdesk.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import douglas.com.helpdesk.dtos.CredenciaisDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/login"); // Define a URL que esse filtro irá interceptar ("/login")
        //setAuthenticationSuccessHandler(this); // Define o manipulador de sucesso da autenticação (opcional)
        System.out.println("[JWTAuthenticationFilter] Filtro configurado para URL /login");

    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
        System.out.println("[attemptAuthentication] Iniciando autenticação...");

        try {
            CredenciaisDTO cred = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
            System.out.println("[attemptAuthentication] Credenciais recebidas: " + cred.getEmail());
            System.out.println("[attemptAuthentication] Tentando autenticar com email: " + cred.getEmail() + " e senha: " + cred.getSenha());

            if (cred.getEmail() == null || cred.getSenha() == null) {
                System.out.println("[attemptAuthentication] Email ou senha não informados!");
                throw new BadCredentialsException("Email ou senha não informados!");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getSenha());
            System.out.println("[attemptAuthentication] Token criado: " + authenticationToken);
            System.out.println("Detalhes do Token:");
            System.out.println("- Principal: " + authenticationToken.getPrincipal());
            System.out.println("- Credentials: " + authenticationToken.getCredentials());
            System.out.println("- Authenticated: " + authenticationToken.isAuthenticated());
            // 3. Tentar autenticar
            System.out.println("[attemptAuthentication] Chamando authenticationManager.authenticate()");

            try{
                Authentication auth = authenticationManager.authenticate(authenticationToken);
                System.out.println("[attemptAuthentication] Autenticação bem-sucedida");
                System.out.println("[attemptAuthentication] Detalhes da autenticação: " + auth);
                System.out.println("[attemptAuthentication] Principal: " + auth.getPrincipal());
                System.out.println("[attemptAuthentication] Credentials: " + auth.getCredentials());
                System.out.println("[attemptAuthentication] Authenticated: " + auth.isAuthenticated());
                System.out.println("[attemptAuthentication] Autenticação concluída com sucesso!");
                return auth;
            }catch (AuthenticationException e) {
                System.out.println("[attemptAuthentication] Erro de autenticação: " + e.getMessage());
                if (e instanceof UsernameNotFoundException) {
                    System.out.println("[attemptAuthentication] Usuário não encontrado: " + e.getMessage());
                }else if (e instanceof BadCredentialsException) {
                    System.out.println("[attemptAuthentication] Senha incorreta ou problema na comparação");
                }
            throw e;

            }
        } catch (IOException e) {
            System.out.println("[attemptAuthentication] Erro ao ler as credenciais: " + e.getMessage());
            throw new RuntimeException(e);
        }catch (AuthenticationException e) {
            System.out.println("[attemptAuthentication] Erro de autenticação: " + e.getMessage());
            throw e;
        }
    }
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
        Authentication authResult) throws IOException, ServletException {
        String username = ((UserSS) authResult.getPrincipal()).getUsername();    
        String token = jwtUtil.generateToken(username);
        response.setHeader("Authorization", "Bearer " + token); // Adiciona o token no cabeçalho da resposta
        response.setHeader("access-control-expose-headers", "Authorization"); // Permite que o cabeçalho Authorization seja acessado pelo cliente
        System.out.println("Token gerado: " + token); // Adiciona uma mensagem de log com o token gerado
    }
    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json"); // Define o tipo de conteúdo da resposta como JSON
        response.getWriter().write("Email ou senha inválidos!"); // Adiciona uma mensagem de erro no corpo da resposta
        response.getWriter().flush(); // Limpa o buffer de saída
    }
    public CharSequence json(){
        long date = new java.util.Date().getTime();
        return "{\"timestamp\": " + date + ", \"status\": 401, \"error\": \"Não autorizado\", \"message\": \"Email ou senha inválidos!\", \"path\": \"/login\"}";
    }
}
