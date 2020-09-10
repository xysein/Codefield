package dao;

import model.User;

import java.util.Optional;

public interface UserDao extends Dao {

    Optional<User> getById(int id);

}
