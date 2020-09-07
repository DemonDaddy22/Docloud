package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {
    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity getFile(@PathVariable Integer fileId) {
        File file = this.fileService.getFileById(fileId);
        String filename = file.getFilename();
        byte[] filedata = file.getFiledata();
        String contenttype = file.getContenttype();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contenttype)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"").body(filedata);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = this.userService.getUser(username);
        Integer userId = user.getUserId();
        File file = new File();

        try {
            file.setFiledata(multipartFile.getBytes());
        } catch (IOException e) {
            System.out.println("Error occurred while uploading file");
        }

        file.setFilename(multipartFile.getOriginalFilename());
        file.setContenttype(multipartFile.getContentType());
        file.setFilesize(multipartFile.getSize());
        file.setUserId(userId);

        if (this.fileService.fileAlreadyExists(file)) {
            model.addAttribute("fileExists", true);
            return "result.html";
        }

        int rows = this.fileService.addFile(file);

        if (rows == 1) model.addAttribute("fileUploadSuccess", true);
        else model.addAttribute("fileUploadFailure", true);

        return "result.html";
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Model model) {
        Integer rowsDeleted = this.fileService.deleteFile(fileId);
        if (rowsDeleted == 1) model.addAttribute("fileDeleteSuccess", true);
        else model.addAttribute("fileDeleteFailure", true);

        return "result.html";
    }
}
