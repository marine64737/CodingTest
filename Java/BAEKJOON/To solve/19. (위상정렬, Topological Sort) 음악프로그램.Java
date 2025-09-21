//https://www.acmicpc.net/problem/2623
//First try
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
        int a, b;
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            a = Integer.parseInt(st.nextToken());
            if (!st.hasMoreTokens()) continue;
            else {
                while (st.hasMoreTokens()){
                    b = Integer.parseInt(st.nextToken());
                    arr.get(a).add(b);
                    if (arr.get(b).contains(a)) {
                        sb.append(0).append("\n");
                        break;
                    }
                    indeq[b]++;
                    a=b;
                }
            }
            if (!sb.isEmpty()) break;
        }
        if (sb.isEmpty()){
            for (int i=1; i<=N; i++) if (indeq[i] == 0) q.add(i);
            while (!q.isEmpty()){
                int top = q.poll();
                sb.append(top).append("\n");
                for (int tmp : arr.get(top)) if (--indeq[tmp] == 0) q.add(tmp);
            }
        }
        System.out.print(sb);
        return;
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
        int a, b;
        int cnt = 0;
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if (num==0) continue;
            int prev = Integer.parseInt(st.nextToken());
            for (int j=1; j<num; j++){
                int next = Integer.parseInt(st.nextToken());
                arr.get(prev).add(next);
                indeq[next]++;
                prev=next;
            }
        }
        for (int i=1; i<=N; i++) if (indeq[i] == 0) q.add(i);
        while (!q.isEmpty()){
            int top = q.poll();
            sb.append(top).append("\n");
            cnt++;
            for (int tmp : arr.get(top)) if (--indeq[tmp] == 0) q.add(tmp);
        }
        if (cnt < N) System.out.println(0);
        else System.out.print(sb);
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

        List<Integer>[] g = new ArrayList[N+1];
        for (int i=1;i<=N;i++) g[i]=new ArrayList<>();
        int[] indeg = new int[N+1];

        // 입력: (k a1 a2 ... ak) -> (a1->a2), (a2->a3), ...
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if (k==0) continue;
            int prev = Integer.parseInt(st.nextToken());
            for (int j=1;j<k;j++){
                int cur = Integer.parseInt(st.nextToken());
                g[prev].add(cur);
                indeg[cur]++;
                prev = cur;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i=1;i<=N;i++) if (indeg[i]==0) q.add(i);

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (!q.isEmpty()){
            int u = q.poll();
            sb.append(u).append('\n');
            cnt++;
            for (int v : g[u]){
                if (--indeg[v]==0) q.add(v);
            }
        }

        // 사이클 판정: 처리 개수로
        if (cnt < N) {
            System.out.println(0);
        } else {
            System.out.print(sb);
        }
    }
}
