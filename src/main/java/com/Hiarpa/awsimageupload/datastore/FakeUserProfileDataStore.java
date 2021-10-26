package com.Hiarpa.awsimageupload.datastore;

import com.Hiarpa.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("93e4fe8d-b71d-4ee4-b325-f0574bc71b43"), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("8d287d85-a319-4946-a824-562fc812f072"), "antoniojunior", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }

    public boolean checkUserId(UUID userId){
        boolean exists = false;
        for(UserProfile index: getUserProfiles()){
            if(index.getUserProfileId() == userId){
                return exists = true;
            }
        }
        return exists;
    }


}
