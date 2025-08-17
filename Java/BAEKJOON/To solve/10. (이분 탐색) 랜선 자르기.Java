//https://www.acmicpc.net/problem/1654
//First try
import java.io.*;
import java.util.*;

class Scratch {
    static int K;
    static long N;
    static int max=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        long[] arr = new long[K];
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int arr_max = Integer.MIN_VALUE;
        for (int i:arr) arr_max = Math.max(arr_max, i);
        for (int i=1; i<=arr_max; i++){
            long number=0;
            for (int x:arr) number+=x/i;
            if (number >= N) max=Math.max(max, i);
        }
        System.out.print(max);
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    static int K, N;
    static long max=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        long[] arr = new long[K];
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        long lo = 1;
        long hi = max;
        long answer = 0;
        while (lo <= hi){
            int number=0;
            long mid = lo+((hi - lo)>>1);
            for (long x:arr) number+=x/mid;
            if (number >= N) {
                answer = mid;
                lo = mid+1;
            }
            else hi = mid - 1;
        }
        System.out.print(answer);
    }
}