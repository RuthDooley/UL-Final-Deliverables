import java.util.ArrayList;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(numCourses);

        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < prerequisites.length; i++){
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            if(visited[i] == 0){
                if(isCycle(i, adj, visited)){
                    return false;
                }
            }

        }
        return true;
    }

    private boolean isCycle(int currNode, ArrayList<ArrayList<Integer>> adj, int[] visited){
        if(visited[currNode] == 2){
            return true;
        }

        visited[currNode] = 2;
        for(int neighbour: adj.get(currNode)){
            if(visited[neighbour]!= 1){
                if(isCycle(neighbour, adj, visited)){
                    return true;
                }
            }
        }
        visited[currNode] = 1;
        return false;

    }
}

//    public boolean canFinish(int numCourses, int[][] prerequisites){
//        ArrayList<Integer>[] adj = new ArrayList[numCourses];
//        for (int i = 0; i < numCourses; ++i){
//            adj[i] = new ArrayList<>();
//        }
//
//        for (int[] pre: prerequisites){
//            adj[pre[0]].add(pre[1]);
//        }
//
//        int visited[] = new int[numCourses];
//        for (int i = 0; i < numCourses; ++i){
//            if (visited[i] == 0 && !dfs(adj, visited, i)) return false;
//        }
//        return true;
//    }
//
//    private boolean dfs (ArrayList<Integer>[] adj, int[] visited, int v){
//        if (visited[v] == 1) return false;
//        visited[v] = 1;
//        for (int ad : adj[v]){
//            if (!dfs(adj, visited, ad)) return false;
//        }
//
//        visited[v] = 2;
//        return true;
//    }
