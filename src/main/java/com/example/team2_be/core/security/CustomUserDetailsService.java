package com.example.team2_be.core.security;

import com.example.team2_be.user.User;
import com.example.team2_be.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserJPARepository userJPARepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User findUser = userJPARepository.findByEmail(email);

        if (findUser == null) {
            return null;
        } else {
            return new CustomUserDetails(findUser);
        }
    }
}


