package ru.itis.hateoasservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoasservice.models.Note;

@RepositoryRestResource
public interface NoteRepository extends JpaRepository<Note, Integer> {
}
