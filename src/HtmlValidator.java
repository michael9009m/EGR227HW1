
/*Michael Martinez
    Created: 01/25/2023
    Data Structures and Analysis
    Homework #1: HTML Validator
    Due: Monday, 1/26/23, 10 PM
 */


import java.util.*;
public class HtmlValidator {

    public static final String INDENTATION_MARKER = "    ";
    private Queue<HtmlTag> tags;
    public HtmlValidator(){
        tags = new LinkedList<>();
     }

    public HtmlValidator(Queue<HtmlTag> tags){
        if(tags == null){
            throw new IllegalArgumentException("YOU CANT DO THAT HERE");
        }
        this.tags = new LinkedList<>(tags);
    }
    public void addTag(HtmlTag tag){
        if(tag == null){
            throw new IllegalArgumentException("Cannot add null tag!");
        }
        tags.add(tag);
    }

    public Queue<HtmlTag> getTags(){
        return new LinkedList<>(tags);
    }

    public void removeAll(String element){


        if(element == null){
            throw new IllegalArgumentException("Cannot add null element");
        }
        tags.removeIf(tag ->tag.getElement().equalsIgnoreCase(element));

        /*while(!tags.isEmpty()){

            element.equals(tags.remove());
        }*/


    }

    public void validate(){
        Stack<HtmlTag> openTags = new Stack<>();
        for(int i = 0; i < tags.size(); i++) {
            // for(HtmlTag tag : tags){ is NOT allowed by the spec
            // Because we can't use a foreach loop :l
            HtmlTag tag = tags.remove();
            tags.add(tag);

            if (tag.isSelfClosing()) {
                printWithIndentation(tag, openTags.size());
            } else if (tag.isOpenTag()) {
                printWithIndentation(tag, openTags.size());
                openTags.push(tag);
            } else if (!openTags.isEmpty() && tag.matches(openTags.peek())) { // By exhaustion, the tag must be a closing tag
                // Closing tag should be at same depth as opening, so we pop before printing
                openTags.pop();
                printWithIndentation(tag, openTags.size());
            } else {
                System.out.println("ERROR unexpected tag: " + tag.toString());
            }
        }
        // Deal with unclosed tags
        while (!openTags.isEmpty()) {
            HtmlTag tag = openTags.pop();
            System.out.println("ERROR unclosed tag: " + tag.toString());
        }
        /*
        Stack<HtmlTag> openTags = new Stack<>();
        for(int i = 0; i < tags.size(); i++){
            HtmlTag tag = tags.remove();
            tags.add(tag);

            if(tag.isSelfClosing()){
                printWithIndentation(tag, openTags.size());
            } else if(tag.isOpenTag()){
                printWithIndentation(tag, openTags.size());
            } else if(!openTags.isEmpty() && tag.matches(openTags.peek())){
                openTags.pop();
                printWithIndentation(tag, openTags.size());
            }
            else{
                System.out.println("Error! Unexpected tag: " + tag.toString());
            }
        }

        while(!openTags.isEmpty()){
            HtmlTag tag = openTags.pop();
            System.out.println("Error unclosed tag:" + tag.toString());
        }*/

    }

    private static void printWithIndentation(HtmlTag tag, int indentationLevel){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < indentationLevel; i++){
            sb.append(INDENTATION_MARKER);
        }
        sb.append(tag.toString());
        System.out.println(sb.toString());
    }
}
