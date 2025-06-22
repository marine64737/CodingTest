public static String solution(int arg1,   String arg2) {
    // your logic here
    String[] tokens = arg2.split(" ");
    List<Integer> array = new ArrayList<>();
    for (String token : tokens){
        array.add(Integer.parseInt(token));
    }
    String answer = String.valueOf(Collections.min(array)) + " " + String.valueOf(Collections.max(array));
    return answer;
}