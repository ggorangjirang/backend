package com.elice.ggorangjirang.amazonS3.service;

import com.amazonaws.services.s3.AmazonS3Client;
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
    private final AmazonS3Client client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile imageFile) throws IOException {

        String fileName = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(imageFile.getContentType());
        objectMetadata.setContentLength(imageFile.getSize());

        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucket, fileName, imageFile.getInputStream(), objectMetadata);

        // S3에 파일 업로드하기
        client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));

        String imageUrl = client.getUrl(bucket, fileName).toString();

        return imageUrl;
    }
}
