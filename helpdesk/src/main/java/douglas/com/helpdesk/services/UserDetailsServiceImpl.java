package douglas.com.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import douglas.com.helpdesk.models.Pessoa;
import douglas.com.helpdesk.repositories.PessoaRepository;
import douglas.com.helpdesk.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Optional<Pessoa> user = pessoaRepository.findByEmail(email);
       if(user.isPresent()) {
            Pessoa pessoa = user.get();
            System.out.println("[UserDetailsServiceImpl] Usuário encontrado: " + pessoa.getEmail());
            System.out.println("[UserDetailsServiceImpl] Senha criptografada no banco: " + pessoa.getSenha());
           return new UserSS(user.get().getId(),user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
       } else {
            System.out.println("[UserDetailsServiceImpl] Usuário NÃO encontrado: " + email);
           throw new UsernameNotFoundException("Email " + email + " não encontrado!");
       }
    }
}
