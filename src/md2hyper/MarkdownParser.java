package md2hyper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParser {

    public Document parse(Path inputPath) throws IOException {
        Document doc = new Document();
        List<String> lines = Files.readAllLines(inputPath);

        for (String line : lines) {
            // Skipping the empty lines and comments
            if (line.trim().isEmpty() || line.trim().startsWith("<!--")) {
                continue;
            } else if (line.trim().startsWith("-->")) {
                continue;
            }

            // Checks for headers '#' '##' '###' '####'
            if (line.startsWith("####")) {
                String content = procInlineFormatting(line.substring(4).trim());
                doc.addElement("h4", content);
            } else if (line.startsWith("###")) {
                String content = procInlineFormatting(line.substring(3).trim());
                doc.addElement("h3", content);
            } else if (line.startsWith("##")) {
                String content = procInlineFormatting(line.substring(2).trim());
                doc.addElement("h2", content);
            } else if (line.startsWith("#")) {
                String content = procInlineFormatting(line.substring(1).trim());
                doc.addElement("h1", content);
            } else {
                // Regular paragraph
                String content = procInlineFormatting(line.trim());
                doc.addElement("p", content);
            }
        }

        return doc;
    }

    private String procInlineFormatting(String text) {
        // Process in order: bold > italic to avoid conflict

        // **bold** and __bold__
        text = text.replaceAll("\\*\\*(.+?)\\*\\*", "<strong>$1</strong>");
        text = text.replaceAll("__(.+?)__", "<strong>$1</strong>");
        
        // *italic* and _italic_
        text = text.replaceAll("\\*(.+?)\\*", "<i>$1</i>");
        text = text.replaceAll("_(.+?)_", "<i>$1</i>");
        
        // underline already uses html so we can ignore it
        // but we will have to add some better logic for
        // ordered and unordered lists and future code
        // blocks...
        //
        // how are we gonna do code blocks? not sure
        
        return text;
    }
}
