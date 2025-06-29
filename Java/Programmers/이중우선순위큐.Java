//https://school.programmers.co.kr/learn/courses/30/lessons/42628
import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        Queue<String> oper = new LinkedList<>();
        PriorityQueue<Integer> priority = new PriorityQueue<>();
        for (int i=0; i<operations.length; i++){
            oper.add(operations[i]);
        }
        while (!oper.isEmpty()){
            String operation = oper.poll();
            String[] arr = operation.split(" ");
            String op0 = arr[0];
            int op1 = Integer.parseInt(arr[1]);
            if (op0.equals("I")){
                priority.add(op1);
            }
            else {
                if (!priority.isEmpty()){
                    if (op1 > 0){
                        priority.remove(Collections.max(priority));
                    }
                    else {
                        priority.remove(Collections.min(priority));
                    }
                }
            }
        }
        if (!priority.isEmpty()){
            answer[0] = Collections.max(priority);
            answer[1] = Collections.min(priority);
        }
        else {
            answer[0] = 0;
            answer[1] = 0;
        }
        return answer;
    }
}