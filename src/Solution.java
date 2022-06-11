/**
[배열 회전]
문제요약 :
 key와 board가 주어졌을 때 주어진 키를 회전, 이동하여 board의 잠금을 풀 수 있는지 판단
 key는 꼭 board안에서만 움직여야 하는게 아니라 board만 완전히 벗어나지 않으면 된다.
 0은 홈, 1은 돌기를 의미

<solution>
 일단 key를 회전하기 위해서는, 배열 회전 알고리즘 필요
 근데 이문제에서는 key에 해당하는 배열을 모두 회전하기 보다
 왼쪽 위의 좌표를 base좌표로 잡고, 돌기의 상대적이 위치를 저장시키는 방식으로 적용

 키의 돌기가 모두 홈에 들어가거나, board의 밖에 위치하는지 확인하고
 board의 모든 홈이 키의 돌기와 만나는지 체크하는 방식으로 true를 확인한다.

 </solution>

 */
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        return answer;
    }
}