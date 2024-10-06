package com.springsecurity.notes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.notes.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	
	List<Note> findNoteByOwnerName(String ownerName);

}
