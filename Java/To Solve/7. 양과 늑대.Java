//3세트 (완전탐색 + 순열/조합)
//https://school.programmers.co.kr/learn/courses/30/lessons/92343

//First try
import java.util.*;
class Solution {
    int sheep = 0;
    int wolf = 0;
    boolean pass = false;
    public int solution(int[] info, int[][] edges) {
        List<List<Integer>> arr = new ArrayList<>();
        boolean[] visited = new boolean[info.length];
        for (int i=0; i<info.length; i++) arr.add(new ArrayList<>());
        for (int i=0; i<edges.length; i++){
            arr.get(edges[i][0]).add(edges[i][1]);
        }
        sheep++;
        while (true){
            dfs(arr, info, visited, 0);
            if (!pass) break;
            pass = false;
        }
        return sheep;
    }
    public void dfs(List<List<Integer>> arr, int[] info, boolean[] visited, int index){
        if (sheep <= wolf) return;
        visited[index] = true;
        for (int i : arr.get(index)){
            System.out.println("index, i, sheep, wolf: "+index+", "+i+", "+sheep+", "+wolf);
            if (!visited[i]) {
                if (info[i] == 0) {
                    pass = true;
                    sheep++;
                    dfs(arr, info, visited, i);
                }
                else if (info[i] == 1 && sheep > wolf + 1) {
                    wolf++;
                    dfs(arr, info, visited, i);
                }
            }
            else dfs(arr, info, visited, i);
        }
        return;
    }
}

//Second try
import java.util.*;
class Solution {
    int max_sheep = 0;
    public int solution(int[] info, int[][] edges) {
        List<List<Integer>> arr = new ArrayList<>();
        for (int i=0; i<info.length; i++) arr.add(new ArrayList<>());
        for (int i=0; i<edges.length; i++){
            arr.get(edges[i][0]).add(edges[i][1]);
        }
        dfs(arr, info, 0, 0, 0, new ArrayList<>(arr.get(edges[0][0])));
        return max_sheep;
    }
    public void dfs(List<List<Integer>> arr, int[] info, int sheep, int wolf, int index, List<Integer> nextNodes){
        if (info[index] == 0) sheep++;
        else wolf++;
        if (sheep <= wolf) return;
        max_sheep = Math.max(max_sheep, sheep);
        for (int i : nextNodes){
            List<Integer> nextNodes_copy = new ArrayList<>(nextNodes);
            nextNodes_copy.remove(Integer.valueOf(i));
            nextNodes_copy.addAll(arr.get(i));
            dfs(arr, info, sheep, wolf, i, nextNodes_copy);
        }
        return;
    }
}

//AI Recommend
public class Solution {
    int maxSheep = 0;

    public int solution(int[] info, int[][] edges) {
        List<Integer>[] graph = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        dfs(0, 0, 0, new boolean[info.length], graph, info, new ArrayList<>(List.of(0)));

        return maxSheep;
    }

    void dfs(int idx, int sheep, int wolf, boolean[] visited, List<Integer>[] graph, int[] info, List<Integer> nextNodes) {
        if (info[idx] == 0) sheep++;
        else wolf++;

        if (wolf >= sheep) return;
        maxSheep = Math.max(maxSheep, sheep);
        visited[idx] = true;

        // 방문 가능한 다음 노드 갱신
        List<Integer> updatedNext = new ArrayList<>(nextNodes);
        updatedNext.remove(Integer.valueOf(idx)); // 현재 노드는 제외
        updatedNext.addAll(graph[idx]); // 현재 노드의 자식 노드 추가

        for (int next : updatedNext) {
            if (!visited[next]) {
                boolean[] newVisited = visited.clone();
                dfs(next, sheep, wolf, newVisited, graph, info, updatedNext);
            }
        }
    }
}

//Third try
class Solution {
    int max_sheep = 0;
    public int solution(int[] info, int[][] edges) {
        List<List<Integer>> arr = new ArrayList<>();
        for (int i=0; i<info.length; i++) arr.add(new ArrayList<>());
        for (int i=0; i<edges.length; i++){
            arr.get(edges[i][0]).add(edges[i][1]);
        }
        dfs(arr, info, 0, 0, 0, new ArrayList<>(List.of(0)));
        return max_sheep;
    }
    public void dfs(List<List<Integer>> arr, int[] info, int sheep, int wolf, int index, List<Integer> nextNodes){
        if (info[index] == 0) sheep++;
        else wolf++;
        if (sheep <= wolf) return;
        max_sheep = Math.max(max_sheep, sheep);
        List<Integer> nextNodes_copy = new ArrayList<>(nextNodes);
        nextNodes_copy.remove(Integer.valueOf(index));
        nextNodes_copy.addAll(arr.get(index));
        for (int i : nextNodes_copy){
            dfs(arr, info, sheep, wolf, i, nextNodes_copy);
        }
        return;
    }
}