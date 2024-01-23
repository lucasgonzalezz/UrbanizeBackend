package com.ecommerce.urbanize.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service interface defining operations for storing and retrieving files.
 */
@Service
public interface StorageService {

    /**
     * Initializes the storage service.
     */
    void init();

    /**
     * Stores the provided file.
     * 
     * @param file The file to be stored.
     * @return The filename of the stored file.
     */
    String store(MultipartFile file);

    /**
     * Loads a file as a Resource.
     * 
     * @param filename The name of the file to be loaded.
     * @return The loaded file as a Resource.
     */
    Resource loadAsResource(String filename);

}