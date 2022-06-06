package Kakao_Blind_Recruitment_2018;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**

 문제 요약 :
 한번의 턴에 같은모양의 인형을 터트린다.
 인접해있는 모든 인형을 터트린 후 빈공간이 없게 아래로 땡긴다.
 더이상 터트릴 인형이 없을때까지 진행
 지워진 블록의 수를 리턴

 <solution>

 한번의 턴에 모든 인형을 탐색하고, 인형을 떨구는 작업 실행

 </solution>

 */
class Solution_프렌즈4블록_level2 {
    private int answer;
    private char[][] map;
    private int[] dr = {0,0,1,1};
    private int[] dc = {0,1,0,1};
    private boolean[][] removable;
    Map<Integer, int[]> poss;
    public int solution(int n, int m, String[] b) {
        answer = 0;
        map = new char[n][m];

        for(int i=0; i<n; i++) map[i] = b[i].toCharArray();

        int removed = 0;
        while(true){


            removable = new boolean[n][m];

            for(int i=0; i<n-1; i++){
                for(int j=0; j<m-1; j++){
                    if(map[i][j] != ' ') {
                        bfs(i,j,map[i][j],n,m);
                    }
                }
            }
            int count = 0;
            for(int i=0; i<n; i++)
                for(int j=0; j<m; j++){
                    if(removable[i][j]) {
                        count++;
                        map[i][j] = ' ';
                    }
                }
            if(count == 0 ) break;
            else answer += count;
            shiftToys(n,m);
        }

        return answer;
    }

    private void shiftToys(int r, int c) {

        for(int i=0; i<c; i++){
            Queue<Character> q = new LinkedList<>();
            for(int j=r-1; j>=0; j--){
                if(map[j][i] == ' ') {
                    for(int k=j-1; k>=0; k--){
                        if(map[k][i] != ' ') {
                            map[j][i] = map[k][i];
                            map[k][i] = ' ';
                            break;
                        }
                    }
                }
            }
        }
    }

    private void bfs(int r, int c, char type, int n, int m) {

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) return;
            if (map[nr][nc] != type) return;
        }


        // if it's possible
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            removable[nr][nc] = true;
        }

    }
}