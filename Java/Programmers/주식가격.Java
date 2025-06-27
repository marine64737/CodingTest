//https://school.programmers.co.kr/learn/courses/30/lessons/42584
import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        for (int i=0; i<prices.length; i++){
            while (!stack.isEmpty()&&prices[i]<prices[stack.peek()]){
                arr.add(i - stack.peek());
                stack.pop();
            }
            while (!stack.isEmpty()&&prices[prices.length-1]>=prices[stack.peek()]){
                arr.add(prices.length-1 - stack.peek());
                stack.pop();
            }
            stack.add(i);
        }
        arr.add(0);
        for (int i=0; i<prices.length; i++){
            answer[i] = arr.get(i);
        }
        
        return answer;
    }
}