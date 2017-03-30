package com.capgemini.validation.apache.tika;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.tika.Tika;

/**
 * Use the standard Java SPI extension to configure use of Apache Tika so
 * we're consistent in how we do this throughout the JVM. See
 * /META-INF/services/java.nio.file.spi.FileTypeDetector for the
 * registration of this class.
 */
public class TikaFileTypeDetector extends java.nio.file.spi.FileTypeDetector {

	private final Tika tika = new Tika();

	@Override
	public String probeContentType(Path path) throws IOException {
		return tika.detect(path.toFile());
	}
}
