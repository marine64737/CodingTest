//https://www.acmicpc.net/problem/11437
//First try
import java.io.*;
import java.util.*;

class Scratch {
    static int[] parent;
    static int[] depth;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for (int i=0; i<N+1; i++) {
            parent[i] = i;
            depth[i] = i;
        }
        for (int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        int M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int a_parent = find(a, 0, N);
            int b_parent = find(b, 0, N);
            if (a_parent != b_parent) sb.append(1).append("\n");
            else sb.append(a_parent).append("\n");
        }
        System.out.print(sb);
        return;
    }
    static void union(int a, int b){
        if (a < b) {
            parent[b] = a;
            depth[b] = depth[a] + 1;
        }
        else {
            parent[a] = b;
            depth[a] = depth[b] + 1;
        }
    }
    static int find(int a, int cnt, int N){
        if (cnt == N) return parent[a];
        else return find(parent[a], cnt+1, N);
    }
}

//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {
    static List<Integer>[] g;
    static int[] parent, depth;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        g = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }

        parent = new int[N + 1];
        depth  = new int[N + 1];
        visited = new boolean[N + 1];

        // 루트는 1번이라고 가정 (문제 조건상 트리)
        dfs(1, 0);

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a, b)).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int u, int p) {
        visited[u] = true;
        parent[u] = p;
        for (int v : g[u]) {
            if (!visited[v]) {
                depth[v] = depth[u] + 1;
                dfs(v, u);
            }
        }
    }

    static int lca(int a, int b) {
        // 1) 깊이 맞추기: 더 깊은 쪽을 위로
        while (depth[a] > depth[b]) a = parent[a];
        while (depth[b] > depth[a]) b = parent[b];

        // 2) 동시에 올리기
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
}
//Second try
import java.io.*;
        import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<List<Integer>> lca = new ArrayList<>();
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] parent = new int[N+1];
        int[] depth = new int[N+1];
        boolean[] visited = new boolean[N+1];
        for (int i=0; i<=N; i++) lca.add(new ArrayList<>());
        for (int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a =Integer.parseInt(st.nextToken());
            int b =Integer.parseInt(st.nextToken());
            lca.get(a).add(b);
            lca.get(b).add(a);
        }
        dq.add(1);
        visited[1] = true;
        while (!dq.isEmpty()){
            int top = dq.poll();
            for (int next : lca.get(top)){
                if (!visited[next]){
                    visited[next] = true;
                    parent[next] = top;
                    depth[next] = depth[top]+1;
                    dq.add(next);
                }
            }
        }
        int M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            while (depth[a] > depth[b]) a = parent[a];
            while (depth[a] < depth[b]) b = parent[b];
            while (a != b){
                a = parent[a];
                b = parent[b];
            }
            sb.append(a).append("\n");
        }
        System.out.print(sb);

    }
}