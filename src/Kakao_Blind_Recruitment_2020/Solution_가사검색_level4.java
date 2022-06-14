package Kakao_Blind_Recruitment_2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
2020 kakao blind recruit 가사검색 level4

 문제요약 :
 와일드카드가 섞인 단어가 주어졌을 때 목록에 있는 단어중에 해당되는 단어의 개수를 리턴하는 문제
 와일드카드 문자는 말머리, 말꼬리에만 연속으로 존재

 <solution>
 solution 1 : 효율성 1,2,3 시간초과
 쿼리에 해당하는 단어를 찾기위해서 먼저 와일드카드를 신경쓰지 않고
 단어의 길이를 먼저 체크해서 거르는 작업 -> 주어진 단어들을 단어의 길이에 따라 map에 저장시켜 탐색시키는 시간을 단축
 그후에 정규표현식 라이브러리에 있는 pattern을 사용해서 와일드카드 문자가 ?로 주어진 것을
 *로 변경 후 매칭되는 단어를 찾아준다!!
 => 처음에 생각했을때에는 문자 하나하나를 비교해서 시간초과가 발생할 것 같아
 정규표현식 라이브러리를 사용했지만 그래도 시간초과 발생

 solution 2 : 문자열 정렬
 와일드카드가 뒤에있는 경우에는 와일드카드가 존재하는 길이까지만 이분탐색을 통해서 찾는다.
 문자열의 순서를 이용 => 검색의 쿼리의 개수가 10만, 주어지는 단어의 개수 100만이므로
 최악의 경우 모두 같은길이로 주어지고, 쿼리에 해당하는 단어의 길이도 모두 같은 경우에 시간초과 발생할 수 있으므로
 이분탐색을 활용해섯 시간을 줄여야한다!!

 이분탐색을 편하게 진행하기 위해서 와일드카드가 존재하는 부분을 모두 알파벳 소문자로 만들어야 하는데
 최소를 a 최대를 z로 정해서 검색


 </solution>


 */
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