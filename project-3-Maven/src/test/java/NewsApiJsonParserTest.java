import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NewsApiJsonParserTest
{

@Test
public void testParseFile_withValidJson() throws Exception {
    NewsApiJsonParser parser = new NewsApiJsonParser();
    File testFile = new File("newsapi.txt");
    
    List<NewsApiArticle> articles = parser.parse(testFile);
    
    assertEquals(20, articles.size());
    assertEquals("The latest on the coronavirus pandemic and vaccines: Live updates - CNN", articles.get(0).getTitle());
}
@Test
public void testParseFile_withBadJson() throws Exception {
    NewsApiJsonParser parser = new NewsApiJsonParser();
    File testFile = new File("../project_2/inputs/bad.json");
    
    List<NewsApiArticle> articles = parser.parse(testFile);
    
    assertEquals(17, articles.size(), "Only valid articles should be parsed.");
    
    for (NewsApiArticle article : articles) {
        assertNotNull(article.getTitle(), "Article title should not be null for valid articles.");
        assertNotNull(article.getPublishedAt(), "Article published date should not be null for valid articles.");
        assertNotNull(article.getUrl(), "Article URL should not be null for valid articles.");
    }
}
}
