//https://www.acmicpc.net/problem/1759

import java.io.*;
import java.util.*;

class Scratch {
    static int L;
    static int C;
    static int[] word;
    static String[] ch;
    static String[] target;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ch = br.readLine().split(" ");
        Arrays.sort(ch);
        target = new String[L];
        word = new int[2];
        dfs(0, 0);
        System.out.print(sb);
    }
    public static void dfs(int depth, int index){
        if (depth == L){
            if (word[0] >= 1 && word[1] >= 2) {
                for (int i = 0; i < L; i++) sb.append(target[i]);
                sb.append('\n');
            }
        }
        else {
            for (int i=index; i<C; i++){
                target[depth] = ch[i];
                if ("aeiou".contains(ch[i])) word[0]++;
                else word[1]++;
                dfs(depth+1, i+1);
                if ("aeiou".contains(ch[i])) word[0]--;
                else word[1]--;
            }
        }
    }
}