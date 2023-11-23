package com.bookaholic.backend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookaholic.backend.model.Privilege;
import com.bookaholic.backend.model.Role;
import com.bookaholic.backend.model.Usuario;
import com.bookaholic.backend.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       Usuario usuario = usuarioRepository.findByEmailQuery(username);
         if(usuario == null) {//here you can check that
             throw new UsernameNotFoundException("user not found");
            
        } else if (usuario.isEnabled() ==  false){
            
            throw new DisabledException("user not enabled");
        } 
        else {
            return usuario; 
            //User(usuario.getEmail(), usuario.getSenha(), usuario.isEnabled(), true, true,true,   getAuthorities(usuario.getRoles()));
        }
       
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
 
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
 
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}