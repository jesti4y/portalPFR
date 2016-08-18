package ru.portalPFR.dao;
import ru.portalPFR.model.UserProfile;

import java.util.List;

/**
 * Created by 048ChubakovaEL on 15.08.2016.
 */
public interface UserProfileDao {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(int id);
}
