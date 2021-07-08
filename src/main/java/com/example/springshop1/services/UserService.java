package com.example.springshop1.services;

import com.example.springshop1.repositories.RoleRepository;
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
import com.example.springshop1.repositories.UserRepository;

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
        User user = findByUserLogin(defaultUser.getLogin()).orElseThrow(() -> new UsernameNotFoundException(String.format("UserName of this User is null.")));
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
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = findByUserLogin(userLogin).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", userLogin)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Optional<User> findByUserLogin(String userLogin) {
        return userRepository.findOneByLogin(userLogin);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
