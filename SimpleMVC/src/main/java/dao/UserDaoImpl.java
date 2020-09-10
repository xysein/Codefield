package dao;

import model.User;

import javax.sql.ConnectionPoolDataSource;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    // private static final DataSource dataSource = PooledDataS



    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional get(int id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }
}
