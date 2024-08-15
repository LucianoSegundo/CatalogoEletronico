package com.LFsoftware.catalogoEletronico.controller.exception;

import java.io.Serializable;
import java.time.Instant;

public class StardartError implements Serializable {
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StardartError() {
		// TODO Auto-generated constructor stub
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

}
