//https://school.programmers.co.kr/learn/courses/30/lessons/42897
class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int total = 0;
        for (int i = 0; i < money.length; i+=2){
            if (i+1 == money.length) break;
            answer += money[i];
        }
        for (int i = 1; i < money.length; i+=2){
            total += money[i];
        }
        answer = (answer > total) ? answer : total;
        total = 0;
        for (int i = 0; i < money.length; i+=2){
            if (i+1 == money.length) break;
            total += money[money.length-1-i];
        }
        answer = (answer > total) ? answer : total;
        total = 0;
        for (int i = 1; i < money.length; i+=2){
            total += money[money.length-1-i];
        }
        answer = (answer > total) ? answer : total;
        return answer;
    }
}