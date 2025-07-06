//https://school.programmers.co.kr/learn/courses/30/lessons/43162

//First try
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] computer = new boolean[computers.length];
        boolean changes = false;
        for (int i = 0; i < computers.length; i++){
            if (visited(computers, computer, i)){
                answer++;
            }
        }
        return answer;
    }
    private boolean visited(int[][] computers, boolean[] computer, int j){
        boolean changes = false;
        for (int i=0; i<computers[0].length; i++){
            if (computers[j][i] == 1 && !computer[j]){
                computer[j] = true;
                changes = true;
                visited(computers, computer, i);
            }
        }
        return changes;
    }
}

//Second try
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] computer = new boolean[computers.length];
        for (int i = 0; i < computers.length; i++){
            if (!computer[i]){
                dfs(computers, computer, i);
                answer++;
            }
        }
        return answer;
    }
    private void dfs(int[][] computers, boolean[] computer, int j){
        computer[j] = true;
        for (int i=0; i<computers[0].length; i++){
            if (computers[j][i] == 1 && !computer[i]){
                dfs(computers, computer, i);
            }
        }
    }
}