package com.src.ds.graph.shortestpath.dijkstra.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aman
 */
public class BatmanAndTheCrimeBruteForce {

    public static void main(String[] args) {

        int[][] graph = new int[][]{
            {0, 1, 5},
            {0, 3, 1},
            {1, 2, 1},
            {1, 5, 3},
            {2, 5, 1},
            {2, 6, 6},
            {3, 5, 2},
            {3, 4, 2},
            {5, 4, 13},
            {5, 6, 10},
            {4, 6, 5},};
        int[] C = new int[]{0, 1, 2, 3, 4, 5, 6};

        int nodes = 7;

        int distance[] = getShortestDistanceFromSourceBruteForce(graph, nodes, 0);

        int[] shortestDistance = new int[C.length];

        for (int i = 0; i < C.length; i++) {
            shortestDistance[i] = distance[C[i]];
        }
        System.out.println("Ans");
        for (int i : shortestDistance) {
            System.out.print(i + " ");
        }

    }

    static class Node {

        int value;
        int distance;

        public Node(int value, int distance) {
            this.value = value;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Node{" + "value=" + value + ", distance=" + distance + '}';
        }

    }

    private static int[] getShortestDistanceFromSourceBruteForce(int[][] graph, int nodes, int sourceNode) {

        Map<Integer, ArrayList<Node>> distMap = new HashMap<>();
        for (int[] row : graph) {
            ArrayList<Node> neighbours = distMap.getOrDefault(row[0], new ArrayList<>());
            neighbours.add(new Node(row[1], row[2]));
            distMap.put(row[0], neighbours);
        }

        int[] distance = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            if (i == sourceNode) {
                distance[i] = 0;
            } else {
                dfs(distMap, sourceNode, i);
                distance[i] = minDistance;
                resetVariables(nodes);
            }

        }

        for (int i = 0; i < distance.length; i++) {
            distance[i] = distance[i] == Integer.MAX_VALUE ? -1 : distance[i];
        }

        return distance;
    }

    private static void resetVariables(int nodes) {
        path = new ArrayList<>();
        paths = new ArrayList<>();
        visited = new boolean[nodes];
        dist = 0;
        minDistance = Integer.MAX_VALUE;
    }

    static List<List<Integer>> paths = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    static boolean visited[] = new boolean[7];
    static int dist = 0;
    static int minDistance = Integer.MAX_VALUE;

    static void dfs(Map<Integer, ArrayList<Node>> graph, int source, int destination) {
        if (source == destination) {
            path.add(destination);
            minDistance = Math.min(dist, minDistance);
            return;
        }

        path.add(source);
        visited[source] = true;

        ArrayList<Node> neighbours = graph.get(source);
        if (neighbours != null && !neighbours.isEmpty()) {
            for (Node neighbour : neighbours) {
                if (visited[neighbour.value]) {
                    continue;
                }
                dist += neighbour.distance;
                dfs(graph, neighbour.value, destination);
                int n = path.remove(path.size() - 1);
                visited[n] = false;
                dist -= neighbour.distance;
            }
        }

    }

}
