package com.project.toyProject2.service;

import com.project.toyProject2.domain.vo.ImageVO;

import java.util.List;

public interface ImageService {
    List<String> findImagePaths(Long productId);
}
