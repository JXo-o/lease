package com.jxh.lease.web.admin.service.impl;

import com.jxh.lease.common.minio.MinioProperties;
import com.jxh.lease.web.admin.service.FileService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private final MinioProperties minioProperties;
    private final MinioClient minioClient;

    public FileServiceImpl(MinioProperties minioProperties, MinioClient minioClient) {
        this.minioProperties = minioProperties;
        this.minioClient = minioClient;
    }

    @Override
    public String upload(MultipartFile file) throws Exception {
        boolean bucketExists = minioClient.bucketExists(
                BucketExistsArgs
                        .builder()
                        .bucket(minioProperties.getBucketName())
                        .build()
        );
        if (!bucketExists) {
            minioClient.makeBucket(
                    MakeBucketArgs
                            .builder()
                            .bucket(minioProperties.getBucketName())
                            .build()
            );
            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs
                            .builder()
                            .bucket(minioProperties.getBucketName())
                            .config(createBucketPolicyConfig(minioProperties.getBucketName()))
                            .build()
            );
        }
        String fileName = getFileName(file);
        minioClient.putObject(
                PutObjectArgs
                        .builder()
                        .bucket(minioProperties.getBucketName())
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );
        return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + fileName;

    }

    private String createBucketPolicyConfig(String bucketName) {
        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }

    private String getFileName(MultipartFile file) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date())
                + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
    }

}
