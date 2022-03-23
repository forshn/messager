/*
package ru.forsh.sweater;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.forsh.sweater.controller.MainController;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
    // тестирование будет происходить в фейковом окружении. так проще и быстрее
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;

    @Test
    public void contextLoads() throws Exception {
        this.mockMvc.perform(get("/")) // выполняем переход на страницу /
                .andDo(print()) // распечатываем результат в консоль (вдруг ошибка будет)
                .andExpect(status().isOk()) // сравниваем ожидание с OK
                .andExpect(content().string(containsString("Hello, guest"))); // проверяем какое содержимое вренулось, сравниваем с ожидаемым
    }

    @Test
    public void accessDenied() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctLoginTest() throws Exception {
        this.mockMvc.perform(formLogin().user("1").password("u"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
        ;
    }

    @Test
    public void badCredentials() throws Exception {
        this.mockMvc.perform(post("/login").param("user", "alfred"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
*/
