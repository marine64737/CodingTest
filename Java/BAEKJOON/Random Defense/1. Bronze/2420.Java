//https://www.acmicpc.net/problem/2420
import java.io.*;
import java.util.*;
class Scratch {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        System.out.print((a>b)? a-b : b-a);
    }
}