package com.project.toyProject2.service.image;

import com.project.toyProject2.domain.vo.ImageVO;
import com.project.toyProject2.repository.ImageDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageDAO imageDAO;
    @Override
    public List<String> findImagePaths(Long productId) {
        List<ImageVO> images = imageDAO.findImages(productId);
        List<String> imagePaths = new ArrayList<>();

        for (ImageVO image : images) {
            String imageName = image.getImageOriginalName();
            imagePaths.add(imageName);
        }
        return imagePaths;
    }

}
