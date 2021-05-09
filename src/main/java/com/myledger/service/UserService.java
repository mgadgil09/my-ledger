package com.myledger.service;
import com.myledger.model.Enums.UserRole;
import com.myledger.model.User;
import com.myledger.model.Role;
import com.myledger.model.UserRegistration;
import com.myledger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public void saveUser(UserRegistration userRegistration){
        userRepository.save(new User(userRegistration.getFirstName(),
                userRegistration.getLastName(),
                userRegistration.getEmail(),
                passwordEncoder.encode(userRegistration.getPassword()),
                Arrays.asList(new Role(UserRole.USER.toString()))));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRoleToAuthority(user.getRoles()));
    }

    public User getUserByEmail(String email) throws UsernameNotFoundException{
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password");
        }
        return userRepository.findByEmail(email);
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthority(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
