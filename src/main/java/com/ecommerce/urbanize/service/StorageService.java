package com.ecommerce.urbanize.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface StorageService {

    void init();

    String store(MultipartFile file);

    Resource loadAsResource(String filename);

}