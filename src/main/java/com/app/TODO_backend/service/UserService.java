package com.app.TODO_backend.service;

import com.app.TODO_backend.repository.RoleRepository;
import com.app.TODO_backend.entity.User;
import com.app.TODO_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public Optional<User> findByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }
    public Optional<User> findByMail(String mail){ return userRepository.getUserByMailIgnoreCase(mail);}

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s not found", login)));
        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(),
                user.getRoles().stream().map(it -> new SimpleGrantedAuthority(it.getName())).toList());
    }

    public void createNewUser(User user){
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(user);
    }

    @Transactional
    public void confirmNewUser(String mail){
        User user = findByMail(mail).orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s not found", mail)));
        user.setActive(true);
        userRepository.save(user);
    }

    @Transactional
    public void changeUserPassword(User user, String password){
        log.warn("User: "+ user.getLogin() + "changed password to " + password);
        User changedUser = findByMail(user.getMail()).orElseThrow(() -> new UsernameNotFoundException(String.format("User: %s not found", user.getLogin())));
        changedUser.setPassword(password);
        userRepository.save(changedUser);
    }
}
