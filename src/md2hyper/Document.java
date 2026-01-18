package md2hyper;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private final List<Element> elements;
    
    public Document() {
        this.elements = new ArrayList<>();
    }
    
    public void addElement(String type, String content) {
        elements.add(new Element(type, content));
    }
    
    public List<Element> getElements() {
        return elements;
    }

    public static class Element {
        private final String type;
        private final String content;

        public Element(String type, String content) {
            this.type = type;
            this.content = content;
        }

        public String getType() {
            return type;
        }
        
        public String getContent() {
            return content;
        }
    }
}
