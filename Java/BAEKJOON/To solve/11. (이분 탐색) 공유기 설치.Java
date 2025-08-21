//https://www.acmicpc.net/problem/2110
//First try
import java.io.*;
import java.util.*;

class Scratch {
    int N, C;
    public void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[]
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine().trim());
        }
        Arrays.sort(arr);
        int lo = 1;
        int hi = arr[N-1]-arr[0];
        while (lo <= hi){

        }
    }
}

//Second try
import java.io.*; import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(br.readLine().trim());
        Arrays.sort(arr);

        long lo = 1, hi = arr[N-1] - arr[0], answer = 0;
        while (lo <= hi){
            long mid = lo+((hi-lo)>>1);
            long last = arr[0];
            int num=1;
            for (int i=1; i<N; i++){
                if (arr[i] - last >= mid){
                    num++;
                    last = arr[i];
                }
            }
            if (num >= C){
                answer = mid;
                lo = mid+1;
            }
            else hi = mid-1;
        }
        System.out.println(answer);
    }
}
