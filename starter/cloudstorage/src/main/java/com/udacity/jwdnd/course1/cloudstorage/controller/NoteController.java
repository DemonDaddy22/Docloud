package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/upload")
    public String uploadNote(@ModelAttribute("newNote") Note note, Authentication authentication, Model model) {
        // same function solves both the purposes of insert and update
        if (note.getNoteId() == null) {
            String username = authentication.getName();
            User user = this.userService.getUser(username);
            Integer userId = user.getUserId();

            note.setUserId(userId);
            int rowsAdded = this.noteService.addNote(note);
            if (rowsAdded == 1) model.addAttribute("noteUploadSuccess", true);
            else model.addAttribute("noteUploadFailure", true);
        } else {
            int rowsUpdated = this.noteService.editNote(note);
            if (rowsUpdated == 1) model.addAttribute("noteUpdateSuccess", true);
            else model.addAttribute("noteUpdateFailure", true);
        }

        return "result.html";
    }

    @GetMapping("/notes/delete/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
        int rowsDeleted = this.noteService.deleteNote(noteId);
        if (rowsDeleted == 1) model.addAttribute("noteDeleteSuccess", true);
        else model.addAttribute("noteDeleteSuccess", true);

        return "result.html";
    }
}
