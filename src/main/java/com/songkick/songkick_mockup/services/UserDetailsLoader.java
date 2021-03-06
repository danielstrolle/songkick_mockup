package com.songkick.songkick_mockup.services;


import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.models.UserWithRoles;
import com.songkick.songkick_mockup.repositories.UsersRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UsersRepository usersRepository;


    public UserDetailsLoader(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user);
    }
}
