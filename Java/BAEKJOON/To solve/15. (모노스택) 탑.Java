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
//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int[] ans = new int[N];
        int[] stackH = new int[N];   // 높이 스택
        int[] stackIdx = new int[N]; // 1-based index 스택
        int top = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());

            // 왼쪽에서 현재 탑을 "막지 못하는" (작은) 애들은 제거
            while (top >= 0 && stackH[top] < cur) top--;

            // 이제 top가 가리키는 게 왼쪽에서 가장 가까운 >= cur (없으면 0)
            ans[i] = (top >= 0 ? stackIdx[top] : 0);

            // 현재 탑을 스택에 push (다음 애들의 왼쪽 후보가 됨)
            stackH[++top] = cur;
            stackIdx[top] = i + 1; // 문제는 1-based 인덱스 출력
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (i > 0) sb.append(' ');
            sb.append(ans[i]);
        }
        System.out.print(sb.toString());
    }
}
