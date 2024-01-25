package com.ecommerce.urbanize.service;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

/**
 * Implementation of StorageService that stores and retrieves files on the
 * filesystem.
 */
@Service
public class FileSystemStorageService implements StorageService {

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;

    /**
     * Initializes the storage location on the filesystem.
     * Creates the root directory if it does not exist.
     */
    @Override
    @PostConstruct
    public void init() {
        try {
            rootLocation = Paths.get(mediaLocation);
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    /**
     * Stores the provided file on the filesystem.
     * 
     * @param file The file to be stored.
     * @return The filename of the stored file.
     * @throws RuntimeException If an error occurs during storage.
     */
    @Override
    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file");
        }
        String filename = file.getOriginalFilename();
        Path destinationFile = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    /**
     * Loads a file from the filesystem as a Resource.
     * 
     * @param filename The name of the file to be loaded.
     * @return The loaded file as a Resource.
     * @throws RuntimeException If an error occurs during file loading.
     */
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = this.rootLocation.resolve(filename).normalize().toAbsolutePath();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }
}