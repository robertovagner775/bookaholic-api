package com.bookaholic.backend.service;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookaholic.backend.model.ConfirmationToken;

import com.bookaholic.backend.model.Usuario;
import com.bookaholic.backend.repository.ConfirmationTokenRepository;
import com.bookaholic.backend.repository.RoleRepository;
import com.bookaholic.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {
    

    private UsuarioRepository repositorio;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    
    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> criarUsuario(Usuario usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
      
       
         if (repositorio.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        usuario.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        repositorio.save(usuario);

        ConfirmationToken confirmationToken = new ConfirmationToken(usuario);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(usuario.getEmail());
       
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("<h1> To confirm your account, please click here : </h1>"
                +"http://localhost:8080/usuario/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
        

         
    
       
    }

    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            Usuario usuario = repositorio.findByEmailQuery(token.getUsuario().getEmail());
            usuario.setEnabled(true);
            repositorio.save(usuario);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    public Usuario viewEmail(String email) {
        return repositorio.findByEmailQuery(email);
    }
    public Usuario viewUsername(String username) {
        return repositorio.findByUsername(username);
    }
 
    public Boolean validarSenha(Usuario usuario) {
        String senha = repositorio.getReferenceById(usuario.getId()).getSenha();
        String senha2 = usuario.getSenha();
       
        Boolean valid = this.passwordEncoder.matches(senha, senha2);
        return valid;
    }
    public String hash(String senha) {
        String cod = passwordEncoder.encode(senha);
        return cod; 
    }


}
