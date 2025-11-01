//https://www.acmicpc.net/problem/2240
//First try
import java.io.*;
import java.util.StringTokenizer;

class Main {
    static BufferedReader br;
    static int sum;
    static int max;
    static int T, W;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sum = 0;
        max = 0;
        arr = new int[T];
        visited = new  boolean[T];
        dfs(1, 0);
        System.out.print(max);
    }
    static void dfs(int cur, int depth) throws IOException {
        if (depth == T) {
            max = Math.max(max, sum);
            return;
        }
        if (!visited[depth]){
            visited[depth] = true;
            arr[depth] = Integer.parseInt(br.readLine());
        }
        int n = arr[depth];
        if (n == cur) {sum++; dfs(n, depth+1); sum--;}
        else if (W>0){
            for (int i=0; i<2; i++){
                if (i==0) {
                    W--;
                    sum++;
                    dfs(n, depth+1);
                    W++;
                    sum--;
                }
                else dfs(cur, depth+1);
            }
        }
    }
}
//Second try
import java.io.*;
        import java.util.StringTokenizer;

class Scratch {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr = new int[T+1];
        int[][] dp = new int[T+1][W+1];
        for (int i=1; i<=T; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int t=1; t<=T; t++){
            int mMax = Math.min(W, t);
            for (int w=0; w<=mMax; w++){
                int pos = (w%2==0) ? 1 : 2;
                int gain = (pos == arr[t]) ? 1 : 0;
                if (w==0) dp[t][w] = dp[t-1][w] + gain;
                else dp[t][w] = Math.max(dp[t-1][w-1], dp[t-1][w]) + gain;
            }
        }
        int ans=0;
        for (int w=0; w<=W; w++) ans = Math.max(ans, dp[T][w]);
        System.out.print(ans);
    }
}