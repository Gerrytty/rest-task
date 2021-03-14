package ru.itis.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.restservice.dto.EditNoteDto;
import ru.itis.restservice.dto.NoteDto;
import ru.itis.restservice.model.Note;
import ru.itis.restservice.service.NoteService;

import java.util.Objects;

@RestController
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("notes")
    public ResponseEntity<?> setNote(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.save(note));
    }

    @GetMapping("notes")
    public ResponseEntity<?> getNotes(@RequestParam(required = false) String query) {

        if (query == null) {

            return ResponseEntity.ok(noteService.get());
        }
        return ResponseEntity.ok(noteService.search(query));
    }

    @GetMapping("notes/{id}")
    public ResponseEntity<?> getNote(@PathVariable Integer id) {

        NoteDto note = noteService.get(id);

        return ResponseEntity.ok(
                Objects.requireNonNullElseGet(note, ResponseEntity::notFound)
        );

    }

    @DeleteMapping("notes/{id}")
    public void delete(@PathVariable Integer id) {
        noteService.delete(id);
    }

    @PutMapping("notes/{id}")
    public void edit(@PathVariable Integer id, @RequestBody EditNoteDto editNoteDto) {
        noteService.update(id, editNoteDto);
    }

}
