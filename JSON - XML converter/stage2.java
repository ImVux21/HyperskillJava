package converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args){
        String wholeInput = createInput();
        if (wholeInput.startsWith("<")) {
            System.out.println(convertXMLToJSON(wholeInput));
        } else if (wholeInput.startsWith("{")) {
            System.out.println(convertJSONToXML(wholeInput));
        }
    }

    private static String createInput() {
        String result = "";
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\JSON - XML converter\\JSON - XML converter\\task\\src\\converter\\test.txt"));
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                result += str;
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException.");
        }
        return result;
    }

    private static String convertJSONToXML(String input) {
        Pattern pattern = Pattern.compile("(?s)\\{\"\\w+\"\\s:\\s\".+\"}");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return convertJSONToXMLWithoutAttributes(input);
        } else {
            return convertJSONToXMLWithAttributes(input);
        }

    }

    private static String convertJSONToXMLWithAttributes(String input) {
        String newInput = input.replaceAll("\\s{2,}", ""); /* {
                                                                                "employee" : {
                                                                                    "@department" : "manager",
                                                                                    "#employee" : "Garry Smith"
                                                                                }
                                                                               }
                                                                       =>  {"employee" : {"@department" : "manager","#employee" : "Garry Smith"}} */
        String[] parts = newInput.split("\\{|\"|:"); // [, , employee,  ,  , , @department,  ,  , manager, ,, #employee,  ,  , Garry Smith, }}]
        String result = "<" + parts[2].trim();

        Pattern patternAtrributes = Pattern.compile("\"@(\\w+)\"\\s:\\s\"?(\\w+)\"?");
        Matcher matcherAttributes = patternAtrributes.matcher(newInput);
        while (matcherAttributes.find()) {
            result += String.format(" %s = \"%s\"", matcherAttributes.group(1), matcherAttributes.group(2));
        }

        Pattern patternContent = Pattern.compile("\"#(\\w+)\"\\s:\\s\"(\\w+\\s\\w+)\"");
        Matcher matcherContent = patternContent.matcher(newInput);

        // check whether contents are (it means "#\\w+" = null)
        if (matcherContent.find()) {
            result += ">";
            matcherAttributes.reset();
            while (matcherContent.find()) {
                result += String.format("%s", matcherContent.group(2));
            }
            result += String.format("</%s>", parts[2].trim());
        } else {
            matcherContent.reset();
            result += " />";
        }


        return result;
    }

    private static String convertJSONToXMLWithoutAttributes(String input) {
        String[] parts = input.split("\""); /* {"jdk" : "1.8.9"}
                                                    [{, jdk,  : , 1.8.9, }] */
        if (parts.length > 3 && !parts[3].equals("null")) {
            return String.format("<%s>%s</%s>", parts[1], parts[3], parts[1]);
        }
        return String.format("<%s/>", parts[1]);
    }

    private static String convertXMLToJSON(String input) {
        String regexContentTagsWithoutAttributes = "<[^=]+>.+</.+>";
        if (input.matches(regexContentTagsWithoutAttributes)) {
            return convertXMLToJSONWithoutAttributes(input);
        }

        String regexContentTagsWithAttributes = "<.+>.+</.+>";
        if (input.matches(regexContentTagsWithAttributes)) {
            return convertXMLToJSONWithAttributes(input);
        }

        String regexWithoutContentTagsAndAttributes = "<[^=]+/>";
        if (input.matches(regexWithoutContentTagsAndAttributes)) {
            return convertXMLToJSONWithoutContentTagsAndAttributes(input);
        }

        String regexWithoutContentTagsAndWithAttributes = "<.+/>";
        if (input.matches(regexWithoutContentTagsAndWithAttributes)) {
            return convertXMLToJSONWithoutContentTagsAndWithAttributes(input);
        }
        return null;
    }

    private static String convertXMLToJSONWithoutContentTagsAndWithAttributes(String input) {
        Pattern pattern = Pattern.compile("((\\w+)(\\s=\\s)\"(\\w+)\")");
        Matcher matcher = pattern.matcher(input);
        String[] parts = input.split("<|\\s");

        String result = "";

        result += String.format("{\"%s\":{", parts[1].trim());
        while (matcher.find()) {
            result += String.format("\"@%s\":\"%s\",", matcher.group(2).trim(), matcher.group(4).trim());
        }
        result += String.format("\"#%s\":%s}}", parts[1], null);
        return result;
    }

    private static String convertXMLToJSONWithoutContentTagsAndAttributes(String input) {
        String[] parts = input.split("<|/>"); /* <success/>
                                                           [, success] */
        return String.format("{\"%s\":%s}", parts[1], null);
    }

    private static String convertXMLToJSONWithAttributes(String input) {
        String[] parts = input.split("</|<|>|\"|=");

        Pattern pattern = Pattern.compile("((\\w+)(\\s=\\s)\"(\\w+)\")");
        Matcher matcher = pattern.matcher(input);


        String result = String.format("{\"%s\":{", parts[parts.length-1].trim());
        while (matcher.find()) {
            result += String.format("\"@%s\":\"%s\",", matcher.group(2).trim(), matcher.group(4).trim());
        }
        result += String.format("\"#%s\":\"%s\"}}", parts[parts.length-1].trim(), parts[parts.length-2].trim());
        return result;
    }

    private static String convertXMLToJSONWithoutAttributes(String input) {
        String[] parts = input.split("[<>]"); /* <host>127.0.0.1</host>
                                                        [, host, 127.0.0.1, /host] */
        return String.format("{\"%s\":\"%s\"}", parts[1], parts[2]);
    }
}
