//https://www.acmicpc.net/problem/17271
//Try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] dp = new int[N+1];
        int C = 1000000007;
        if (N < M) {
            System.out.print(1);
            return;
        }
        for (int i=0; i<=N; i++){
            if (i < M) dp[i] = 1;
            else dp[i] = (dp[i-1]+dp[i-M])%C;
        }
        System.out.print(dp[N]);
    }
}