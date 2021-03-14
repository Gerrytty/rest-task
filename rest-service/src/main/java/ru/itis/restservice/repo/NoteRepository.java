package ru.itis.restservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itis.restservice.model.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Modifying
    @Query("update Note n set n.title = ?1 where n.id = ?2")
    void updateTitleByNoteId(String title, Integer id);

    @Modifying
    @Query("update Note n set n.content = ?1 where n.id = ?2")
    void updateBodyByNoteId(String body, Integer id);

    @Modifying
    @Query("update Note n set n.title = ?1, n.content = ?2 where n.id = ?3")
    void updateTitleAndBodyByNoteId(String title, String body, Integer id);

    List<Note> findAllByContentContainingOrTitleContaining(String param, String param2);
}
