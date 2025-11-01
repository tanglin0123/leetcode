package practice.amplitude.problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncodingDecoding {
    public String encode(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        Map<String, String> map = new HashMap<>();

        List<String> keys = new ArrayList<>();
        List<String> indices = new ArrayList<>();

        for (int i = 0; i < arr.length; ++i) {
            String idx = map.get(arr[i]);

            if(idx == null) { // new key
                map.put(arr[i], "" + i);
                keys.add(arr[i]);
                indices.add("" + i); 
            } else { // existing key
                indices.add(idx); 
            }
        }

        return String.join(",", keys) + ":" + String.join(",", indices);
    }

    public String[] decode(String encodedString) {
        if (encodedString == null || encodedString.isEmpty()) {
            return new String[0];
        } 

        String[] ss = encodedString.split(":");
        
        String[] keys = ss[0].split(",");
        String[] indices = ss[1].split(",");

        String[] result = new String[indices.length];
        
        for(int i = 0; i < indices.length; ++i) {
            result[i] = keys[Integer.parseInt(indices[i])];
        }

        return result;
    }


    public static void main(String[] args) {
        EncodingDecoding ende = new EncodingDecoding();

        String[] input = {"apple", "orange", "melon", "apple"};

        String encodedString = ende.encode(input);

        System.out.println(encodedString);

        String[] decodedArray = ende.decode(encodedString);

        System.out.println(Arrays.toString(decodedArray));
    }
}
