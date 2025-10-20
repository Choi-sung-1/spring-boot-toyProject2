package com.project.toyProject2.service;

import java.util.List;

public interface ImageService {
    List<String> findImagePaths(Long productId);
}
