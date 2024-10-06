package com.springsecurity.notes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springsecurity.notes.entity.Note;
import com.springsecurity.notes.exceptions.NoteNotFoundException;
import com.springsecurity.notes.repository.NoteRepository;

import jakarta.transaction.Transactional;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository repo;
	
	public Note saveNote(Note note) {
		Note savedNote = repo.save(note);
		return savedNote;
		
	}
	
	public void deleteNoteById(Long id) {
		repo.deleteById(id);
	}
	
	
	@Transactional
	public Note updateNote(Long id, String username, String content) {
		
		Note oldNote = repo.findById(id)
				.orElseThrow(()-> new NoteNotFoundException("No note found with ID: "+id));
		
		oldNote.setContent(content);
		oldNote.setOwnerName(username);
		
		repo.save(oldNote);
		
		return oldNote;
	}
	
	public List<Note> findAllNotes() {
		return repo.findAll();
	}
	
	public Note findNoteById(Long id) {
		return repo.findById(id)
				.orElseThrow(()-> new NoteNotFoundException("No note found with ID: "+id));
	}
	
	public List<Note> findAllNoteByOwnerName(String ownerName) {
		List<Note> notes = repo.findNoteByOwnerName(ownerName);
		return notes;
	}
}
