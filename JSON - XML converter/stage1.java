package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.startsWith("<")) {
            System.out.println(convertXMLToJSON(input));
        } else if (input.startsWith("{")) {
            System.out.println(convertJSONToXML(input));
        }
    }

    private static String convertJSONToXML(String input) {
        String[] parts = input.split("\""); /* {"jdk" : "1.8.9"}
                                                    [{, jdk,  : , 1.8.9, }] */
        if (parts.length > 3 && !parts[3].equals("null")) {
            return String.format("<%s>%s</%s>", parts[1], parts[3], parts[1]);
        }
        return String.format("<%s/>", parts[1]);
    }

    private static String convertXMLToJSON(String input) {
        String regexContentTags = "<.+>.+</.+>";
        if (input.matches(regexContentTags)) {
            String[] parts = input.split("[<>]"); /* <host>127.0.0.1</host>
                                                        [, host, 127.0.0.1, /host] */
            return String.format("{\"%s\":\"%s\"}", parts[1], parts[2]);
        }

        String regexWithoutContentTags = "<.+/>";
        if (input.matches(regexWithoutContentTags)) {
            String[] parts = input.split("<|/>"); /* <success/>
                                                           [, success] */
            return String.format("{\"%s\":%s}", parts[1], null);
        }
        return null;
    }
}
