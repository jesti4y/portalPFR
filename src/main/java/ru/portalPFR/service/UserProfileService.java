package ru.portalPFR.service;

import ru.portalPFR.model.UserProfile;
import java.util.List;


/**
 * Created by 048ChubakovaEL on 15.08.2016.
 */
public interface UserProfileService {

    UserProfile findById(int id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();

}