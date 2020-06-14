package com.src.ds.graph.shortestpath.dijkstra.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Aman
 */
public class BatmanAndTheCrime {

    public static void main(String[] args) {

        int[][] graph = new int[][]{
            {0, 1, 3},
            {0, 3, 4},
            {0, 2, 5},
            {3, 2, 3},
            {1, 2, 1}
        };
        int[] C = new int[]{1, 3, 2};

        int nodes = 4;

        int distance[] = getShortestDistanceFromSource(nodes, graph, 0);

        int[] shortestDistance = new int[C.length];

        for (int i = 0; i < C.length; i++) {
            shortestDistance[i] = distance[C[i]];
        }

        for (int i : shortestDistance) {
            System.out.println(i + " ");
        }

    }

    private static int[] getShortestDistanceFromSource(int nodes, int[][] graph, int sourceNode) {

        Map<Integer, ArrayList<Node>> distMap = new HashMap<>();
        for (int[] row : graph) {
            ArrayList<Node> neighbours = distMap.getOrDefault(row[0], new ArrayList<>());
            neighbours.add(new Node(row[1], row[2]));
            distMap.put(row[0], neighbours);

            neighbours = distMap.getOrDefault(row[1], new ArrayList<>());
            neighbours.add(new Node(row[0], row[2]));
            distMap.put(row[1], neighbours);
        }

        PriorityQueue<Node> PQ = new PriorityQueue<>((node1, node2) -> node1.distance - node2.distance);
        PQ.add(new Node(sourceNode, 0));

        boolean[] visited = new boolean[nodes];
        int[] distance = new int[nodes];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[sourceNode] = 0;
        visited[sourceNode] = true;

        while (!PQ.isEmpty()) {
            Node node = PQ.poll();
            visited[node.value] = true;
            if (distMap.containsKey(node.value)) {
                List<Node> neighbours = distMap.get(node.value);
                for (Node neighbour : neighbours) {
                    if (!visited[neighbour.value]) {
                        if (distance[node.value] + neighbour.distance < distance[neighbour.value]) {
                            distance[neighbour.value] = distance[node.value] + neighbour.distance;
                        }
                        PQ.add(new Node(neighbour.value, distance[neighbour.value]));
                    }
                }
            }

        }

        for (int i = 0; i < distance.length; i++) {
            distance[i] = distance[i] == Integer.MAX_VALUE ? -1 : distance[i];
        }

        return distance;
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

}
