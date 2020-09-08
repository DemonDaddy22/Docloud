package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping("/upload")
    public String uploadCredential(@ModelAttribute("newCredential") Credential credential, Authentication authentication, Model model) {
        // same controller method solves both the purposes of insert and update
        if (credential.getCredentialId() == null) {
            String username = authentication.getName();
            User user = this.userService.getUser(username);
            Integer userId = user.getUserId();

            credential.setUserId(userId);
            int rowsAdded = this.credentialService.addCredential(credential);
            if (rowsAdded == 1) model.addAttribute("credentialUploadSuccess", true);
            else model.addAttribute("credentialUploadFailure", true);
        } else {
            int rowsUpdated = this.credentialService.editCredential(credential);
            if (rowsUpdated == 1) model.addAttribute("credentialUploadSuccess", true);
            else model.addAttribute("credentialUploadFailure", true);
        }

        return "result.html";
    }

    @GetMapping("/delete/{credentialId")
    public String deleteCredential(@PathVariable Integer credentialId, Model model) {
        int rowsDeleted = this.credentialService.deleteCredential(credentialId);
        if (rowsDeleted == 1) model.addAttribute("credentialDeleteSuccess", true);
        else model.addAttribute("credentialDeleteSuccess", true);
        return "result.html";
    }
}
