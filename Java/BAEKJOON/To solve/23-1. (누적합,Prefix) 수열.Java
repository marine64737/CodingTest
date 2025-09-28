//https://www.acmicpc.net/problem/2559
//First try
import java.io.*; import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        long sum=0;
        int cnt=0;
        long ans=Integer.MIN_VALUE;
        for (int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum+=arr[i];
            cnt++;
            if (cnt > K){
                sum-=arr[i-K];
                cnt--;
            }
            if (i >= K-1) ans = Math.max(ans, sum);
        }
        System.out.print(ans);
        return;
    }
}
