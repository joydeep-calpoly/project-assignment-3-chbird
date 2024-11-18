import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleJsonParserTest
{

@Test
public void testParseFile_withValidJson()
{
    SimpleJsonParser parser = new SimpleJsonParser();
    File testFile = new File("../project_2/inputs/simple.txt");
    
    SimpleJsonArticle article = parser.parse(testFile);
    
    assertEquals("Extend Assignment #1 to support multiple sources and to introduce source processor.", article.getDescription());
    assertEquals("2021-04-16 09:53:23.709229", article.getPublishedAt());
    assertEquals("Assignment #2", article.getTitle());
    assertEquals("https://canvas.calpoly.edu/courses/55411/assignments/274503", article.getUrl());
}
@Test
public void testParseString_withMissingDescription() {
    SimpleJsonParser parser = new SimpleJsonParser();
    
    String jsonInput = """
        {
          "publishedAt": "2021-04-16 09:53:23.709229",
          "title": "Assignment #2",
          "url": "https://canvas.calpoly.edu/courses/55411/assignments/274503"
        }
        """;
    
    SimpleJsonArticle article = parser.parse(jsonInput);
    
    assertNull(article, "Description is missing for article");
    
}
}