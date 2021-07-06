package datastructure.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int v; //顶点个数

    private LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    // 广度优先搜索
    public void bfs(int s, int t) {
        if (s == t) return;

        boolean visited[] = new boolean[v];
        int prev[] = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int n = queue.poll();

            for (Integer i : adj[n]) {
                if (!visited[i]) {
                    prev[i] = n;

                    if (i == t) {
                        print(prev, s, t);
                        return;
                    }

                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }


    boolean found = false;

    // 深度优先搜索
    public void dfs(int s, int t) {
        if (s == t) return;

        boolean visited[] = new boolean[v];
        int prev[] = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }

        recurDfs(visited, prev, s, t);
        print(prev, s, t);
    }

    private void recurDfs(boolean[] visited, int[] prev, int w, int t) {
        if (found) return;

        for (Integer n : adj[w]) {
            if (found) return;
            if (!visited[n]) {
                prev[n] = w;
                visited[n] = true;

                if (n == t) {
                    found = true;
                }
                recurDfs(visited, prev, n, t);
            }
        }
    }

    public void print(int[] prev, int s, int t) {
        if (prev[s] != -1 && prev[s] != t) {
            print(prev, s, prev[t]);
        }
        System.out.println(t + " ");
    }
}
