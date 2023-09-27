package org.hw7;

public class StringReverse {
    public static String reverse(String src){
        String newSrc = "";
        char[] chars = src.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            newSrc += chars[i];
        }
        return newSrc;
    }

    public static String reverse(String src, String dest){
        String newSrc = "";
        int counter = 0;
        int start = src.indexOf(dest);
        dest = reverse(dest);
        int end = start + dest.length();
        char[] chars = src.toCharArray();
        char[] charsDest = dest.toCharArray();
        if(start != -1){
            for (int i = start; i < end; i++) {
                chars[i] = charsDest[counter];
                counter++;
            }
        }
        for (int i = 0; i < chars.length; i++) {
            newSrc += chars[i];
        }
        return newSrc;
    }

    public static String reverse(String src, int firstIndex, int lastIndex){
        String newSrc = "";
        int counter = 0;
        int start = firstIndex;
        int end = lastIndex;
        String dest = reverse(src.substring(firstIndex, lastIndex));
        char[] chars = src.toCharArray();
        char[] charsDest = dest.toCharArray();
        if(start != -1){
            for (int i = start; i < end; i++) {
                chars[i] = charsDest[counter];
                counter++;
            }
        }
        for (int i = 0; i < chars.length; i++) {
            newSrc += chars[i];
        }
        return newSrc;
    }

}
