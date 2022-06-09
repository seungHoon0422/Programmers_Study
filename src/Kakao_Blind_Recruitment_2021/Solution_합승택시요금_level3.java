package Kakao_Blind_Recruitment_2021;

import java.util.Arrays;

/**
 * [플로이드 와샬]
문제요약 :
 둘이 택시를 타고가는데 특정 지점까지 택시를 같이타고가 택시요금을 줄이기 위한 경로

 <solution>
 공통으로 요금을 낼 노드를 정하고, 그지점에서 각 도착점까지의 최단요금을 계산해야 하기 때문에
 다익스트라로 처리하기보다는 플로이드와샬 알고리즘 사용

 모든 지점을 INF로 마킹해두고, 양방향 그래프이기 때문에 양쪽 모두 초기화시켜준다. => 의미없나..?
 a,b로 갈때 공통 경유지가 자기자신이 될 수도 있기 때문에 자기자신으로 가는 비용을 0으로 처리한다.
 그후 모든 노드를 경우할때 비교해서 가장 비용이 최소인 경우인 비용을 리턴

 </solution>


 */
class Solution_합승택시요금_level3 {
    private static final int INF = 987654321;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] weight = new int[n+1][n+1];
        // 처음에 INF로 초기화
        for(int i=0; i<n+1; i++) Arrays.fill(weight[i], INF);

        // 주어진 간선 비용 저장
        for(int[] edge : fares) {
            weight[edge[0]][edge[1]] = edge[2];
            weight[edge[1]][edge[0]] = edge[2];
        }
        // 플로이드 와샬 알고리즘 시작
        for(int k=1; k<=n; k++)
            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    weight[i][j] = Math.min(weight[i][j], weight[i][k] + weight[k][j]);
        // 자기 자신에 대한 비용은 0으로 초기화
        for(int i=1; i<=n; i++) weight[i][i] = 0;
        // 최소비용은 경유하지 않고 바로가는 경우로 초기화
        int sum = weight[s][a] + weight[s][b];
        for(int i=1; i<=n; i++){
            // 간선이 있는 경우에만 실행
            if(weight[s][i] == INF || weight[i][a] == INF || weight[i][b] == INF) continue;
            sum = Math.min(sum, weight[s][i] + weight[i][a] + weight[i][b]);
        }



        return sum;
    }
}