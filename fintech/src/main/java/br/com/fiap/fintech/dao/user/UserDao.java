package br.com.fiap.fintech.dao.user;

import br.com.fiap.fintech.dao.DaoAdapter;
import br.com.fiap.fintech.model.Login;
import br.com.fiap.fintech.model.User;

public interface UserDao extends DaoAdapter<User> {

    boolean validateUser(Login login);
}
