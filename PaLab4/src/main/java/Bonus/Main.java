package Bonus;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String args[]){
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i) )
                .toArray(Student[]::new);

        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i,(int) (Math.random() * 2) + 1))
                .toArray(School[]::new);

        Map<School, List<Student>> schoolPreferenceMap = new HashMap<>();
        Map<Student, List<School>> studentPreferenceMap = new HashMap<>();

        studentPreferenceMap.put(students[0],Arrays.asList(schools[0],schools[1],schools[2]));
        studentPreferenceMap.put(students[1],Arrays.asList(schools[0],schools[1],schools[2]));
        studentPreferenceMap.put(students[2],Arrays.asList(schools[0],schools[1]));
        studentPreferenceMap.put(students[3],Arrays.asList(schools[0],schools[2]));

        schoolPreferenceMap.put(schools[0],Arrays.asList(students[3],students[0],students[1],students[2]));
        schoolPreferenceMap.put(schools[1],Arrays.asList(students[0],students[2],students[1]));
        schoolPreferenceMap.put(schools[2],Arrays.asList(students[0],students[1],students[3]));

        schools[0].setCapacity(1);
        schools[1].setCapacity(2);
        schools[2].setCapacity(2);

        List<Student> listOfStudents = new ArrayList<>(Arrays.asList(students));
        List<School> listOfSchools = new ArrayList<>(Arrays.asList(schools));

        for(int i=0;i<3;i++)
        System.out.println("H" + i + " are capacitatea " + schools[i].getCapacity());
        System.out.println();

        /*
        * problem1 si problem2 au aceleasi date de intrare, dar pot avea rezultate diferite, in functie de cum sunt
        * alese preferintele "nestricte"
         */
        Problem problem1 = new Problem(schoolPreferenceMap,studentPreferenceMap,listOfStudents,listOfSchools);
        Solution solution1 = new Solution(problem1);
        solution1.printSolution();

        schools[0].setCapacity(1);
        schools[1].setCapacity(2);
        schools[2].setCapacity(2);
        studentPreferenceMap.put(students[0],Arrays.asList(schools[0],schools[1],schools[2]));
        studentPreferenceMap.put(students[1],Arrays.asList(schools[0],schools[1],schools[2]));
        studentPreferenceMap.put(students[2],Arrays.asList(schools[0],schools[1]));
        studentPreferenceMap.put(students[3],Arrays.asList(schools[0],schools[2]));

        Problem problem2 = new Problem(schoolPreferenceMap,studentPreferenceMap,listOfStudents,listOfSchools);
        Solution solution2 = new Solution(problem2);
        solution2.printSolution();


    }
}