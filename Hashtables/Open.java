import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Open {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("open.dat")));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String line;
        while ((line = br.readLine()) != null) {

        }
    }

    class Project implements Comparable<Project> {

        Set<String> userids;
        String projectName;

        public Project(String name) {
            userids = new HashSet<>();
            projectName = name;
        }

        public void addSignUp(String userid) {
            userids.add(userid);
        }

        public int getSignUps() {
            return userids.size();
        }

        public String getProjectName() {
            return projectName;
        }

        @Override
        public int compareTo(Project project) {
            return 0;
        }

    }

}
