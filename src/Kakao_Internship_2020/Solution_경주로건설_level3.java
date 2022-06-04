package Kakao_Internship_2020;

/**
 * [DFS, 백트래킹]
 * 벽이있는 곳은 경주로를 설치할 수 없다.
 * depth를 통해서 직선도로의 개수를 파악하고,
 * corner는 dfs진행할 때 변수로 이전에 방향을 입력받아
 * 반대방향의 경우 건설하지 못하게 하고, 코너체크 진행
 *
 * 백트래킹을 하기위해서
 * sum값을 들고다니면서 백트래킹 진행
 */
class Solution_경주로건설_level3 {
    int N;
    private boolean[][] visited;

    int answer;

    // 시계방향, 남 서 북 동 순서
    int[] dr = {1,0,-1,0};
    int[] dc = {0,-1,0,1};
    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        N = board.length;
        visited = new boolean[N][N];
        visited[0][0] = true;
        // r,c 시작좌표, 직선주로 개수, 곡선주로 개수
        DFS(0,0,0,0, 0, 0, board);

        DFS(0,0,0,0, 3, 0, board);

        return answer;
    }

    private void DFS(int r, int c, int depth, int corner, int dir, int sum, int[][] board) {

        if(sum > answer) return;
        // 목표지점 도착
        if(r==N-1 && c==N-1){
            answer = Math.min(answer, depth*100 + corner*500);
            return;
        }

        for(int d=0; d<4; d++){
            // 반대방향 백트래킹
            int i = (dir+d)%4;
            if(i == (dir+2)%4) continue;
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr<0 || nr>=N || nc<0 || nc>=N) continue;    // 범위체크
            if(visited[nr][nc]) continue; // 방문한 지점인지 확인
            if(board[nr][nc] == 1) continue; // 벽이면 건설 불가
            visited[nr][nc] = true;
            if(dir == i) DFS(nr,nc,depth+1, corner, i, sum+100, board);
            else DFS(nr,nc,depth+1, corner+1, i, sum+600, board);
            visited[nr][nc] = false;
        }
    }
}