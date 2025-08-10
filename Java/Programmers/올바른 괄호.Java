class Solution {
    public static void main(String[] args) {
        System.out.println(solution("(((("));
    }
    public boolean solution(String s) {
        int bal = 0;
        for (int i=0; i<s.size(); i++){
            if (s.chatAt(i) == '(') bal++;
            else bal--;
            if (bal < 0) return false;
        }
        return bal == 0;
    }
}