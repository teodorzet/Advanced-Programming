package Optional;

import java.util.List;

public class Solution {
    Problem problem;

    public Solution(Problem problem) {
        this.problem = problem;
    }

    /**
     * Afisarea repartizarii fiecarui student intr-o scoala
     */
    public void printSolution(){
        /*
        * Functia parcurge fiecare student din lista de studenti care este ordonata dupa scorurile acestora. Deci va
        * incepe de la studentul cu cel mai mare scor pana la cel cu cel mai mic scor. Pentru fiecare, cauta in functie
        * de preferintele acestora, in ce scoala poate fi repartizat unde au ramas locuri disponibile. Astfel, fiecare
        * student va fi repartizat in locul corespunzator, deoarece cei cu scoruri mai mari vor intra in preferintele
        * mai mari.
         */
        for(int i=0;i<problem.getStudentPreferenceMap().size();i++){
            //int positionOfStudent=0;
            List<School> listOfSchoolsPreferences = problem.getStudentPreferenceMap().get(problem.getListOfStudents().get(i));
            //System.out.println(problem.getListOfStudents().get(i) + " are preferintele " + listOfSchoolsPreferences);
            for(int j=0;j<listOfSchoolsPreferences.size();j++){
                int positionInCurrentSchool=0;
                List<Student> listOfStudents = problem.getSchoolPreferenceMap().get(listOfSchoolsPreferences.get(j));
                for(int l=0;l<listOfStudents.size();l++){
                    if (problem.getListOfStudents().get(i) == listOfStudents.get(l)){
                        positionInCurrentSchool = l+1;
                        break;
                    }
                }
                //System.out.println("Studentul S" + problem.getListOfStudents().get(i).getIndex() + " este pe pozitia " + positionInCurrentSchool + " la scoala " + listOfSchoolsPreferences.get(j));
                if (listOfSchoolsPreferences.get(j).getCapacity() > 0){
                    System.out.println("Studentul " + problem.getListOfStudents().get(i).getName() + " a fost repartizat la scoala " + listOfSchoolsPreferences.get(j).getName() + " cu punctajul " + problem.getListOfStudents().get(i).getScore());
                    listOfSchoolsPreferences.get(j).setCapacity(listOfSchoolsPreferences.get(j).getCapacity()-1);
                    break;
                }
            }
        }
    }
}
