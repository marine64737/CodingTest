//https://www.acmicpc.net/problem/10384

import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++){
            int[] alphabet = new int[26];
            for (char c : br.readLine().toCharArray()){
                if (c >= 'a' && c <= 'z') alphabet[c-'a']++;
                else if (c >= 'A' && c <= 'Z') alphabet[c-'A']++;
            }
            int num = Integer.MAX_VALUE;
            for (int n : alphabet) num = Math.min(num, n);
            if (num == 0) sb.append("Case ").append(i+1).append(": ").append("Not a pangram").append("\n");
            else if (num == 1) sb.append("Case ").append(i+1).append(": ").append("Pangram!").append("\n");
            else if (num == 2) sb.append("Case ").append(i+1).append(": ").append("Double pangram!!").append("\n");
            else if (num == 3) sb.append("Case ").append(i+1).append(": ").append("Triple pangram!!!").append("\n");
        }
        System.out.print(sb);
        return;
    }
}