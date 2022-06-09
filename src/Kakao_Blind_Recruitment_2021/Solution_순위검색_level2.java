package Kakao_Blind_Recruitment_2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 [문자열, 자료구조, 이분탐색]
 => 이게왜... level2지...
 문제요약 :
 지원자 스펙에 따라서 쿼리에 해당하는 지원자의 수를 리턴해야한다.
 쿼리에는 - 값으로 해당 칼럼에 있는 모든 지원자를 탐색해야함

 <solution>
 쿼리를 실행할 떄마다 지원자를 탐색하기에는 시간이 부족하기 때문에
 미리 지원자의 스펙을 Hash 형식으로 저장한다.
 점수까지 저장하기에는 메모리가 부족할수 있으므로,
 그전 조건까지만 저장

 에로사항 :
 1) 처음에 info를 저장할 때 -처리를 따로 두어서 query를 실행할 때 -를 만나면 쿼리를 해당 조건 수만큼 실행
 => 시간초과 발생, 코드가 너무길어지고 더러워진다, query에 해당하는 점수를 카운트할때 순차탐색으로 한것도 문제

 1-1)info를 저장할 때 -를 아예 같이 저장시켜버리자!!
 => info의 크기가 커지지만 list의 길이가 상당히 길어질 수 있기 때문에 애초에 저장할 때 시간을 소비
 => info에 대해 '-'를 만날 수 있는 모든 경우를 넣어준다.

 1-2) query에 해당하는 점수를 만족하는 개수를 카운트하기 위해서 이분탐색 실행
 => list이분탐색을 통해서 가능한 개수만 count

 </solution>




 */
class Solution_순위검색_level2 {
    private HashMap<String, ArrayList<Integer>> info;
    private int count;

    public int[] solution(String[] infos, String[] queries) {
        int[] answer = {};
        ArrayList<Integer> result = new ArrayList<>();
        initInfo(infos);
        for(String query : queries)
            result.add(querySplit(query));
        answer = new int[queries.length];
        for(int i=0; i<queries.length; i++)
            answer[i] = result.get(i);
        return answer;
    }

    private int querySplit(String query) {

        count = 0;
        query = query.replaceAll(" and ", "");
        StringTokenizer st = new StringTokenizer(query, " ");
        String str = st.nextToken();
        queryExecute(str, Integer.parseInt(st.nextToken()));
        return count;
    }

    private void queryExecute(String key, int score) {
        if(!info.containsKey(key)) return;
        ArrayList<Integer> list = info.get(key);
        int start=0, end = list.size()-1;
        while(start<=end) {
            int mid = (start+end)>>1;
            if(list.get(mid) < score) start = mid+1;
            else end = mid-1;
        }
        count = list.size()-start;
    }

    private void initInfo(String[] infos) {
        info = new HashMap<String, ArrayList<Integer>>();
        for(String in : infos ) {
            StringTokenizer st = new StringTokenizer(in, " ");
            ArrayList<String> i = new ArrayList<>();
            while(st.hasMoreTokens())
                i.add(st.nextToken());
            addInfos(0, "", i);
        }
        for(ArrayList<Integer> value : info.values()) {
            Collections.sort(value);
        }
    }

    private void addInfos(int index, String key, ArrayList<String> q) {
        // 기저조건
        if(index == 4) {
            int score = Integer.parseInt(q.get(index));
            if(!info.containsKey(key)) info.put(key, new ArrayList<>());
            info.get(key).add(score);
            return;
        }
        addInfos(index+1, key+"-", q);
        addInfos(index+1, key+q.get(index), q);
    }
}