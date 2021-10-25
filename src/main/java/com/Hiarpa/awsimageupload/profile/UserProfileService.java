package com.Hiarpa.awsimageupload.profile;

import com.Hiarpa.awsimageupload.bucket.BucketName;
import com.Hiarpa.awsimageupload.filestore.FileStore;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        userExistsById(userProfileId);

        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfileId);
        String filename = String.format("%s-%s", file.getName(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata),file.getInputStream());
        } catch (IOException e){
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Type", String.valueOf(file.getSize()));
        return metadata;
    }

    private void userExistsById(UUID userProfileId) {
        if(!userProfileDataAccessService.checkUserExistsById(userProfileId)){
            throw new IllegalStateException(String.format("User profile %s not found",userProfileId));
        }
    }

    private void isImage(MultipartFile file) {
        if(file.getContentType().equals(ContentType.IMAGE_PNG) || file.getContentType().equals(ContentType.IMAGE_PNG)|| file.getContentType().equals(ContentType.IMAGE_GIF)){
            throw new IllegalStateException("The file must be an image[" + file.getContentType() + "]");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()){
            throw new IllegalStateException("The file is empty");
        }
    }
}
