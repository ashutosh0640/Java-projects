package com.springsecurity.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.notes.entity.Note;
import com.springsecurity.notes.service.NoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService service;

	@PostMapping(path = "/save")
	public Note saveNote(@RequestParam String content, @AuthenticationPrincipal UserDetails userDetails) {
		
		Note note = new Note();
		note.setContent(content);
		note.setOwnerName(userDetails.getUsername());

		Note savedNote = service.saveNote(note);
		return savedNote;

	}
	
	
	@GetMapping(path="/findAll")
	public List<Note> findAllNote() {
		return service.findAllNotes();
	}

	// http://yourdomain.com/note/getById/123
	@GetMapping(path = "/getById/{id}")
	public Note findNoteById(@PathVariable Long id) {
		return service.findNoteById(id);
	}
	
	@GetMapping(path="/findAllNote/{ownerName}")
	public List<Note> findAllNoteByOwnerName(@PathVariable String ownerName) {
		return service.findAllNoteByOwnerName(ownerName);
	}
	
	@PutMapping(path="/update")
	public Note updateNote(@RequestParam Long id, @RequestBody String content, @AuthenticationPrincipal UserDetails userDetails) {
		String userName = userDetails.getUsername();
		return service.updateNote(id, userName, content);
		
	}
	
	@DeleteMapping(path="/delete/{id}")
	public void deleteNoteById(Long id) {
		service.deleteNoteById(id);
	}

}
