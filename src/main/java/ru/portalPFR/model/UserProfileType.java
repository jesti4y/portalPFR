package ru.portalPFR.model;
import java.io.Serializable;
/**
 * Created by 048ChubakovaEL on 15.08.2016.
 */
public enum UserProfileType implements Serializable{
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }

}
