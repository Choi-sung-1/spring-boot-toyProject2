package com.project.toyProject2.domain.vo;

import lombok.Data;

@Data
public class ImageVO {
    private Long imageId;
    private String imageOriginalName;
    private String imagePath;
    private String imageType;
    private Long referenceId;
    private String mainImage;
}
