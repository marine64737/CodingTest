public int[] solution(int brown, int yellow) {
    int[] answer = {};
    for (int i=1; i<=yellow; i++){
        if (yellow % i == 0 && 2*(i+yellow/i)+4== brown){
            return new int[]{yellow/i+2, i+2};
        }
    }
    return answer;
}