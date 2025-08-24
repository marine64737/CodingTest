///https://www.acmicpc.net/problem/2805

import java.io.*;
import java.util.*;

class Scratch {
    static int N;
    static int M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int lo = 0;
        int hi = 2000000000;
        int answer=0;
        while (lo <= hi){
            int mid = lo + ((hi - lo) >> 1);
            long sum = 0;
            for (int i=0; i<N; i++){
                if (arr[i] > mid) sum += arr[i] - mid;
            }
            if (sum >= M) {
                answer = mid;
                lo = mid + 1;
            }
            else hi = mid - 1;
        }
        System.out.print(answer);
        return;
    }
}