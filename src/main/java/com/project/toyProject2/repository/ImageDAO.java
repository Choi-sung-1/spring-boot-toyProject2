package com.project.toyProject2.repository;

import com.project.toyProject2.domain.vo.ImageVO;
import com.project.toyProject2.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ImageDAO {
    private final ImageMapper imageMapper;

    public void insert(ImageVO imageVO) {
        imageMapper.insert(imageVO);
    }
    public List<ImageVO> findImages(String imageType,Long referenceId){
        return imageMapper.selectImages(imageType,referenceId);
    }
    public void delete(String imageType, Long referenceId){
        imageMapper.delete(imageType,referenceId);
    }
}
