//https://www.acmicpc.net/problem/2493

//First try
import java.io.*; import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] top = new int[N];
        int[] answer = new int[N];
        int bot = 0;
        st = new StringTokenizer(br.readLine());
        top[0] = Integer.parseInt(st.nextToken());
        sb.append(0);
        for (int i=1; i<N; i++){
            top[i] = Integer.parseInt(st.nextToken());
            while (top[bot] < top[i]) bot++;
            for (int j=i-1; j>=bot; j--){
                if (top[j] > top[i]){
                    answer[i] = j+1;
                    break;
                }
            }
            sb.append(" ").append(answer[i]);
        }
        System.out.print(sb);
        return;
    }
}

//Second Try
import java.io.*; import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] top = new int[N];
        int bot = -1;
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            top[i] = Integer.parseInt(st.nextToken());
            while (bot >= 0 && top[bot] < top[i]) bot--;
            if (i==0) sb.append(0);
            else {
                if (top[i - 1] > top[i]) bot = i - 1;
                sb.append(" ").append((bot >= 0) ? bot+1 : 0);
            }
        }
        System.out.print(sb);
        return;
    }
}
