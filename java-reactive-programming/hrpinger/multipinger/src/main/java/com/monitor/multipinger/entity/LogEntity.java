package com.monitor.multipinger.entity;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logs ")
public class LogEntity {
	
	@Id
	private int hostId;
	@Indexed
	private String hsotName;
	private String timestamp;
	private String message;
	
	public LogEntity() {
		super();
	}
	
	public LogEntity(String hsotName, String timestamp, String message) {
		super();
		this.hsotName = hsotName;
		this.timestamp = timestamp;
		this.message = message;
	}

	public LogEntity(int hostId, String hsotName, String timestamp, String message) {
		super();
		this.hostId = hostId;
		this.hsotName = hsotName;
		this.timestamp = timestamp;
		this.message = message;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public String getHsotName() {
		return hsotName;
	}

	public void setHsotName(String hsotName) {
		this.hsotName = hsotName;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "LogEntity [hostId=" + hostId + ", hsotName=" + hsotName + ", timestamp=" + timestamp + ", message="
				+ message + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(hostId, hsotName, message, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogEntity other = (LogEntity) obj;
		return hostId == other.hostId && Objects.equals(hsotName, other.hsotName)
				&& Objects.equals(message, other.message) && Objects.equals(timestamp, other.timestamp);
	}
	
	
	

}
