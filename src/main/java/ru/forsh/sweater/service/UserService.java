package ru.forsh.sweater.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.forsh.sweater.domain.Role;
import ru.forsh.sweater.domain.User;
import ru.forsh.sweater.repository.UserRepo;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final MailSenderService mailSenderService;

    public UserService(UserRepo userRepo, MailSenderService mailSenderService) {
        this.userRepo = userRepo;
        this.mailSenderService = mailSenderService;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {

        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb !=null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Sweater. Please follow the link http://localhost:8080/activate/%s",
                    user.getUsername(), user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(), "activation code", message);
        }

        return true;
    }
}
