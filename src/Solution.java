import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
[문자열, 그리디?]

문제요약 :
 광고를 재생시킬 최적 시각을 찾는 문제
 사용자들의 로그를 보고 광고를 최대한 많은 사용자가 볼 시각을 찾아라!!

 <solution>
 시청자들의 로그를 일단 분석해서 자료 저장
 로그는 class를 만들어서 관리
 시간차이를 구할 때 시,분,초 데이터를 갖고 비교하는 것 보다는
 모두 초로 변환한 값으로 비교하는 것이 편리

 </solution>
 */

class Solution {
    private String pt;
    private String at;
    private ArrayList<Log> logs;

    public String solution(String play_time, String adv_time, String[] l) {
        String answer = "";
        pt = play_time;
        at = adv_time;
        logs = new ArrayList<Log>();

        Log startPoint = new Log();
        startPoint.st = "00:00:00";
        startPoint.ssec = 0;
        logs.add(startPoint);

        int index = 0;
        for(String log : l) {
            StringTokenizer st = new StringTokenizer(log, "-");
            Log lg = new Log();
            lg.st = st.nextToken();
            lg.et = st.nextToken();
            lg.ssec = converToSecond(lg.st);
            lg.esec = converToSecond(lg.et);
            lg.runningTime = lg.esec - lg.ssec;
            lg.index = index++;
            logs.add(lg);
        }
        // 시작시간 기준으로 정렬
        Collections.sort(logs);

        int pts = converToSecond(play_time);
        int ets = pts + converToSecond(adv_time);

        long maxTotal = 0;
        String startTime = "";
        for(Log cmp : logs) {
            int sad = cmp.ssec;
            int ead = sad + converToSecond(adv_time);
            long total = 0;
            for(Log log : logs) {
                if(log.esec < sad || log.ssec > ead) continue;
                int startadv = Math.max(log.ssec, sad);
                int endadv = Math.min(log.esec, ead);
                total += endadv-startadv;
            }
            if(maxTotal < total) {
                startTime = cmp.st;
                maxTotal = total;
            }
        }

        
        
        return startTime;
    }

    private int converToSecond(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        return h * 3600 + m * 60 + s;
    }

    static class Log implements Comparable<Log> {
        String st, et;
        int index;
        int ssec, esec, runningTime;

        @Override
        public int compareTo(Log o) {
            return this.ssec - o.ssec;
        }
    }
}