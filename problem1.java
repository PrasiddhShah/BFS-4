// Time Complexity : O(mn)
// Space Complexity :O(8)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no

/*
Approach 
we will use BFS to solve the question
in the queue we add the clicked area
then we start the loop on queue till it's empty
on every itteration we take out one element, check how many mines are near that block using a custom function "countMine"
if no mine is around we loop to all the 8 dirs from the postion and add it to queue if its value is 'E'
if mines are there we just put the number of mines there


the countmine function uses the same 8 dirs array to check if there are mine or not and returns the count
*/
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, -1 },
                { -1, 1 },
                { 1, -1 } };
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cnt = countMine(board, cur[0], cur[1], dirs);
            if (cnt == 0) {
                for (int[] dir : dirs) {
                    int nr = cur[0] + dir[0];
                    int nc = cur[1] + dir[1];
                    if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'E') {
                        q.add(new int[] { nr, nc });
                        board[nr][nc] = 'B';
                    }
                }
            } else {
                board[cur[0]][cur[1]] = (char) (cnt + '0');
            }
        }
        return board;
    }

    private int countMine(char[][] board, int i, int j, int[][] dirs) {
        int re = 0;
        for (int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];
            if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'M') {
                re++;
            }
        }
        return re;
    }
}