package com.project.toyProject2.service.image;

import java.util.List;

public interface ImageService {
    List<String> findImagePaths(String imageType,Long referenceId);
}
