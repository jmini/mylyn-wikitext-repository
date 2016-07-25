package wikitext;

import java.io.StringWriter;

import org.eclipse.mylyn.wikitext.asciidoc.core.AsciiDocLanguage;
import org.eclipse.mylyn.wikitext.confluence.core.ConfluenceLanguage;
import org.eclipse.mylyn.wikitext.core.parser.MarkupParser;
import org.eclipse.mylyn.wikitext.core.parser.builder.HtmlDocumentBuilder;
import org.eclipse.mylyn.wikitext.core.parser.markup.MarkupLanguage;
import org.eclipse.mylyn.wikitext.markdown.core.MarkdownLanguage;
import org.eclipse.mylyn.wikitext.mediawiki.core.MediaWikiLanguage;
import org.eclipse.mylyn.wikitext.textile.core.TextileLanguage;
import org.eclipse.mylyn.wikitext.tracwiki.core.TracWikiLanguage;
import org.eclipse.mylyn.wikitext.twiki.core.TWikiLanguage;

public class WikitextMain {

  private static final String NAME_ASCIIDOC = "AsciiDoc";
  private static final String NAME_CONFLUENCE = "Confluence";
  private static final String NAME_MARKDWON = "Markdown";
  private static final String NAME_MEDIAWIKI = "MediaWiki";
  private static final String NAME_TEXTILE = "Textile";
  private static final String NAME_TRACWIKI = "TracWiki";
  private static final String NAME_TWIKI = "TWiki";

  public static void main(String[] args) {
    printHelloWorld(new AsciiDocLanguage(), NAME_ASCIIDOC, createAsciiDocHelloWorld());
    printHelloWorld(new ConfluenceLanguage(), NAME_CONFLUENCE, createConfluenceHelloWorld());
    printHelloWorld(new MarkdownLanguage(), NAME_MARKDWON, createMarkdownHelloWorld());
    printHelloWorld(new MediaWikiLanguage(), NAME_MEDIAWIKI, createMediaWikiHelloWorld());
    printHelloWorld(new TextileLanguage(), NAME_TEXTILE, createTextileHelloWorld());
    printHelloWorld(new TracWikiLanguage(), NAME_TRACWIKI, createTracWikiHelloWorld());
    printHelloWorld(new TWikiLanguage(), NAME_TWIKI, createTWikiHelloWorld());
  }

  private static void printHelloWorld(MarkupLanguage markupLanguage, String languageName, String text) {
    printHeader(languageName);
    StringWriter writer = new StringWriter();
    HtmlDocumentBuilder builder = new HtmlDocumentBuilder(writer);
    builder.setEmitAsDocument(false);
    MarkupParser parser = new MarkupParser(markupLanguage, builder);
    parser.parse(text);
    System.out.println(writer.toString());
    System.out.println();
  }

  private static void printHeader(String languageName) {
    printLine();
    System.out.println(languageName);
    printLine();
  }

  private static void printLine() {
    System.out.println("========================================================================");
  }
  
  private static String createAsciiDocHelloWorld() {
	  StringBuilder sb = new StringBuilder();
	  sb.append("= Heading 1\n");
	  sb.append("\n");
	  sb.append("Hello World!\n");
	  sb.append("\n");
	  sb.append("* Lorem\n");
	  sb.append("* Ipsum\n");
	  sb.append("\n");
	  sb.append("This is *AsciiDoc* language.\n");
	  return sb.toString();
  }

  private static String createConfluenceHelloWorld() {
    StringBuilder sb = new StringBuilder();
    sb.append("h1. Heading 1\n");
    sb.append("\n");
    sb.append("Hello World!\n");
    sb.append("\n");
    sb.append("* Lorem\n");
    sb.append("* Ipsum\n");
    sb.append("\n");
    sb.append("This is *Confluence* language.\n");
    return sb.toString();
  }

  private static String createMarkdownHelloWorld() {
    StringBuilder sb = new StringBuilder();
    sb.append("# Heading 1\n");
    sb.append("\n");
    sb.append("Hello World!\n");
    sb.append("\n");
    sb.append("* Lorem\n");
    sb.append("* Ipsum\n");
    sb.append("\n");
    sb.append("This is **Markdown**  language.\n");
    return sb.toString();
  }

  private static String createMediaWikiHelloWorld() {
    StringBuilder sb = new StringBuilder();
    sb.append("= Heading 1 =\n");
    sb.append("\n");
    sb.append("Hello World!\n");
    sb.append("\n");
    sb.append("* Lorem\n");
    sb.append("* Ipsum\n");
    sb.append("\n");
    sb.append("This is '''Mediawiki''' language.\n");
    return sb.toString();
  }

  private static String createTextileHelloWorld() {
    StringBuilder sb = new StringBuilder();
    sb.append("h1. Heading 1\n");
    sb.append("\n");
    sb.append("Hello World!\n");
    sb.append("\n");
    sb.append("* Lorem\n");
    sb.append("* Ipsum\n");
    sb.append("\n");
    sb.append("This is *Textile* language.\n");
    return sb.toString();
  }

  private static String createTracWikiHelloWorld() {
    StringBuilder sb = new StringBuilder();
    sb.append("= Heading 1 =\n");
    sb.append("\n");
    sb.append("Hello World!\n");
    sb.append("\n");
    sb.append(" * Lorem\n");
    sb.append(" * Ipsum\n");
    sb.append("\n");
    sb.append("This is '''Tracwiki''' language.\n");
    return sb.toString();
  }

  private static String createTWikiHelloWorld() {
    StringBuilder sb = new StringBuilder();
    sb.append("---+ Heading 1\n");
    sb.append("\n");
    sb.append("Hello World!\n");
    sb.append("\n");
    sb.append("   * Lorem\n");
    sb.append("   * Ipsum\n");
    sb.append("\n");
    sb.append("This is *Twiki* language.\n");
    return sb.toString();
  }
}
