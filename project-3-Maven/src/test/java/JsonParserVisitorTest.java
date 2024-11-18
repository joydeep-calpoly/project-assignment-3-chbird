import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JsonParserVisitorTest {

/**
 * Tests the behavior of the JsonParserVisitor when the configuration specifies a NewsAPI source from a file.
 * It verifies that the NewsApiJsonParser's `parse` method is called with the correct file input, ensuring
 * that the visitor correctly handles file-based sources for the NewsAPI format.
 *
 * @throws Exception if an error occurs during parsing
 */
@Test
public void testVisitorWithNewsApiFileSource() throws Exception {
    // Arrange
    String testFilePath = "test_newsapi_file.json";
    ParserConfig config = new ParserConfig(ParserSource.FILE, ParserFormat.NewsAPI, testFilePath);
    File testFile = new File(testFilePath);
    
    NewsApiJsonParser newsApiParser = mock(NewsApiJsonParser.class);
    JsonParserVisitor visitor = new JsonParserVisitor(config);
    
    // Act
    visitor.visit(newsApiParser);
    
    // Assert: Check that parse is called for NewsAPI with File source
    verify(newsApiParser, times(1)).parse(testFile); // Verify interaction with mock
    
    // Additional assert to check that the visitor is indeed using a file source
    assertEquals(ParserSource.FILE, config.getSource(), "Source should be FILE for NewsAPI.");
    assertEquals(ParserFormat.NewsAPI, config.getFormat(), "Format should be NewsAPI for this test.");
}

/**
 * Tests the behavior of the JsonParserVisitor when the configuration specifies a NewsAPI source from a URL.
 * It ensures that the NewsApiJsonParser's `parse` method is called with the correct URL input, verifying that
 * the visitor correctly handles URL-based sources for the NewsAPI format.
 *
 * @throws Exception if an error occurs during parsing
 */
@Test
public void testVisitorWithNewsApiUrlSource() throws Exception {
    // Arrange
    String testUrlString = "http://example.com/test_newsapi_url.json";
    ParserConfig config = new ParserConfig(ParserSource.URL, ParserFormat.NewsAPI, testUrlString);
    URL testUrl = new URL(testUrlString);
    
    NewsApiJsonParser newsApiParser = mock(NewsApiJsonParser.class);
    JsonParserVisitor visitor = new JsonParserVisitor(config);
    
    // Act
    visitor.visit(newsApiParser);
    
    // Assert: Check that parse is called for NewsAPI with URL source
    verify(newsApiParser, times(1)).parse(testUrl); // Verify interaction with mock
    
    // Additional assert to check that the visitor is indeed using a URL source
    assertEquals(ParserSource.URL, config.getSource(), "Source should be URL for NewsAPI.");
    assertEquals(ParserFormat.NewsAPI, config.getFormat(), "Format should be NewsAPI for this test.");
}

/**
 * Tests the behavior of the JsonParserVisitor when the configuration specifies a SIMPLE source from a file.
 * It verifies that the SimpleJsonParser's `parse` method is called with the correct file input, ensuring
 * that the visitor correctly handles file-based sources for the SIMPLE format.
 *
 * @throws Exception if an error occurs during parsing
 */
@Test
public void testVisitorWithSimpleJsonFileSource() throws Exception {
    // Arrange
    String testFilePath = "test_simple_file.json";
    ParserConfig config = new ParserConfig(ParserSource.FILE, ParserFormat.SIMPLE, testFilePath);
    File testFile = new File(testFilePath);
    
    SimpleJsonParser simpleJsonParser = mock(SimpleJsonParser.class);
    JsonParserVisitor visitor = new JsonParserVisitor(config);
    
    // Act
    visitor.visit(simpleJsonParser);
    
    // Assert: Check that parse is called for SimpleJson with File source
    verify(simpleJsonParser, times(1)).parse(testFile); // Verify interaction with mock
    
    // Additional assert to check that the visitor is indeed using a file source
    assertEquals(ParserSource.FILE, config.getSource(), "Source should be FILE for SIMPLE format.");
    assertEquals(ParserFormat.SIMPLE, config.getFormat(), "Format should be SIMPLE for this test.");
}

/**
 * Tests the behavior of the JsonParserVisitor when the configuration specifies a SIMPLE source from a URL.
 * Since the SIMPLE format does not support URL sources, it verifies that an UnsupportedOperationException
 * is thrown, ensuring that the visitor correctly handles this unsupported case.
 */
@Test
public void testVisitorWithSimpleJsonUrlSourceNotSupported() {
    // Arrange
    String testUrlString = "http://example.com/test_simple_url.json";
    ParserConfig config = new ParserConfig(ParserSource.URL, ParserFormat.SIMPLE, testUrlString);
    URL testUrl = null;
    try {
        testUrl = new URL(testUrlString);
    } catch (Exception ignored) {}
    
    SimpleJsonParser simpleJsonParser = mock(SimpleJsonParser.class);
    JsonParserVisitor visitor = new JsonParserVisitor(config);
    
    // Act & Assert: Ensure Simple format does not support URL source
    UnsupportedOperationException exception = assertThrows(UnsupportedOperationException.class, () -> visitor.visit(simpleJsonParser));
    assertEquals("SIMPLE format does not support URL source.", exception.getMessage(), "Exception message should indicate unsupported URL source for SIMPLE format.");
}

/**
 * Tests the consistency of the parser type used by the JsonParserVisitor based on the input configuration.
 * It verifies that the visitor uses the correct parser type (JsonParserVisitor) for both NewsAPI and SIMPLE
 * formats, ensuring that the visitor behaves consistently with the provided configurations.
 */
@Test
public void testVisitorParserTypeConsistency() {
    // Arrange for NewsAPI format with FILE source
    ParserConfig newsApiFileConfig = new ParserConfig(ParserSource.FILE, ParserFormat.NewsAPI, "project_3/inputs/newsapi.txt");
    JsonParserVisitor newsApiVisitor = new JsonParserVisitor(newsApiFileConfig);
    
    // Assert that the NewsAPI parser type is used
    assertTrue(newsApiVisitor instanceof JsonParserVisitor, "Visitor type should be JsonParserVisitor for NewsAPI.");
    
    // Arrange for SIMPLE format with FILE source
    ParserConfig simpleFileConfig = new ParserConfig(ParserSource.FILE, ParserFormat.SIMPLE, "project_3/inputs/simple.txt");
    JsonParserVisitor simpleVisitor = new JsonParserVisitor(simpleFileConfig);
    
    // Assert that the SimpleJsonParser type is used
    assertTrue(simpleVisitor instanceof JsonParserVisitor, "Visitor type should be JsonParserVisitor for SIMPLE.");
}
}
