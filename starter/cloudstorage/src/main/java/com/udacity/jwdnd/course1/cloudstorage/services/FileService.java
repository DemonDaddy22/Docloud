package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean fileAlreadyExists(File file) {
        List<File> duplicateFiles = this.fileMapper.getDuplicateFiles(file);
        return duplicateFiles != null && !duplicateFiles.isEmpty();
    }

    public List<File> getFiles(Integer userId) {
        return this.fileMapper.getFiles(userId);
    }

    public File getFileById(Integer fileId) {
        return this.fileMapper.getFileById(fileId);
    }

    public int addFile(File file) {
        return this.fileMapper.addFile(file);
    }

    public int deleteFile(Integer fileId) {
        return this.fileMapper.deleteFile(fileId);
    }
}
