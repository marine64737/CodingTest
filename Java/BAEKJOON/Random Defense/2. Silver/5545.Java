//https://www.acmicpc.net/problem/5545
//First try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(br.readLine());
        Integer[] cal = new Integer[N];
        for (int i=0; i<N; i++) cal[i] = Integer.parseInt(br.readLine());
        Arrays.sort(cal, Collections.reverseOrder());
        int priceSum=A;
        int calSum=C;
        double aver = (double) calSum/priceSum;
        double max = aver;
        for (int i=0; i<N; i++){
            priceSum += B;
            calSum+= cal[i];
            aver = (double) calSum/priceSum;
            if (max < aver) max = aver;
        }
        System.out.print((int)max);
    }
}

//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(br.readLine());
        Integer[] cal = new Integer[N];
        for (int i=0; i<N; i++) cal[i] = Integer.parseInt(br.readLine());
        Arrays.sort(cal, Collections.reverseOrder());
        int priceSum=A;
        int calSum=C;
        double aver = (double) calSum/priceSum;
        double max = aver;
        for (int i=0; i<N; i++){
            priceSum += B;
            calSum+= cal[i];
            aver = (double) calSum/priceSum;
            if (max < aver) max = aver;
            else {
                System.out.print((int)max);
                return;
            }
        }
        System.out.print((int)max);
    }
}