public static int solution(int[] people, int limit) {
    int answer = 0;

    Arrays.sort(people);
    int h = people.length;
    int j = 0;
    for (int i=0 ; i< people.length; i++) {
        if (people[h-1-i] + people[j] <= limit) {
            j++;
        }
        answer++;
        if (h-1-i <= j) break;
    }
    return answer;
}