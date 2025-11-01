//https://www.acmicpc.net/problem/14575
//First try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());
        int min = 0;
        int max = 0;
        long sum_low = 0;
        long sum_high = 0;
        int[][] arr = new int[N][2];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a; arr[i][1] = b;
            sum_low+=a;
            sum_high+=b;
            min = Math.max(min, a);
            max = Math.max(max, b);
        }
        if (sum_low > T || sum_high < T) {
            System.out.print(-1);
            return;
        }
        for (int i=min; i<=max; i++){
            long sum=0;
            for (int j = 0; j <N; j++){
                sum+=Math.min(arr[j][1], i);
            }
            if (sum >= T) {
                System.out.print(i);
                return;
            }
        }

        return;
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());
        int min = 0;
        int max = 0;
        long sum_low = 0;
        long sum_high = 0;
        int[][] arr = new int[N][2];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a; arr[i][1] = b;
            sum_low+=a;
            sum_high+=b;
            min = Math.max(min, a);
            max = Math.max(max, b);
        }
        if (sum_low > T || sum_high < T) {
            System.out.print(-1);
            return;
        }
        for (int i=min; i<=max; i++){
            long sum=0;
            for (int j = 0; j <N; j++){
                sum+=Math.min(arr[j][1], i);
            }
            if (sum >= T) {
                System.out.print(i);
                return;
            }
        }
        return;
    }
}

//Second try_2
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Long.parseLong(st.nextToken());
        int min = 0;
        int max = 0;
        long sum_low = 0;
        long sum_high = 0;
        int[][] arr = new int[N][2];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i][0] = a; arr[i][1] = b;
            sum_low+=a;
            sum_high+=b;
            min = Math.max(min, a);
            max = Math.max(max, b);
        }
        if (sum_low > T || sum_high < T) {
            System.out.print(-1);
            return;
        }
        int lo = min;
        int hi = max;
        int ans=hi;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            long sum = 0;
            for (int i=0; i<N; i++) sum+=Math.min(arr[i][1], mid);
            if (sum >= T){
                ans = mid;
                hi = mid-1;
            }
            else {
                lo = mid+1;
            }
        }
        System.out.print(ans);
        return;
    }
}