//https://school.programmers.co.kr/learn/courses/30/lessons/12947
class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int num = x;
        int total = 0;
        while (num > 0){
            total += num%10;
            num /= 10;
        }
        return (x%total > 0) ? false : true;
    }
}