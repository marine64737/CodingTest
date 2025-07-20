//1세트 (재귀 입문 + DFS 기초)
//https://school.programmers.co.kr/learn/courses/30/lessons/12946

class Solution {
    int index = 0;
    public int[][] solution(int n) {
        int[][] answer = {};
        int[][] arr = new int[pow(n)-1][2];
        hanoi(arr, n, 1, 2, 3);
        return arr;
    }
    public int pow(int n){
        int tot = 1;
        for(int i = 0; i < n; i++){
            tot*=2;
        }
        return tot;
    }
    public void hanoi(int[][] arr, int n, int from, int via, int to){
        if (n==1) {
            arr[index][0]=from;
            arr[index][1]=to;
            index++;
            return;
        }
        hanoi(arr, n-1, from, to, via);
        arr[index][0] = from;
        arr[index][1] = to;
        index++;
        hanoi(arr, n-1, via, from, to);
        return;
    }
}