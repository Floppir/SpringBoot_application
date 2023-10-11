package PP._1_2.service;

import PP._1_2.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> findById(long id);

    public List<User> findAll();

    public void save(User user);

    public void deleteById(Long id);

    public void update(User user);

}
