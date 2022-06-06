package Kakao_Blind_Recruitment_2018;

import java.util.ArrayList;
    import java.util.PriorityQueue;
    import java.util.StringTokenizer;

    /**

    문제요약 :
     음악의 재생시간, 끝난시간, 악보가 제공
     악보 길이를 보고 반복된 횟수를 확인할 수 있다.
     멜로디가 주어졌을 때 해당 멜로디가 포함된 음악을 찾아야 한다.
     결과가 여러음악이 나온 경우, 재생된 시간이 제일 긴 음악을 선택하고,
     길이도 같으면 시간이 빠른 음악을 고른다.

     <solution>
      음악에 대한 정보를 저장하기 위해 class를 활용
     멜로디 확인은 contains를 통해 확인하고, 재생 시간만큼 악보를 늘려놓는다.

     멜로디를 확인할 때 #붙은 놈들 처리가 너무문제...
     카카오는 문제풀다보면 replaceAll을 자주 사용해야 쉽게 풀린다.
     #붙은 음은 아예 다른음으로 치환시켜버린다!!
     결국 길이만큼 자를때 원래 멜로디 크기를 사용할 수 있게

     입력되는 멜로디에도 처리
     </solution>
     */

    class Solution_방금그곡_level2 {

        ArrayList<Song> songs;
        public String solution(String m, String[] musicinfos) {
            String answer = "";

            m = m.replaceAll("A#", "1");
            m = m.replaceAll("C#", "2");
            m = m.replaceAll("D#", "3");
            m = m.replaceAll("F#", "4");
            m = m.replaceAll("G#", "5");



            songs = new ArrayList<>();
            initSongs(m, musicinfos);
            ArrayList<Song> possible = new ArrayList<>();

            for(Song song : songs) {
                if(song.melody.contains(m)) {
                    possible.add(song);
                }
            }
            if(possible.size() == 0) return "(None)";
            Song result = possible.get(0);
            for(int i=1; i<possible.size(); i++) {
                Song o = possible.get(i);
                if(result.runningTime < o.runningTime) result = o;
                else if(result.runningTime == o.runningTime){
                    if(result.sh > o.sh) result = o;
                    else if(result.sh == o.sh){
                        if ( result.sm > o.sm ) result = o;
                    }
                }
            }


            return result.title;
        }

        private void initSongs(String m, String[] musicinfos) {
            for(String data : musicinfos) {
                StringTokenizer st = new StringTokenizer(data, ",");
                String startTime = st.nextToken();
                String endTime = st.nextToken();
                String title = st.nextToken();
                String originalMelody = st.nextToken();

                String simple = originalMelody.replaceAll("A#", "1");
                simple = simple.replaceAll("C#", "2");
                simple = simple.replaceAll("D#", "3");
                simple = simple.replaceAll("F#", "4");
                simple = simple.replaceAll("G#", "5");

                st = new StringTokenizer(startTime, ":");
                int sh = Integer.parseInt(st.nextToken());
                int sm = Integer.parseInt(st.nextToken());
                st = new StringTokenizer(endTime, ":");
                int eh = Integer.parseInt(st.nextToken());
                int em = Integer.parseInt(st.nextToken());
                int runningTime = (eh - sh) * 60 + (em - sm);
                StringBuilder melody = new StringBuilder();
                int i=0;
                int rest = runningTime;
                for(i=0; i<runningTime/simple.length(); i++){
                    melody.append(simple);
                    rest -= simple.length();
                }
                int index = 0;
                while(rest > 0) {
                        melody.append(simple.charAt(index++));
                        rest--;
                }

                Song song = new Song(sh,sm,eh,em,runningTime, title, melody.toString());
                songs.add(song);
            }

        }

        static class Song {
            int sh, sm, eh, em;
            int runningTime;
            String title, melody;

            public Song(int sh, int sm, int eh, int em, int runningTime, String title, String melody) {
                this.sh = sh;
                this.sm = sm;
                this.eh = eh;
                this.em = em;
                this.runningTime = runningTime;
                this.title = title;
                this.melody = melody;
            }



        }
    }