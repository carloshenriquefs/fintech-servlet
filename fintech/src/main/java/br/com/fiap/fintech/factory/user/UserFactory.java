package br.com.fiap.fintech.factory.user;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.dao.user.impl.UserDaoImpl;
import br.com.fiap.fintech.model.User;
import br.com.fiap.fintech.model.builder.UserBuilder;

import java.time.LocalDate;

public class UserFactory {

    public static DaoAdapter<User> getUserDao() {
        return new UserDaoImpl();
    }

    public static User createUser() {
        return new UserBuilder()
                .setUsername("Carlos")
                .setLastName("Henrique")
                .setEmail("carlos@gmail.com")
                .setPassword("123456")
                .setAddress("Rua Alfredo Gonçalvez")
                .setPhone("1126563212")
                .setGender("Masculino")
                .setPosition("Pedreiro")
                .setDate(LocalDate.of(2000, 5,12))
                .build();
    }
}
