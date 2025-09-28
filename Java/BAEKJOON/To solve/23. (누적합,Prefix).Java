//https://www.acmicpc.net/problem/11659
//First try -> for문으로 일일이 덧셈
//Second try
import java.io.*; import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        int sum=0;
        for (int i=1; i<=N; i++){
            sum+=Integer.parseInt(st.nextToken());
            arr[i] = sum;
        }
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(arr[end] - arr[start-1]).append("\n");
        }
        System.out.print(sb);
        return;
    }
}
