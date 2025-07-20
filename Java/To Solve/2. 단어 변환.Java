//2세트 (백트래킹 / 경우의 수)
//https://school.programmers.co.kr/learn/courses/30/lessons/43163

class Solution {
    int answer = 0;
    public int solution(String begin, String target, String[] words) {
        if (!isContained(target, words)) return 0;
        change(begin,target,words,0);
        return answer;
    }
    public void change(String begin, String target, String[] words, int index) {
        boolean diff = false;
        for(int i = 0; i<begin.length(); i++){
            if (begin.charAt(i) != target.charAt(i)) {
                if (diff == true){
                    diff = false;
                    break;
                }
                else diff = true;
            }
        }
        if (diff == true) {
            answer++;
            return;
        }
        for(int i = 0; i<begin.length(); i++){
            if (begin.charAt(i) != words[index].charAt(i)) {
                if (diff == true){
                    change(begin,target,words,index+1);
                    return;
                }
                else diff = true;
            }
        }
        answer++;
        if (index < words.length - 1)
            change(words[index],target,words,index+1);
        return;
    }
    public boolean isContained(String target, String[] words){
        boolean contained = false;
        for(String word : words){
            if(target.equals(word)) return true;
        }
        return contained;
    }
}

//AI Recommend
class Solution {
    int answer = Integer.MAX_VALUE;

    public int solution(String begin, String target, String[] words) {
        boolean hasTarget = false;
        for (String word : words)
            if (word.equals(target)) hasTarget = true;
        if (!hasTarget) return 0;

        boolean[] visited = new boolean[words.length];
        dfs(begin, target, words, visited, 0);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    void dfs(String cur, String target, String[] words, boolean[] visited, int depth) {
        if (cur.equals(target)) {
            answer = Math.min(answer, depth);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && isOneLetterDiff(cur, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, visited, depth + 1);
                visited[i] = false;
            }
        }
    }

    boolean isOneLetterDiff(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}
