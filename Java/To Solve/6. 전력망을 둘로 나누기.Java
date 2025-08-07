//3세트 (완전탐색 + 순열/조합)
//https://school.programmers.co.kr/learn/courses/30/lessons/86971

//First try
class Solution {
    int number = 1;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < wires.length; i++){
            int[] route = {wires[i][0], wires[i][1]};
            boolean[] visited = new boolean[n];
            if (i == 0) {
                System.out.println("Start: "+i);
                visited[wires[1][0]-1] = true;
                dfs(wires, route, visited, 1);
            }
            else {
                System.out.println("Start: "+i);
                visited[wires[0][0]-1] = true;
                dfs(wires, route, visited, 0);
            }
            int dif = Math.abs(n-2*number);
            answer = Math.min(answer, dif);
            number = 1;
        }
        return answer;
    }
    public void dfs(int[][] wires, int[] route, boolean[] visited, int index){
        if (wires[index][0] == route[0] && wires[index][1] == route[1]) {
            System.out.println("number: "+number);
            System.out.println(index+", "+wires[index][0]+", "+wires[index][1]);
            return;
        }
        else {
            System.out.println("index, wire[0], wire[1], number: "+index+", "+wires[index][0]+", "+wires[index][1]+", "+number);
            visited[wires[index][0]-1] = true;
            visited[wires[index][1]-1] = true;
            number++;
            for (int i = index + 1; i < wires.length; i++){
                if ((!visited[wires[i][1]-1] && wires[index][1] == wires[i][0]) || (!visited[wires[i][0]-1] && wires[index][1] == wires[i][1]) || (!visited[wires[i][1]-1] && wires[index][0] == wires[i][0]) || (!visited[wires[i][0]-1] && wires[index][0] == wires[i][1]))
                    dfs(wires, route, visited, i);
            }
        }
        return;
    }
}

//Second Try
import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        int answer = n;
        for (int i = 0; i < wires.length; i++){
            List<List<Integer>> arr = new ArrayList<>();
            boolean[] visited = new boolean[n];
            for (int j=0; j<n; j++) arr.add(new ArrayList<>());
            for (int j=0; j<wires.length; j++){
                if (i==j) continue;
                arr.get(wires[j][0]-1).add(wires[j][1]-1);
                arr.get(wires[j][1]-1).add(wires[j][0]-1);
            }
            answer = Math.min(answer, Math.abs(n - 2*dfs(arr, visited, 0)));
        }
        return answer;
    }
    public int dfs(List<List<Integer>> arr, boolean[] visited, int index){
        int count = 0;
        if (!visited[index]) {
            count++;
            visited[index] = true;
            for (int num : arr.get(index)){
                count+=dfs(arr, visited, num);
            }
        }
        return count;
    }
}

//배열 기반 시도 예정