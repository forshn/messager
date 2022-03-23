package ru.forsh.sweater.service;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.forsh.sweater.domain.Role;
import ru.forsh.sweater.domain.User;
import ru.forsh.sweater.repository.UserRepo;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService service;
    @MockBean
    private UserRepo userRepo;
    @MockBean
    private MailSenderService mailSender;
    @MockBean
    private PasswordEncoder passwordEncoder;


    /*тест падает потому что для создания юзера, нужно также создавать пароль, почту, и тд.
    это делает интеграционное тестирование. В юнит тестах это не нужно. Для того, чтобы
    данный тест не падал, нужно использовать Mock(фреймворк Moquito)
     для этого создаём новые поля с аннотацией @MockBean
     */
    @Test
    void addUser() {
        User user = new User();
        user.setEmail("some@mail.ru");

        boolean isCreated = service.addUser(user);

        assertTrue(isCreated);
        assertNotNull(user.getActivationCode());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(mailSender, Mockito
                        .times(1))
                .send(ArgumentMatchers.eq(user.getEmail()), ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString());

    }

    @Test
    void addUserFailTest() {
        User user = new User();

        user.setUsername("John");

        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("John");

        boolean isUserCreated = service.addUser(user);

        assertFalse(isUserCreated);
        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        Mockito.verify(mailSender, Mockito.times(0))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    @Test
    public void activateUser() {
        User user = new User();

        user.setActivationCode("bingo!");

        Mockito.doReturn(user)
                .when(userRepo)
                .findByActivationCode("activate");

        boolean isUserActivated = service.activateUser("activate");

        Assert.assertTrue(isUserActivated);
        Assert.assertNull(user.getActivationCode());

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = service.activateUser("activate me");

        Assert.assertFalse(isUserActivated);

        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }
}