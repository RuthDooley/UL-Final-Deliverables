public class main {
    public static void main(String[] args) {
        int numCourses1 = 2;
        int [][] prerequisites1 = {{1,0},{0,1}}; //Impossible

        int numCourses2 = 2;
        int [][] prerequisites2 = {{1,0}}; //Possible

        CourseSchedule newCourses  = new CourseSchedule();
        if (newCourses.canFinish(numCourses1, prerequisites1)) System.out.println("Can finish");
        else System.out.println("Can't finish");

        if (newCourses.canFinish(numCourses2, prerequisites2)) System.out.println("Can finish");
        else System.out.println("Can't finish");
    }
}
