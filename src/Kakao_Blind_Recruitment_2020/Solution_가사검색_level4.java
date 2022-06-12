package Kakao_Blind_Recruitment_2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution_가사검색_level4 {
    private HashMap<Integer, ArrayList<String>> map;

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        map = new HashMap<Integer, ArrayList<String>>();
        for(String word : words) {
            if(!map.containsKey(word.length())) map.put(word.length(), new ArrayList<>());
            map.get(word.length()).add(word);
        }
        int index = 0;
        for(String query : queries) {
            query = query.replaceAll("[?]", ".");
            Pattern pattern = Pattern.compile(query);
            Matcher matcher;
            if(!map.containsKey(query.length())) {
                index++;
                continue;
            }
            ArrayList<String>  wordList = map.get(query.length());
            for(String word : wordList){
                if(pattern.matcher(word).find()) answer[index]++;

            }
            index++;
        }
        return answer;
    }
}