package com.capgemini.validation;

public interface FileValidationBean {

	String probeContentType(String correlationId, String path);
	
	long getLocalFileSize(String correlationId, String path);

}
