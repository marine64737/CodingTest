//https://www.acmicpc.net/problem/34242
//First try
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        int len = st.length();
        int[] emotion = new int[len];
        boolean[] visited = new boolean[len];
        int max=0;
        int plus =0;
        int minus = 0;
        for (int i=0; i<len; i++) {
            if (!visited[i]){
                if (st.startsWith("-^-", i)) {
                    for (int index=i; index< Math.min(i+3,len); index++) visited[index] = true;
                    emotion[i]--;
                    minus++;
                }
                else if (st.startsWith("+^+", i)) {
                    for (int index=i; index<Math.min(i+3,len); index++) visited[index] = true;
                    emotion[i]++;
                }
                else if (st.startsWith("+-^+", i) ||
                        st.startsWith("+^-+", i) ||
                        st.startsWith("+^^+", i)){
                    for (int index=i; index<Math.min(i+4,len); index++) visited[index] = true;
                    plus++;
                }
                max+=emotion[i];
            }
        }
        if (plus > 0 || minus > 0) max+=1;
        System.out.print(max);
    }
}
//AI Recommend
import java.io.*;
import java.util.*;

public class Main {
    static int val(char[] s, int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0 || c >= s.length) return 0;
        if (s[b] != '^') return 0;
        if (s[a] == '+' && s[c] == '+') return 1;
        if (s[a] == '-' && s[c] == '-') return -1;
        return 0;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().trim().toCharArray();
        int n = s.length;

        // tri[k] = s[k..k+2]의 기여값
        int[] tri = new int[Math.max(0, n - 2)];
        int base = 0;
        for (int k = 0; k + 2 < n; k++) {
            tri[k] = val(s, k, k + 1, k + 2);
            base += tri[k];
        }

        int best = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // 사라지는 3개: 시작 i-2, i-1, i
            int lost = 0;
            if (i - 2 >= 0 && i - 2 < tri.length) lost += tri[i - 2];
            if (i - 1 >= 0 && i - 1 < tri.length) lost += tri[i - 1];
            if (i >= 0 && i < tri.length)         lost += tri[i];

            // 새로 생기는 2개: (i-2, i-1, i+1), (i-1, i+1, i+2)
            int gain = 0;
            if (i - 2 >= 0 && i - 1 >= 0 && i + 1 < n) gain += val(s, i - 2, i - 1, i + 1);
            if (i - 1 >= 0 && i + 1 < n && i + 2 < n)  gain += val(s, i - 1, i + 1, i + 2);

            int candidate = base + (gain - lost);
            if (candidate > best) best = candidate;
        }

        System.out.println(best);
    }
}
//Second try
import java.io.*;

class Scratch {
    static int val(char[] s, int a, int b, int c){
        if (a < 0 || b < 0 || c < 0 || c >= s.length) return 0;
        else if (s[b] != '^') return 0;
        else if (s[a] == '+' && s[c] == '+') return 1;
        else if (s[a] == '-' && s[c] == '-') return -1;
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        int len = arr.length;
        int[] score = new int[len];
        int best = 0;
        int max = Integer.MIN_VALUE;
        for (int i=0; i<arr.length; i++){
            score[i] =  val(arr, i, i+1, i+2);
            best += score[i];
        }
        for (int i=0; i<len; i++){
            int loss = 0;
            int gain = 0;
            if (i-2 >= 0 && i-2 < len - 2) loss += score[i-2];
            if (i-1 >= 0 && i-1 < len - 2) loss += score[i-1];
            if (i < len - 2) loss += score[i];

            if (i-2 >= 0 && i+1 <len) gain += val(arr, i-2, i-1, i+1);
            if (i-1 >= 0 && i+2 <len) gain += val(arr, i-1, i+1, i+2);
            int temp = best + gain - loss;
            if (max < temp) max = temp;
        }
        System.out.print(max);
    }
}