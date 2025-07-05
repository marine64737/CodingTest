//https://school.programmers.co.kr/learn/courses/30/lessons/42897

//First try
//class Solution {
//    public int solution(int[] money) {
//        int answer = 0;
//        int total = 0;
//        for (int i = 0; i < money.length; i+=2){
//            if (i+1 == money.length) break;
//            answer += money[i];
//        }
//        for (int i = 1; i < money.length; i+=2){
//            total += money[i];
//        }
//        answer = (answer > total) ? answer : total;
//        total = 0;
//        for (int i = 0; i < money.length; i+=2){
//            if (i+1 == money.length) break;
//            total += money[money.length-1-i];
//        }
//        answer = (answer > total) ? answer : total;
//        total = 0;
//        for (int i = 1; i < money.length; i+=2){
//            total += money[money.length-1-i];
//        }
//        answer = (answer > total) ? answer : total;
//        return answer;
//    }
//}

//Second try
class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int dp1, dp2;
        int dp[] = new int[money.length-1];
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        for (int i = 2; i < money.length-1; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+money[i]);
        }
        dp1 = dp[money.length-2];
        dp = new int[money.length-1];
        dp[0] = money[1];
        dp[1] = Math.max(money[1], money[2]);
        for (int i = 2; i < money.length-1; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+money[i+1]);
        }
        dp2 = dp[money.length-2];
        return Math.max(dp1, dp2);
    }
}
//AI Recommend
private int rob(int[] arr) {
    int[] dp = new int[arr.length];
    dp[0] = arr[0];
    dp[1] = Math.max(arr[0], arr[1]);
    for (int i = 2; i < arr.length; i++) {
        dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
    }
    return dp[arr.length - 1];
}
public int solution(int[] money) {
    int dp1 = rob(Arrays.copyOfRange(money, 0, money.length - 1));
    int dp2 = rob(Arrays.copyOfRange(money, 1, money.length));
    return Math.max(dp1, dp2);
}
