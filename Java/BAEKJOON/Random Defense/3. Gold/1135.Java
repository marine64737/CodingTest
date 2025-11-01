//https://www.acmicpc.net/problem/1135
//Try
import java.io.*;
import java.util.*;

class Scratch {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<List<Integer>> tree = new ArrayList<>();
        Integer[] node = new Integer[N];
        for (int i=0; i<N; i++) node[i] = i;
        int[] depth = new int[N];
        int[] size = new int[N];
        int number=0;
        st.nextToken();
        int num;
        tree.add(new ArrayList<>());
        depth[number] = 0;
        int dMax=0;
        for (int i=1; i<N; i++){
            number++;
            tree.add(new ArrayList<>());
            num=Integer.parseInt(st.nextToken());
            tree.get(num).add(number);
            depth[number] = depth[num]+1;
            size[num]++;
            dMax = Math.max(dMax, depth[number]);
        }
        Arrays.sort(node, (a, b) -> (depth[a] == depth[b]) ? size[b]-size[a] : depth[a]-depth[b]);
        for (int i=dMax; i>=0; i--){
            ArrayList<Integer> arr = new ArrayList<>();
            for (int n : node) if (depth[n] == i) arr.add(n);
            for (int n : arr) {
                int maxSize = 0;
                int nodeSize = size[n];
                int tmp = nodeSize;
                PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(size[b], size[a]));
                pq.addAll(tree.get(n));
                while (!pq.isEmpty()) {
                    int top = pq.poll();
                    maxSize = Math.max(maxSize, nodeSize + Math.max(size[top] - (--tmp), 0));
                }
                size[n] = maxSize;
            }
        }
        System.out.print(size[0]);
    }
}

//AI Recommend
import java.io.*;
        import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] child;
    static int[] dp;          // dp[u] = u 서브트리 전파 최소 시간
    static int[] order;       // post-order 담을 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        child = new ArrayList[N];
        for (int i = 0; i < N; i++) child[i] = new ArrayList<>();

        st.nextToken(); // root의 부모(-1) 스킵
        for (int i = 1; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            child[p].add(i);
        }

        dp = new int[N];
        // 비재귀 post-order: 간단히 스택 2개
        int[] stack = new int[N];
        int[] out   = new int[N];
        int top = 0, outTop = 0;
        stack[top++] = 0; // root = 0
        while (top > 0) {
            int u = stack[--top];
            out[outTop++] = u;
            for (int v : child[u]) stack[top++] = v;
        }
        // out에는 root->리프 순서가 들어 있으니, 거꾸로 처리 = post-order
        for (int idx = outTop - 1; idx >= 0; idx--) {
            int u = out[idx];
            if (child[u].isEmpty()) { dp[u] = 0; continue; } // 리프

            // 자식 dp를 모아 내림차순 정렬
            ArrayList<Integer> vals = new ArrayList<>(child[u].size());
            for (int v : child[u]) vals.add(dp[v]);
            vals.sort(Comparator.reverseOrder());

            int best = 0;
            for (int i = 0; i < vals.size(); i++) {
                // i번째로 알리면 i만큼 지연. u->child 한 단계(+1)
                best = Math.max(best, vals.get(i) + i + 1);
            }
            dp[u] = best;
        }
        System.out.println(dp[0]);
    }
}
