//https://school.programmers.co.kr/learn/courses/30/lessons/131704
//First solution
import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int index = 1;
        Stack<Integer> temp = new Stack<Integer>();
        while(index <= order.length && answer < order.length){
            if(order[answer] != index){
                if (!temp.isEmpty()){
                    if (order[answer] == temp.peek()){
                        temp.pop();
                        answer++;
                    }
                    else{
                        temp.push(index);
                        index++;
                    }
                }
                else{
                    temp.push(index);
                    index++;
                }
            }
            else {
                if (index < order.length) index++;
                answer++;
            }
        }
        return answer;
    }
}

//Refactoring Ver.
import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int index = 1;
        Stack<Integer> temp = new Stack<Integer>();
        while(index <= order.length && answer < order.length){
            if(order[answer] == index) {
                if (index < order.length) index++;
                answer++;
            }
            else if (!temp.isEmpty() && order[answer] == temp.peek()){
                temp.pop();
                answer++;
            }
            else{
                temp.push(index);
                index++;
            }
        }
        return answer;
    }
}