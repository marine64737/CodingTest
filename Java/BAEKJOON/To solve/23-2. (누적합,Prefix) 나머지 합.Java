//https://www.acmicpc.net/problem/10986
//First try
import java.io.*; import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {
        int answer=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            if (i==0) arr[i]=Integer.parseInt(st.nextToken());
            else arr[i] = arr[i-1]+Integer.parseInt(st.nextToken());
            if (arr[i] % M == 0) answer++;
        }
        for (int k = 1; k < N; k++){
            for (int i=k; i<N; i++){
                if ((arr[i]-arr[i-k])%M == 0) answer++;
            }
        }
        System.out.print(answer);
        return;
    }
}
