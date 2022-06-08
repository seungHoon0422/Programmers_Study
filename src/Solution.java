import java.util.ArrayList;
import java.util.HashMap;

/**

문제요약:
 각 알파벳에 대한 색인번호가 주어지고,
 문자열이 주어졌을 때 색인번호 안에있는 가장 큰 문자열을 파악하고,
 그다음문자가 없을때에는 해당 색인번호를 출력하고,
 temp에 저장되어있는 문자열을 색인 번호에 등록



 */
class Solution {
    private HashMap<String, Integer> map;

    public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<Integer> result = new ArrayList<>();
        char[] arr = msg.toCharArray();

        map = new HashMap<String, Integer>();
        for(int i=1; i<=26; i++){
            map.put((char)('A'+i-1)+"", i);
        }
        int number = 27;
        System.out.println(map.keySet().toString());
        String tmp = "";
        for(char c : arr) {
            tmp += c;
            if(map.containsKey(tmp)) {
                result.add(map.get(tmp));
                continue;
            }
            else {
                result.add(map.get(tmp.substring(0,tmp.length()-1)));
                map.put(tmp, number++);
                tmp = "";
            }
        }
        if(tmp.length()!=0) result.add(map.get(tmp));
        answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) answer[i] = result.get(i);
        return answer;
    }
}