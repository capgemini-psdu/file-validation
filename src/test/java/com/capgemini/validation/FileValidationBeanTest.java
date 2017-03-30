package com.capgemini.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileValidationBeanTest {

	private static final String CORRELATION_ID = "1234QWER";

	private static final String DIR = "src/test/resources/";

	private DefaultFileValidationBean bean = new DefaultFileValidationBean();

	@Test
	public void testEmptyContentType() throws Exception {
		assertEquals("text/plain", bean.probeContentType(CORRELATION_ID, DIR + "test.empty"));
	}
	
	@Test
	public void testOctetStreamContentType() throws Exception {
		assertEquals("application/octet-stream", bean.probeContentType(CORRELATION_ID, DIR + "test.some_unrecognised_binary_file"));
	}
	
	@Test
	public void testCsvContentType() throws Exception {
		assertEquals("text/csv", bean.probeContentType(CORRELATION_ID, DIR + "test.csv"));
	}
	
	@Test
	public void testXmlContentType() throws Exception {
		assertEquals("application/xml", bean.probeContentType(CORRELATION_ID, DIR + "test.xml"));
	}
	
	@Test
	public void testTextPlainContentType() throws Exception {
		assertEquals("text/plain", bean.probeContentType(CORRELATION_ID, DIR + "test.foobar"));
	}
	
	@Test
	public void testZipContentType() throws Exception {
		assertEquals("application/zip", bean.probeContentType(CORRELATION_ID, DIR + "test.zip"));
	}

	@Test
	public void testGetLocalFileSize() throws Exception {
		assertEquals(14, bean.getLocalFileSize(CORRELATION_ID, DIR + "test.xml"));
	}

	@Test(expected=RuntimeException.class)
	public void testGetLocalFileSizeFileNotFound() throws Exception {
		bean.getLocalFileSize(CORRELATION_ID, DIR + "no_such_file");
	}

}
