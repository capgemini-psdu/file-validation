package com.capgemini.validation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.validation.exception.MonitoredError;

public class DefaultFileValidationBean implements FileValidationBean {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String probeContentType(String correlationId, String path) {
		logger.info("CorrelationID {} : Getting content type for file {}", correlationId, path);
		String mimeType = null;
		try {
			// See TikaFileTypeDetector to which this call is ultimately delegated
			mimeType = Files.probeContentType(Paths.get(path));
			logger.info("CorrelationID {} : File {} content type {}", correlationId, path, mimeType);
		} catch (Exception e) {
			MonitoredError.LOCAL_FILE_ACCESS_ERROR.create(correlationId, path, "Error determining file content type.", e);
		}
		return mimeType; 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getLocalFileSize(String correlationId, String path) {
		logger.info("CorrelationId {} : Getting local file size for file {}", correlationId, path);
		long size = -1;
		try {			
			File file = new File(path);
			if (!file.exists()) {
				MonitoredError.LOCAL_FILE_ACCESS_ERROR.create(correlationId, path, "File not found when trying to determine local file size.");
			}
			size = file.length();
			logger.info("CorrelationId {} : File size for file {} is {} bytes", correlationId, path, size);
		} catch (Exception e) {
			MonitoredError.LOCAL_FILE_ACCESS_ERROR.create(correlationId, path, "Error determining local file size.", e);
		}
		return size;
	}

}
