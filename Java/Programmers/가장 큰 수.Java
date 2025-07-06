//https://school.programmers.co.kr/learn/courses/30/lessons/42746

//AI Recommend
public static String solution(int[] numbers) {
    String answer = "";
    String[] nums = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .toArray(String[]::new);

    Arrays.sort(nums, (a, b) -> (b+a).compareTo(a+b));

    for (int i=0; i< nums.length; i++){
        answer += nums[i];
    }

    return answer;
}

//Second Try(Fail)
import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int number : numbers){
            nums.add(number);
        }
        while (!nums.isEmpty()){
            String s = Integer.toString(nums.get(0));
            int temp = 0;
            for (int i = 1; i < nums.size(); i++){
                String s1 = Integer.toString(nums.get(i));
                if ((s+s1).compareTo(s1+s) < 0) {
                    temp = i;
                    s = s1;
                }
            }
            nums.remove(temp);
            answer+=s;
        }
        return answer;
    }
}

//Third Try
import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++){
            nums[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(nums, (a,b) -> (b+a).compareTo(a+b));
        for (String num : nums){
            answer += num;
        }
        if (nums[0].equals("0")) return "0";
        return answer;
    }
}

//Fourth Try
import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] nums = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);
        Arrays.sort(nums, (a,b) -> (b+a).compareTo(a+b));
        for (String num : nums){
            answer += num;
        }
        if (nums[0].equals("0")) return "0";
        return answer;
    }
}