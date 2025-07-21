package com.ra.ss9.controller;

import com.ra.ss9.model.dto.request.ImageDTO;
import com.ra.ss9.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class ImageUploadController {

    private final CloudinaryService cloudinaryService;
    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @PostMapping
    public String uploadImage(@ModelAttribute ImageDTO image) {
        logger.info("Nhận yêu cầu upload ảnh với tên: {}", image.getFile());

        if (image.getFile().isEmpty()) {
            logger.warn("Ảnh không tồn tại hoặc rỗng.");
            return "Vui lòng chọn ảnh để upload.";
        }

        String imageUrl = cloudinaryService.uploadImage(image.getFile());
        return "Upload thành công. Ảnh của bạn: " + imageUrl;
    }
}
;