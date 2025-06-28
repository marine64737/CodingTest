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

//Second try
    public static int[] solution(int[] prices) {
    int n = prices.length;
    int[] answer = new int[n];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {
        // If current price < price at stack top, calculate time and pop
        while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
            int top = stack.pop();
            answer[top] = i - top;
        }
        stack.push(i);
    }

    // For prices that never dropped
    while (!stack.isEmpty()) {
        int top = stack.pop();
        answer[top] = n - top - 1;
    }

    return answer;
}