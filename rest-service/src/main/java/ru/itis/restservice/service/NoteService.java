package ru.itis.restservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.restservice.dto.EditNoteDto;
import ru.itis.restservice.dto.NoteDto;
import ru.itis.restservice.model.Note;
import ru.itis.restservice.repo.NoteRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Value("${N}")
    private int N;

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> get() {
        return noteRepository.findAll();
    }

    public NoteDto get(Integer id) {
        Optional<Note> optionalNote = noteRepository.findById(id);

        if (optionalNote.isPresent()) {

            Note note = optionalNote.get();

            return replaceTitle(note);

        }
        return null;
    }

    public void delete(Integer id) {
        noteRepository.deleteById(id);
    }

    @Transactional
    public void update(Integer id, EditNoteDto editNoteDto) {

        if (editNoteDto.getTitle() != null && editNoteDto.getContent() != null) {
            noteRepository.updateTitleAndBodyByNoteId(editNoteDto.getTitle(), editNoteDto.getContent(), id);
        }

        else if (editNoteDto.getTitle() != null) {
            noteRepository.updateTitleByNoteId(editNoteDto.getTitle(), id);
        }

        else if (editNoteDto.getContent() != null) {
            noteRepository.updateBodyByNoteId(editNoteDto.getContent(), id);
        }

    }

    public List<NoteDto> search(String param) {

        List<NoteDto> list = new ArrayList<>();

        for (Note note : noteRepository.findAllByContentContainingOrTitleContaining(param, param)) {
            list.add(replaceTitle(note));
        }

        return list;
    }

    public NoteDto replaceTitle(Note note) {
        NoteDto noteDto = NoteDto.builder()
                .id(note.getId())
                .content(note.getContent())
                .title(note.getTitle())
                .build();

        String title = note.getTitle();

        if (title == null) {
            noteDto.setTitle(noteDto.getContent().substring(0, N));
        }

        return noteDto;
    }

}
