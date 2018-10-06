package com.example.ticket.ticketbooking.model;

import java.util.Date;

public class CustomDTO {

	private Integer status;
	private String code;
	private String message;
	private Date timeStamp;
	private Object response;

	public CustomDTO(Integer status, String code, String message, Date timeStamp, Object response) {
		super();
		this.status = status;
		this.code = code;
		this.message = message;
		this.timeStamp = timeStamp;
		this.response = response;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "CustomDTO [status=" + status + ", code=" + code + ", message=" + message + ", timeStamp=" + timeStamp
				+ ", response=" + response + "]";
	}

}
