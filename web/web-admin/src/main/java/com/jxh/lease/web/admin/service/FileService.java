package com.jxh.lease.web.admin.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String upload(MultipartFile file) throws Exception;
}
