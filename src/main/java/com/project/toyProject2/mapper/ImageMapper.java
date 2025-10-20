package com.project.toyProject2.mapper;

import com.project.toyProject2.domain.vo.ImageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    public void insert(ImageVO imageVO);
    public List<ImageVO> selectImages(Long productId);
    public void delete(Long productId);
}
