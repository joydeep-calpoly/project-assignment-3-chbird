import java.io.IOException;

public class Driver {
public static void main(String[] args) {
    try {
        // Simple format from a file
        ParserConfig simpleFileConfig = new ParserConfig(ParserSource.FILE, ParserFormat.SIMPLE, "project_3/inputs/simple.txt");
        SimpleJsonParser simpleParser = new SimpleJsonParser();
        JsonVisitor simpleVisitor = new JsonParserVisitor(simpleFileConfig);
        simpleParser.accept(simpleVisitor);
        
        // NewsAPI format from a URL
        ParserConfig newsApiUrlConfig = new ParserConfig(ParserSource.URL, ParserFormat.NewsAPI, "http://newsapi.org/v2/top-headlines?country=us&apiKey=e0b316a2c3634dbd97824d8d679cc0f8");
        NewsApiJsonParser newsParser = new NewsApiJsonParser();
        JsonVisitor newsVisitorUrl = new JsonParserVisitor(newsApiUrlConfig);
        newsParser.accept(newsVisitorUrl);
        
        // NewsAPI format from a file
        ParserConfig newsApiFileConfig = new ParserConfig(ParserSource.FILE, ParserFormat.NewsAPI, "project_3/inputs/newsapi.txt");
        JsonVisitor newsVisitorFile = new JsonParserVisitor(newsApiFileConfig);
        newsParser.accept(newsVisitorFile);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
