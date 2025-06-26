public int solution(String[][] clothes) {
    int answer = 1;
    HashMap<String, Integer> clothHash = new HashMap<String, Integer>();
    for (int i=0; i<clothes.length; i++){
        if (clothHash.containsKey(clothes[i][1])){
            clothHash.put(clothes[i][1], clothHash.get(clothes[i][1])+1);
        }
        else {
            clothHash.put(clothes[i][1], 1);
        }
    }
    for (String cloth : clothHash.keySet()){
        answer *= clothHash.get(cloth)+1;
    }
    return answer-1;
}

public int solution(String[][] clothes) {
    HashMap<String, Integer> clothHash = new HashMap<>();

    for (String[] cloth : clothes) {
        clothHash.put(cloth[1], clothHash.getOrDefault(cloth[1], 0) + 1);
    }

    int answer = 1;
    for (int count : clothHash.values()) {
        answer *= (count + 1);
    }

    return answer - 1;
}
