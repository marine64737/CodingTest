//https://www.acmicpc.net/problem/2003

import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int index = 0;
        int sum = 0;
        for (int i=0; i<N; i++){
            sum+=arr[i];
            while (sum > M) {
                sum -= arr[index];
                index++;
            }
            if (sum == M) answer++;
        }
        System.out.print(answer);
        return;
    }
}

//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int filled = 0;
        while (filled < N) {
            if (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(br.readLine());
                continue;
            }
            arr[filled++] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        long sum = 0; // 여유롭게 long
        int answer = 0;
        for (int right = 0; right < N; right++) {
            sum += arr[right];
            while (sum > M && left <= right) {
                sum -= arr[left++];
            }
            if (sum == M) answer++;
        }
        System.out.println(answer);
    }
}
