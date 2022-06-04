package Kakao_Internship_2020;

import java.util.Arrays;

/**
 * [DFS, 백트래킹]
 * 벽이있는 곳은 경주로를 설치할 수 없다.
 * depth를 통해서 직선도로의 개수를 파악하고,
 * corner는 dfs진행할 때 변수로 이전에 방향을 입력받아
 * 반대방향의 경우 건설하지 못하게 하고, 코너체크 진행
 *
 * <solution>
 * 백트래킹을 하기위해서
 * sum값을 들고다니면서 백트래킹 진행
 * => 위 방법도 테스트 10,11,15,19 시간초과
 *
 * 메모이제이션 방법으로 변경
 * </solution>
 */
class Solution_경주로건설_level3_2 {
    int N;
    private boolean[][] visited;
    private int[][] cost;
    int answer;

    // 시계방향, 남 서 북 동 순서
    int[] dr = {1,0,-1,0};
    int[] dc = {0,-1,0,1};
    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        N = board.length;
        cost = new int[N][N];


        // r,c 시작좌표, 직선주로 개수, 곡선주로 개수
        for(int i=0; i<N; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);
        cost[0][0] = 0;
        DFS(0,0,0,0, 0, 0, board);


        return cost[N-1][N-1];
    }

    private void DFS(int r, int c, int depth, int corner, int dir, int sum, int[][] board) {

        // 목표지점 도착
        if(r==N-1 && c==N-1){
            return;
        }

        if(sum > answer) return;

        for(int d=0; d<4; d++){
            int i = (dir+d)%4;
            // 반대방향 백트래킹
            if(i == (dir+2)%4) continue;
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr<0 || nr>=N || nc<0 || nc>=N) continue;    // 범위체크
            if(board[nr][nc] == 1) continue; // 벽이면 건설 불가

            if(cost[nr][nc] == Integer.MAX_VALUE){
                if(r==0 && c==0){
                    cost[nr][nc] = cost[r][c] + 100;
                    DFS(nr,nc,depth+1, corner, i, sum+100, board);
                } else if(dir == i) {
                    cost[nr][nc] = cost[r][c] + 100;
                    DFS(nr,nc,depth+1, corner, i, sum+100, board);
                }
                else {
                    cost[nr][nc] = cost[r][c] + 600;
                    DFS(nr,nc,depth+1, corner+1, i, sum+600, board);
                }
            } else{
                if(dir == i && cost[r][c] + 100 < cost[nr][nc]){
                    cost[nr][nc] = cost[r][c]+100;
                    DFS(nr,nc,depth+1, corner, i, sum+100, board);

                }
                else if(dir != i && cost[r][c]+600 < cost[nr][nc]){
                    cost[nr][nc] = cost[r][c] + 600;
                    DFS(nr,nc,depth+1, corner+1, i, sum+600, board);
                }

            }
        }
    }
}