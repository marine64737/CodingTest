//https://www.acmicpc.net/problem/14225
//First try
import java.io.*;
import java.math.BigInteger;
import java.util.*;

class Scratch {
    static int N;
    static BigInteger bit;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        bit = BigInteger.ZERO;
        int answer = 0;
        int max = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int a : arr) {
            bit = bit.or(bit.shiftLeft(a)).setBit(a-1);
            max+=a;
        }
        while (answer < max && bit.testBit(answer)) answer++;
        System.out.print(answer+1);
    }
}