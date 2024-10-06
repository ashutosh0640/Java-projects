package com.springsecurity.notes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	
	@Lob
	private String content;
	private String ownerName;
	
	public Note() {
		super();
	}
	public Note(String content, String ownerName) {
		super();
		this.content = content;
		this.ownerName = ownerName;
	}
	public Note(long id, String content, String ownerName) {
		super();
		Id = id;
		this.content = content;
		this.ownerName = ownerName;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	@Override
	public String toString() {
		return "Note [Id=" + Id + ", content=" + content + ", ownerName=" + ownerName + "]";
	}
	
}
