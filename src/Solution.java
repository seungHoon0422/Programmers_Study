import java.util.ArrayList;

class Solution {
    int answer;

    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        search(0, new String[banned_id.length],user_id, banned_id);

        return answer;
    }

    private void search(int cnt, String[] banList, String[] user_id, String[] banned_id) {
        // 기저조건
        if(cnt == banned_id.length){
            answer++;
            return;
        }


loop1:  for (String s : user_id) {
            if (isPossible(s, banned_id[cnt])) {
                for (String name : banList) {
                    if (name.equals(s))
                        continue loop1;
                }
                banList[cnt] = s;
                search(cnt + 1, banList, user_id, banned_id);


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