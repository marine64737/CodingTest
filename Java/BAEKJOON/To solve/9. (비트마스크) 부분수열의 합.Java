//https://www.acmicpc.net/problem/1182
//First try
import java.io.*;
import java.util.*;

class Scratch {
    static int N, S;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i=1; i<(1<<N); i++){
            int sum = 0;
            for (int j=0; j<N; j++) if ((i & (1<<j)) != 0) sum+=arr[j];
            if (sum == S) answer++;
        }
        System.out.print(answer);
    }
}