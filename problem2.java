// Time Complexity :O(mn)
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no

/*
Approach
we will first convert the 2d matrix to 1d, in the process we use a flag to help with the conversion, if we encounter a number not equal to
-1 that means its a ladder or snake so account for that as well

then we start BFS using a queue and use a moves variable, we add the first element in queue and start a loop on it until
we start a for loop till size of current queue we take out the element and check if it's equal to n*n-1 if yes we return moves
else
we start another loop from 1 to 6 (die numbers), in loop we check all the possible steps 1,2,3.. after that we do a boundary check
and if that numer is not already visited we add it to queue and mark it visited
when both for loop finishes we increase move count
in the end return moves
*/

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        boolean flag = true;
        int r = n - 1;
        int c = 0;
        int idx = 0;
        while (idx < arr.length) {
            if (board[r][c] == -1) {
                arr[idx] = -1;
            } else {
                arr[idx] = board[r][c] - 1;
            }
            idx++;
            if (flag) {
                c++;
                if (c == n) {
                    c--;
                    r--;
                    flag = false;
                }
            } else {
                c--;
                if (c < 0) {
                    c++;
                    r--;
                    flag = true;
                }
            }
        }
        int moves = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == n * n - 1)
                    return moves;
                for (int j = 1; j <= 6; j++) {
                    int newidx = cur + j;
                    if (newidx >= n * n) {
                        break;
                    }
                    if (arr[newidx] != -2) {
                        if (arr[newidx] == -1) {
                            q.add(newidx);
                        } else {
                            q.add(arr[newidx]);
                        }
                        arr[newidx] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}