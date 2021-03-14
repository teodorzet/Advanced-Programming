package Optional;

public class Solution {
    Problem problem;

    public Solution(Problem problem) {
        this.problem = problem;
    }

    public void printSolution(){
        for(int i=0;i<problem.getStudentPreferenceMap().size();i++){
            int positionOfStudent=0;
            for(int j=0;j<problem.getSchoolPreferenceMap().size();j++){
                positionOfStudent = problem.getSchoolPreferenceMap().get(j).indexOf(problem.getSchoolPreferenceMap().get(i));
                //System.out.println("Studentul " + problem.getSchoolPreferenceMap().get(i) );
            }
        }
    }
}
