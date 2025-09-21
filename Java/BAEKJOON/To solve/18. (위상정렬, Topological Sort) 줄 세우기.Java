//https://www.acmicpc.net/problem/2252
//First try
import java.io.*; import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> arr = new ArrayList<>();
        int size=0;
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (arr.isEmpty()) {
                arr.add(a);
                arr.add(b);
                size+=2;
            } else {
                if (!arr.contains(a)) {
                    if (arr.contains(b)) arr.add(arr.indexOf(b), a);
                    else arr.add(a);
                    size++;
                }
                if (!arr.contains(b)) {
                    arr.add(b);
                    size++;
                }
                if (arr.indexOf(a) > arr.indexOf(b)){
                    int index_a = arr.indexOf(a);
                    int index_b = arr.indexOf(b);
                    arr.set(index_a, b);
                    arr.set(index_b, a);
                }
            }
        }
        for (int i=0; i<size; i++){
            if (i==0) sb.append(arr.get(i));
            else sb.append(" ").append(arr.get(i));
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();
        int[] indeg = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            indeg[b]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) if (indeg[i] == 0) q.add(i);

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int u = q.poll();
            sb.append(u).append(' ');
            for (int v : g[u]) {
                if (--indeg[v] == 0) q.add(v);
            }
        }

        // (2252는 DAG가 들어옵니다. 일반론으론 사이클이면 모든 노드 못 뽑음)
        System.out.print(sb.toString().trim());
    }
}
//Second try
import java.io.*; import java.util.*;

class Scratch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] indeq = new int[N+1];
        List<List<Integer>> arr = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i=0; i<=N; i++) arr.add(new ArrayList<>());
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.get(a).add(b);
            indeq[b]++;
        }
        for (int i=1; i<=N; i++) if (indeq[i] == 0) q.add(i);
        while (!q.isEmpty()){
            int top = q.poll();
            sb.append(top).append(" ");
            for (int tmp : arr.get(top)) if (--indeq[tmp] == 0) q.add(tmp);
        }
        System.out.print(sb);
        return;
    }
}
