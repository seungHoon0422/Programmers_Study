package Kakao_Blind_Recruitment_2021;

import java.util.StringTokenizer;

/**
[누적합, 슬라이딩윈도우]

문제요약 :
 광고를 재생시킬 최적 시각을 찾는 문제
 사용자들의 로그를 보고 광고를 최대한 많은 사용자가 볼 시각을 찾아라!!

 <solution>
 시청자들의 로그를 일단 분석해서 자료 저장
 시간차이를 구할 때 시,분,초 데이터를 갖고 비교하는 것 보다는
 모두 초로 변환한 값으로 비교하는 것이 편리

 총 플레이타임을 초단위로 나누어 로그를 1초마다 실행되는 시간에 카운트한다.
 adv time 만큼 먼저 최소 값을 잡아주고,
 인덱스르 1씩증가시키면서 누적합을 계산
 가장 카운트만 많은 경우에 인덱스 체크
 </solution>
 */

class Solution_광고삽입_level3 {
    private int[] time = new int[360000];

    public String solution(String play_time, String adv_time, String[] l) {
        String answer = "";
        int pt = converToSecond(play_time);
        int at = converToSecond(adv_time);

        int index = 0;
        for(String log : l) {
            StringTokenizer st = new StringTokenizer(log, "-");
            int ssec = converToSecond(st.nextToken());
            int esec = converToSecond(st.nextToken());
            for(int i=ssec; i<esec; i++){
                time[i]++;
            }
        }

        int max_idx = 0;
        long max_sum = 0;
        long sum = 0;
        for(int i=0; i<at; i++) {
            sum += time[i];
        }
        max_sum = sum;

        for(int i = at; i<pt; i++) {
            sum += time[i] - time[i-at];
            if(sum > max_sum) {
                max_sum = sum;
                max_idx = i-at+1;
            }
        }
        int hour = max_idx / 3600;
        max_idx = max_idx % 3600;
        int min = max_idx / 60;
        int sec = max_idx % 60;
        String time = "";
        if(hour < 10) time = time + "0";
        time += hour + ":";
        if(min < 10) time = time + "0";
        time += min + ":";
        if(sec < 10) time = time + "0";
        time += sec;
        return time;
    }

    private int converToSecond(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        return h * 3600 + m * 60 + s;
    }

}