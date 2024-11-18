/**
 * Processes articles of different types using the Visitor design pattern.
 * This class implements the {@link ArticleVisitor} interface, providing
 * specific logic for processing {@link SimpleJsonArticle} and {@link NewsApiArticle}.
 */
public class ArticleProcessor implements ArticleVisitor
{

/**
 * Processes a {@link SimpleJsonArticle} by printing its details to the console.
 *
 * @param article the SimpleJsonArticle to process
 */
@Override
public void visit(SimpleJsonArticle article)
{
    System.out.println("Processing Simple JSON Article:");
    System.out.println("Title: " + article.getTitle());
    System.out.println("Description: " + article.getDescription());
    System.out.println("Published At: " + article.getPublishedAt());
    System.out.println("URL: " + article.getUrl());
}


/**
 * Processes a {@link NewsApiArticle} by printing its details to the console.
 *
 * @param article the NewsApiArticle to process
 */
@Override
public void visit(NewsApiArticle article)
{
    System.out.println("Processing News API Article:");
    System.out.println("Author: " + article.getAuthor());
    System.out.println("Title: " + article.getTitle());
    System.out.println("Description: " + article.getDescription());
    System.out.println("Published At: " + article.getPublishedAt());
    System.out.println("URL: " + article.getUrl());
    
    if (article.getArticleSource() != null)
    {
        System.out.println("Source ID: " + article.getArticleSource().getId());
        System.out.println("Source Name: " + article.getArticleSource().getName());
    }
    System.out.println("URL to Image: " + article.getUrlToImage());
    System.out.println("Content: " + article.getContent());
}
}
