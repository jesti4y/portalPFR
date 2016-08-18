package ru.portalPFR.dao;
import ru.portalPFR.model.User;

import java.util.List;

/**
 * Created by 048ChubakovaEL on 15.08.2016.
 */
public interface UserDao {

    User findById(int id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();

}