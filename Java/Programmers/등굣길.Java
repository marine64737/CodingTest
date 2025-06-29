//https://school.programmers.co.kr/learn/courses/30/lessons/42898

//First try
//class Solution {
//    public int solution(int m, int n, int[][] puddles) {
//        int answer = fact(m+n-2)/fact(m-1)/fact(n-1);
//        for (int i = 0; i<puddles.length; i++){
//            int p_m = puddles[i][0];
//            int p_n = puddles[i][1];
//            answer -= fact(p_m+p_n-2)/fact(p_m-1)/fact(p_n-1)*
//                    fact(m+n-p_m-p_n)/fact(m-p_m)/fact(n-p_n);
//        }
//        return answer;
//    }
//    public int fact(int n){
//        int result = 1;
//        for (int i=1; i<=n; i++){
//            result*=i;
//        }
//        return result;
//    }
//}

//Second Try
//import java.util.*;
//class Solution {
//    public int solution(int m, int n, int[][] puddles) {
//        int answer = 0;
//        int[][] cases = new int[n][m];
//        for (int[] i : cases) Arrays.fill(i, 1);
//        for (int i=0; i<puddles.length; i++){
//            cases[puddles[i][1]-1][puddles[i][0]-1] = 0;
//        }
//        for (int i = 1; i<n; i++){
//            for (int j = 1; j < m; j++){
//                if (cases[i][j] == 0){
//                    continue;
//                }
//                else {
//                    cases[i][j] = cases[i-1][j]+cases[i][j-1];
//                }
//            }
//        }
//        return cases[n-1][m-1]%1000000007;
//    }
//}

//Third try
//import java.util.*;
//class Solution {
//    public int solution(int m, int n, int[][] puddles) {
//        int answer = 0;
//        int[][] cases = new int[n][m];
//        boolean[][] isPuddle = new boolean[n][m];
//        for (int[] p : puddles){
//            isPuddle[p[1]][p[0]] = true;
//        }
//        for (int i = 0; i<n-1; i++){
//            for (int j = 0; j < m-1; j++){
//                if (isPuddle[i][j]){
//                    continue;
//                }
//                if (i == 0 && j == 0) cases[i][j] = 1;
//                if (i-1 < 0){
//                    cases[i][j] = cases[i][j-1];
//                }
//                else if (j-1 < 0){
//                    cases[i][j] = cases[i-1][j];
//                }
//                else {
//                    cases[i][j] = cases[i-1][j]+cases[i][j-1];
//                }
//            }
//        }
//        return cases[n-1][m-1]%1000000007;
//    }
//}

//Fourth try
import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] cases = new int[n][m];
        boolean[][] isPuddle = new boolean[n][m];
        for (int[] p : puddles){
            isPuddle[p[1]-1][p[0]-1] = true;
        }
        for (int i = 0; i<n; i++){
            for (int j = 0; j < m; j++){
                if (isPuddle[i][j]){
                    continue;
                }
                if (i == 0 && j == 0) cases[i][j] = 1;
                else if (i == 0){
                    cases[i][j] = cases[i][j-1];
                }
                else if (j == 0){
                    cases[i][j] = cases[i-1][j];
                }
                else {
                    cases[i][j] = cases[i-1][j]+cases[i][j-1];
                    cases[i][j] %= 1000000007;
                }
            }
        }
        return cases[n-1][m-1];
    }
}