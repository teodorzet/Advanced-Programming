package Compulsory;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String args[]){
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i) )
                .toArray(Student[]::new);

        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i) )
                .toArray(School[]::new);

        List<Student> listOfStudents = new LinkedList<>();
        listOfStudents.addAll(Arrays.asList(students));
        Collections.sort(listOfStudents, Comparator.comparing(Student::getName));

        Set<School> setOfSchools = new TreeSet<>(Arrays.asList(schools));

        Map<Student, List<School>> studentPreferenceMap = new HashMap<>();
        studentPreferenceMap.put(listOfStudents.get(0),Arrays.asList(schools[0],schools[1],schools[2]));
        studentPreferenceMap.put(listOfStudents.get(1),Arrays.asList(schools[0],schools[1],schools[2]));
        studentPreferenceMap.put(listOfStudents.get(2),Arrays.asList(schools[0],schools[1]));
        studentPreferenceMap.put(listOfStudents.get(3),Arrays.asList(schools[0],schools[2]));

        List<Student> prefList0 = new ArrayList<>();
        prefList0.add(students[3]);
        prefList0.add(students[0]);
        prefList0.add(students[1]);
        prefList0.add(students[2]);

        List<Student> prefList1 = new ArrayList<>();
        prefList1.add(students[0]);
        prefList1.add(students[2]);
        prefList1.add(students[1]);

        List<Student> prefList2 = new ArrayList<>();
        prefList2.add(students[0]);
        prefList2.add(students[1]);
        prefList2.add(students[3]);

        Map<School, List<Student>> schoolPreferenceMap = new HashMap<>(){{
            put(schools[0],prefList0);
            put(schools[1],prefList1);
            put(schools[2],prefList2);
        }};

        System.out.println(studentPreferenceMap.toString());
        System.out.println(schoolPreferenceMap.toString());

        List<School> listOfShools = Arrays.asList(schools[0],schools[1]);
    }
}
