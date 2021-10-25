package com.Hiarpa.awsimageupload.datastore;

import com.Hiarpa.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "antoniojunior", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }

    public boolean checkUserId(UUID userId){
        boolean exists = false;
        for(UserProfile index: USER_PROFILES){
            if(index.getUserProfileId() == userId){
                exists = true;
            }
        }
        return exists;
    }
}
