package sorting;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void print(String sortingType, String dataType, Scanner sc, int length, PrintWriter w) {
        int count = 0;
        if (sortingType.equals("byCount")) {
            HashMap<String, Integer> m = new LinkedHashMap<>();
            m.putAll(byCount(dataType, sc, length, w));
            for (var entry : m.entrySet()) {
                if (length == 6) {
                    w.println(entry.getKey() + ": " + entry.getValue() + " time(s), " + average(m).get(count) + "%");
                } else {
                    System.out.println(entry.getKey() + ": " + entry.getValue() + " time(s), " + average(m).get(count) + "%");
                }
                count++;
            }
        } else if (sortingType.equals("natural")) {
            List<String> l = new ArrayList<>();
            l.addAll(natural(dataType, sc, length, w));
            if (dataType.equals("line")) {
                if (length == 6) {
                    w.println("Sorted data:");
                } else {
                    System.out.println("Sorted data:");
                }
            } else {
                if (length == 6) {
                    w.print("Sorted data: ");
                } else {
                    System.out.print("Sorted data: ");
                }
            }
            for (String s : l) {
                if (length == 6) {
                    w.print(s + " ");
                } else {
                    System.out.print(s + " ");
                }
            }
        }
    }

    public static void count(Map<String, Integer> map, String num) { // count the number of appearance of each element
        if (!map.containsKey(num)) {
            map.put(num, 1); // put element which haven't already been in the map
        }
        else {
            map.replace(num, map.get(num) + 1); // if already in the map, adding 1 to the value's this element
        }
    }

    public static List<Integer> average(HashMap<String, Integer> map) {
        int total = 0;
        int count = 0;
        List<Integer> averageList = new ArrayList<>();
        for (int i : map.values()) {
            total += i;
        }

        for (int i : map.values()) {
            int average = (int) (((double) i / total) * 100);
            averageList.add(count, average);
            count++;
        }
        return averageList;
    }

    public static void swapByLong(List<Long> l1, List<Integer> l2, int i , int j) { // swap in bubble sort
        long temp1 = l1.get(i);
        l1.set(i, l1.get(j));
        l1.set(j, temp1);
        int temp2 = l2.get(i);
        l2.set(i, l2.get(j));
        l2.set(j, temp2);
    }
    public static void swapByString(List<String> l1, List<Integer> l2, int i , int j) {
        String temp1 = l1.get(i);
        l1.set(i, l1.get(j));
        l1.set(j, temp1);
        int temp2 = l2.get(i);
        l2.set(i, l2.get(j));
        l2.set(j, temp2);
    }

    // Sorting long of byCount of sortingType
    public static Map<String, Integer> sortLongType (Map<String, Integer> map) {
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        // initialize two Lists to store keys and values
        List<Long> listKeys = new ArrayList<>();
        List<Integer> listValues = new ArrayList<>();

        int count = 0;
        // put all elements from map to two Lists
        for (var entry : map.entrySet()) {
             listKeys.add(count, Long.valueOf(entry.getKey()));
             listValues.add(count, entry.getValue());
             count++;
        }
        // sort by keys
        for (int i = 0; i < listKeys.size(); i++) {
            for (int j = i + 1; j < listKeys.size(); j++) {
                if (listKeys.get(i) > listKeys.get(j)) {
                    swapByLong(listKeys, listValues, i, j);
                }
            }
        }
        // sort by values
        for (int i = 0; i < listValues.size(); i++) {
            for (int j = i + 1; j < listValues.size(); j++) {
                if (listValues.get(i) > listValues.get(j)) {
                    swapByLong(listKeys, listValues, i, j);
                }
            }
        }

        // put all keys and values from two Lists to new sorted Map
        for (int i = 0; i < listKeys.size(); i++) {
            sortedMap.put(String.valueOf(listKeys.get(i)), listValues.get(i));
        }

        return sortedMap;
    }

    // Sorting word and line of byCount of sortingType
    public static Map<String, Integer> sortStringType (Map<String, Integer> map) {
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        Map<String, Integer> m = new TreeMap<>(map); // initialize TreeMap to sort by keys
        // initialize two Lists to store keys and values
        List<String> listKeys = new ArrayList<>();
        List<Integer> listValues = new ArrayList<>();
        int count = 0;

        // put all elements in map into two lists
        for (var entry : m.entrySet()) {
            listKeys.add(count, entry.getKey());
            listValues.add(count, entry.getValue());
            count++;
        }

        // sort according to values
        for (int i = 0; i < listValues.size(); i++) {
            for (int j = i + 1; j < listValues.size(); j++) {
                if (listValues.get(i) > listValues.get(j)) {
                    swapByString(listKeys, listValues, i, j);
                }
            }
        }

        // put all into the new sorted Map
        for (int i = 0; i < listValues.size(); i++) {
            sortedMap.put(String.valueOf(listKeys.get(i)), listValues.get(i));
        }
        return sortedMap;
    }

    // -sortingType byCount
    public static Map<String, Integer> byCount(String dataType, Scanner sc, int length, PrintWriter w) {
        int total = 0;
        Map<String, Integer> map = new LinkedHashMap<>();

        if (dataType.equals("long")) {
            while (sc.hasNextLong()) { // scanning input
                long number = sc.nextLong(); // scanning input
                count(map, String.valueOf(number));
                total++;
                map = sortLongType(map);
            }
        } else if (dataType.equals("line")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                count(map, line);
                total++;
                map = sortStringType(map);
            }
        } else if (dataType.equals("word")) {
            while (sc.hasNext()) {
                String number = sc.next();
                count(map, number);
                total++;
                map = sortStringType(map);
            }
        }
        if (length == 6) {
            w.format("Total %s: %d\n", dataType, total);
        } else {
            System.out.format("Total %s: %d\n", dataType, total);
        }

        return map;
    }

    public static List<String> natural(String dataType, Scanner sc, int length, PrintWriter w) {
        int total = 0;
        int count = 0;
        int countLong = 0;
        List<String> listString = new ArrayList<>(); // to contain line and words
        List<Long> listLong = new ArrayList<>(); //  to contain long number
        if (dataType.equals("long")) {
            while (sc.hasNextLong()) {
                long number = sc.nextLong();
                listLong.add(count, number); // add element to List
                Collections.sort(listLong);
                count++;
                total++;
            }
            for (long l : listLong) {
                listString.add(countLong, String.valueOf(l)); // modify List<Long> to List<String>
                countLong++;
            }
        } else if (dataType.equals("line")) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                listString.add(count, line);
                count++;
                total++;
            }
            listString.sort(new LineComparator());

        } else if (dataType.equals("word")) {
            while (sc.hasNext()) {
                String word = sc.next();
                listString.add(count, word);
                count++;
                total++;
            }
            Collections.sort(listString);
        }
        if (length == 6) {
            w.format("Total %s: %d\n", dataType, total);
        } else {
            System.out.format("Total %s: %d\n", dataType, total);
        }
        return listString;
    }

    public static boolean checkError(String[] args) {
        boolean checkDataType = false;
        boolean checkSortingType = false;
        for (String s : args) {
            if (s.equals("-sortingType")) {
                checkSortingType = true;
                break;
            }
            else if (s.equals("dataType")) {
                checkDataType = true;
                break;
            }
        }
        if (args.length % 2 != 0 && checkSortingType) {
            System.out.println("No sorting type defined!");
            return false;
        }  else if (args.length % 2 != 0 && checkDataType) {
            System.out.println("No data type defined!");
            return false;
        }
        return true;
    }

    public static void main(final String[] args) throws IOException {
        for (String s : args) {
            if (s.equals("-inputFile") && args.length == 6) {
                File inputFile = new File(args[3]);
                inputFile.createNewFile();
                File outputFile = new File(args[5]);
                outputFile.createNewFile();
                try (Scanner sc = new Scanner(inputFile);
                     PrintWriter w = new PrintWriter(outputFile)) {
                    if (args[1].equals("byCount")) {
                        print("byCount", "line", sc, args.length, w);
                    }
                }
            }
        }
        if (checkError(args))
            if (args.length > 2 && args.length < 5) {
                if (args[0].equals("-dataType")) {
                    print(args[3], args[1], scanner, args.length, null);
                } else if (args[0].equals("-sortingType")) {
                    print(args[1], args[3],scanner, args.length, null);
                }
            } else if (args.length < 3) {
                print("natural", args[1], scanner, args.length, null);
            }

    }
}

// customize Comparator to sort line.length in List
class LineComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
}
