package md2hyper;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting up!");

        Path inputPath = Path.of(args[0]);
        Path outputPath = Path.of(args[1]);

        MarkdownParser parser = new MarkdownParser();
        HTMLGenerator generator = new HTMLGenerator();

        Document document = parser.parse(inputPath);
        generator.generate(document, outputPath);
    }

    @SuppressWarnings("unused")
    private static void printUsage() {
        System.err.println();
        System.exit(64);
    }
}