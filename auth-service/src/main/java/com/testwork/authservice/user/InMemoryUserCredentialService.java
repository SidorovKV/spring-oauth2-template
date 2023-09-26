package com.testwork.authservice.user;

import com.testwork.authservice.Role.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InMemoryUserCredentialService implements UserDetailsService, UserCredentialService {
    private final Map<String, UserCredential> userCredentialRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public InMemoryUserCredentialService() {
        this.userCredentialRepository = new HashMap<>();

        //<-- Just for to show example. Don't copy that.
        UserCredential admin = new UserCredential();
        admin.setEmail("admin@admin.com");
        admin.setRoles(List.of(Role.ADMIN, Role.USER));
        admin.setPassword(passwordEncoder.encode("admin"));
        userCredentialRepository.put(admin.getEmail(), admin);

        UserCredential user = new UserCredential();
        user.setEmail("user@user.com");
        user.setRoles(List.of(Role.USER));
        user.setPassword(passwordEncoder.encode("user"));
        userCredentialRepository.put(user.getEmail(), user);
        //-->
    }

    @Override
    public void add(UserCredentialDto newUserCredentialDto) {
        if (userCredentialRepository.containsKey(newUserCredentialDto.getEmail())) {
            throw new IllegalArgumentException("Email already registered to another account.");
        }
        UserCredential userCredential = Mapper.toUserCredential(newUserCredentialDto);
        Role role = Role.USER;
        userCredential.setRoles(List.of(role));
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.put(newUserCredentialDto.getEmail(), userCredential);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.get(email);
        if (userCredential == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        Set<SimpleGrantedAuthority> authorities = userCredential.getRoles().stream().map(Role::getAuthority)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new User(
                userCredential.getEmail(),
                userCredential.getPassword(),
                authorities
        );
    }
}
