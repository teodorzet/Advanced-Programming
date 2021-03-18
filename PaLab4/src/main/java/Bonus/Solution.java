package Bonus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    Problem problem;
    Map<Student,Boolean> mapOfStudentsMatched;
    Map<School,List<Student>> mapOfSchoolsMatched;

    public Solution(Problem problem) {
        this.problem = problem;
        mapOfStudentsMatched = new HashMap<>();
        for(int i=0;i<problem.getListOfStudents().size();i++){
            mapOfStudentsMatched.put(problem.getListOfStudents().get(i),false);
        }
        mapOfSchoolsMatched = new HashMap<>();
        for(int i=0;i<problem.getListOfSchools().size();i++){
            List<Student> listOfStudents = new ArrayList<>();
            mapOfSchoolsMatched.put(problem.getListOfSchools().get(i),listOfStudents);
        }
    }
    public boolean existsStudentWithoutSchool(){
        for(int i=0;i<problem.getListOfStudents().size();i++){
            if (mapOfStudentsMatched.get(problem.getListOfStudents().get(i)) == false) {
                if (problem.existSchoolInStudentPreference(problem.getListOfStudents().get(i)))
                return true;
            }
        }
        return false;
    }
    public Student firstStudentWithoutSchool(){
        for(int i=0;i<problem.getListOfStudents().size();i++){
            if (mapOfStudentsMatched.get(problem.getListOfStudents().get(i)) == false)
                return problem.getListOfStudents().get(i);
        }
        return null;
    }
    public School firstSchoolOnStudentList(Student student){
        if (problem.getStudentPreferenceMap().get(student).get(0) != null)
            return problem.getStudentPreferenceMap().get(student).get(0);

        if (problem.existSchoolInStudentPreference(student)) {
            int randomNumber = randomNumberBetween(1,problem.getStudentPreferenceMap().get(student).size());
            while (problem.getStudentPreferenceMap().get(student).get(randomNumber) == null){
                randomNumber = randomNumberBetween(1,problem.getStudentPreferenceMap().get(student).size());
            }
            return problem.getStudentPreferenceMap().get(student).get(randomNumber);
        }
        else return null;
    }
    public int randomNumberBetween(int min, int max){
        return (int) (Math.random() * (max - min) + min);
    }
    public int positionInPreferenceOfSchool(School school, Student student){
        for(int i=0;i<problem.getSchoolPreferenceMap().get(school).size();i++){
            if (problem.getSchoolPreferenceMap().get(school).get(i) == student)
                return i;
        }
        return -1;
    }
    public void printSolution(){
        /*
        * Algoritmul este Gale-Shapley, cu modificarea faptului ca fiecare scoala poate avea mai mult decat un match cu
        * studentii (adica capacitatea poate fi mai mare de 1). Fiecare student prefera prima scoala fata de restul din
        * lista acestuia. Astfel, in cazul in care studentul a fost ales pentru scoala de pe primul loc din preferinte
        * dar a fost inlocuit cu altul care are in lista scolii o preferinta mai mare (adica este mai preferat),
        * deoarece restul preferintelor nu au precedenta una fata de alta, se va alege o scoala random din lista ramasa
        * pentru a incerca un match. Astfel, pentru acelasi caz, vor exista mai multe cazuri si rezultate finale, dato-
        * rita acelor preferinte "nestricte".
         */
        while (existsStudentWithoutSchool()){
            /*
            * Algoritmul ruleaza pana cand nu mai exista un student fara scoala, sau care nu a incercat o pereche cu
            * toate scolile din lista de preferinte a acestuia.
             */
            Student studentWithoutSchool = firstStudentWithoutSchool();
            School firstSchool = firstSchoolOnStudentList(studentWithoutSchool);
            /*
            * Gasim o scoala care nu a fost incercata din lista preferintelor primului student care nu are pereche, in
            * functie de prioritate, scoate scoala din lista acestuia pentru a nu o incerca din nou, si verificam daca
            * este loc. Daca nu este loc, verificam daca in lista curenta a perechilor scolii este un student cu o pre
            * ferinta mai mica decat celui fara pereche. Daca este, se face schimbul intre ei, si se modifica starea
            * acestora (daca au pereche sau nu).
             */
            problem.removeSchool(studentWithoutSchool,firstSchool);
            if (firstSchool != null && firstSchool.getCapacity() > 0){
                mapOfSchoolsMatched.get(firstSchool).add(studentWithoutSchool);
                mapOfStudentsMatched.put(studentWithoutSchool,true);
                firstSchool.setCapacity(firstSchool.getCapacity()-1);
            } else {
                List<Student> listOfStudentsFromSchool = mapOfSchoolsMatched.get(firstSchool);
                if (listOfStudentsFromSchool != null)
                for(int i=0;i<listOfStudentsFromSchool.size();i++){
                    if (positionInPreferenceOfSchool(firstSchool,listOfStudentsFromSchool.get(i)) > positionInPreferenceOfSchool(firstSchool,studentWithoutSchool)){
                        mapOfStudentsMatched.put(studentWithoutSchool,true);
                        mapOfStudentsMatched.put(listOfStudentsFromSchool.get(i),false);
                        mapOfSchoolsMatched.get(firstSchool).remove(listOfStudentsFromSchool.get(i));
                        mapOfSchoolsMatched.get(firstSchool).add(studentWithoutSchool);
                        break;
                    }
                }
            }
        }
        for(int i=0;i<mapOfSchoolsMatched.size();i++){
            System.out.println(problem.getListOfSchools().get(i) + " are studentii " + mapOfSchoolsMatched.get(problem.getListOfSchools().get(i)));
        }
        System.out.println();

    }
}
