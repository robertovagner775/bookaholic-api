package com.bookaholic.backend.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookaholic.backend.config.TokenService;
import com.bookaholic.backend.dto.TokenDTO;
import com.bookaholic.backend.model.ErrorResponse;
import com.bookaholic.backend.model.Leitor;
import com.bookaholic.backend.model.LeitorDto;
import com.bookaholic.backend.model.LoginDto;
import com.bookaholic.backend.model.Usuario;
import com.bookaholic.backend.repository.LeitorRepository;
import com.bookaholic.backend.repository.UsuarioRepository;

import com.bookaholic.backend.service.UsuarioService;

import lombok.Getter;
@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class Login  {

    @Autowired
    private AuthenticationManager authenticateManager;

    @Autowired
    private TokenService tokenService;
    
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LeitorRepository leitorRepository;

    @GetMapping
    public List<Usuario> viewUsuario() {
        return usuarioRepository.findAll();
    }

   

    @PostMapping
    public ResponseEntity<?>  addUsuario(@RequestBody  Usuario usuario){
       /*  if(usuarioRepository.findByEmail(usuario.getEmail()) != null || 
        usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            return ResponseEntity.status(200).body( new ErrorResponse(200, "error - inserir usuario", "username ou email já existe"));
        }
      
        return ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario, null));
        */
        return usuarioService.criarUsuario(usuario);
    }

    @PostMapping("/cliente")
    public ResponseEntity<?> cadastrarCliente(LeitorDto leitordto) {
        Usuario usuario = usuarioRepository.findById(leitordto.idUser()).get();
        Leitor leitor = new Leitor(leitordto.RG(), leitordto.CPF(), leitordto.nome_completo(), leitordto.data_nascimento(), usuario);
        return ResponseEntity.ok().body(leitorRepository.save(leitor));
    }

    @GetMapping(value="/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        return usuarioService.confirmEmail(confirmationToken);
    }


    @PostMapping("/login") 
    public ResponseEntity<?> login(@RequestBody  LoginDto login){
        try{
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.email(), login.senha());
            Authentication authenticate = this.authenticateManager.authenticate(usernamePasswordAuthenticationToken);
            var usuario = (Usuario) authenticate.getPrincipal();
            

            var token = tokenService.generateToken(usuario);
            String position;
            if(usuario.getAuthorities().toString().equals("[ROLE_LEITOR]")) {
                position = "LEITOR";
            } else if(usuario.getAuthorities().toString().equals("[ROLE_USER]")) {
                position = "USER";
            } else {
                position = "ADMIN";
            }
            
            TokenDTO tokenDto = new TokenDTO(usuario.getId() , usuario.getName(),token,  position);
           
            return ResponseEntity.status(200).body(tokenDto);
        }catch(UsernameNotFoundException text){
            return ResponseEntity.status(200).body(new ErrorResponse(401, "NOT-FOUND", ""+text.getMessage()));
        }
        catch (BadCredentialsException ex) {
            return ResponseEntity.status(200).body(new ErrorResponse(401, "NOT-FOUND", ""+ex.getMessage()));
        } catch (LockedException ex) {
            return ResponseEntity.status(200).body(new ErrorResponse(401, "Erro: Conta Bloqueada", ""+ex.getMessage()));
        } catch (DisabledException ex) {
            return ResponseEntity.status(200).body(new ErrorResponse(401, "CONFIRM-ACCOUNT", ""+ex.getMessage()));
        }  catch (AuthenticationException ex) {
            return ResponseEntity.status(200).body(new ErrorResponse(401, "NOT-AUTHORIZATION", ""+ex.getMessage()));
        }
      
    }



}
