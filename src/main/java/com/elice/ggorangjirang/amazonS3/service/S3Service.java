package com.elice.ggorangjirang.amazonS3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.product-image-path}")
    private String productImagePath;

    @Value("${cloud.aws.s3.review-image-path}")
    private String reviewImagePath;

    @Value("${cloud.aws.s3.description-image-path}")
    private String descriptionImagePath;

    private static final String BUCKET_PATH = "https://ggorangjirang-s3.s3.amazonaws.com/";

    public String uploadFile(MultipartFile imageFile, String imagePath) throws IOException {

        String fileName = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
        String filePath = imagePath + fileName;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(imageFile.getContentType());
        objectMetadata.setContentLength(imageFile.getSize());

        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucket, filePath, imageFile.getInputStream(), objectMetadata);

        // S3에 파일 업로드하기
        client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));

        String imageUrl = client.getUrl(bucket, filePath).toString();

        return imageUrl;
    }

    public String uploadProductImage(MultipartFile imageFile) throws IOException {
        return uploadFile(imageFile, productImagePath);
    }

    public String uploadReviewImage(MultipartFile imageFile) throws IOException {
        return uploadFile(imageFile, reviewImagePath);
    }

    public String uploadDescriptionImage(MultipartFile imageFile) throws IOException {
        return uploadFile(imageFile, descriptionImagePath);
    }

    public void deleteFile(String imageUrl) {
        if(imageUrl != null) {
            // 이미지 URL에서 버킷의 경로와 파일 이름 추출 및 버킷 경로 제거
            String fileKey = imageUrl.replace(BUCKET_PATH, "");

            // 파일 삭제
            client.deleteObject(bucket, fileKey);
        }
    }
}
