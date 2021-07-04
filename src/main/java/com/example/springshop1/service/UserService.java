package com.example.springshop1.service;

import com.example.springshop1.repository.RoleRepository;
import com.example.springshop1.utils.DefaultUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.springshop1.models.Role;
import com.example.springshop1.models.User;
import com.example.springshop1.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public boolean save(DefaultUser defaultUser) {
        User user = findByUsername(defaultUser.getLogin()).orElseThrow(() -> new UsernameNotFoundException(String.format("UserName of this User is null.")));
        user.setLogin(defaultUser.getLogin());
        user.setPassword(passwordEncoder.encode(defaultUser.getPassword()));
        user.setFirstName(defaultUser.getFirstName());
        user.setLastName(defaultUser.getLastName());
        user.setEmail(defaultUser.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findOneByName("ROLE_CLIENT").get()));
        userRepository.save(user);
        return true;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Optional<User> findByUsername(String username) {
        return userRepository.findOneByUserName(username);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
