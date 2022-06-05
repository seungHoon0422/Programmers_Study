package Kakao_Internship_2019;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * isPossible : 회원의 이름이 불량 사용자에 해당하는지 검사하는 함수
 * search : 불량사용자 리스트 만큼 반복돌면서 뽑아냄
 *
 * 불량 사용자의 순서는 상관없으므로 list보단 set을 사용해서 중복만 확인
 * set의 중복을 처리하기 위해 map에 저장하여 key비교를 통해 중복 제거
 *
 * 3테스트케이스 실패
 * 29번째줄이 안되는 이유는 뭘까...
 * solution => map.put(set, true)에서 map.put(new HashSet<>(set), true)로 수정하니 해결
 *
 * [[ map보다 중복을 확인할 때에는 set을 사용하자!! ]]
 *
 */
class Solution_불량사용자_level3 {
    int answer;
    Map<Set<String>, Boolean> banMap;
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        banMap = new HashMap<>();
        search(0, new HashSet<>(), new String[banned_id.length],user_id, banned_id);

        return answer;
    }

    private void search(int cnt, HashSet<String> set, String[] banList, String[] user_id, String[] banned_id) {
        // 기저조건
        if(cnt == banned_id.length){
            if(banMap.containsKey(set)) return;
//            banMap.put(set, true);
            banMap.put(new HashSet<>(set), true);
            answer++;
            return;
        }


       for (String s : user_id) {
            if (isPossible(s, banned_id[cnt])) {
                if(set.contains(s)) continue;

                set.add(s);

                banList[cnt] = s;
                search(cnt + 1, set, banList, user_id, banned_id);
                set.remove(s);


            }
        }
        // 벤가능한 아이디 확인

        // 해당 아이디가 banlist에 이미 들어있는지 확인
        // 이미 들어있으면 return
        // 안들어있으면 cnt 추가하고, 다음 작업
    }

    boolean isPossible(String user, String ban) {

        if(user.length() != ban.length()) return false;
        for(int index = 0; index < ban.length(); index++){
            if(ban.charAt(index) == '*') continue;
            else if(user.charAt(index) != ban.charAt(index)) return false;
        }

        return true;
    }
}