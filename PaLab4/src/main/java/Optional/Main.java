package Optional;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void main(String args[]){
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i, (int) (Math.random() * 100)) )
                .toArray(Student[]::new);

        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i, (int) (Math.random()*2 + 2 ) ))
                .toArray(School[]::new);

        List<Student> listOfStudents = new LinkedList<>();
        listOfStudents.addAll(Arrays.asList(students));
        Collections.sort(listOfStudents, Comparator.comparing(Student::getScore));

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
        Collections.sort(prefList0, Comparator.comparing(Student::getScore));
        Collections.sort(prefList0, Comparator.reverseOrder());

        List<Student> prefList1 = new ArrayList<>();
        prefList1.add(students[0]);
        prefList1.add(students[2]);
        prefList1.add(students[1]);
        Collections.sort(prefList1, Comparator.comparing(Student::getScore));
        Collections.sort(prefList1, Comparator.reverseOrder());

        List<Student> prefList2 = new ArrayList<>();
        prefList2.add(students[0]);
        prefList2.add(students[1]);
        prefList2.add(students[3]);
        Collections.sort(prefList2, Comparator.comparing(Student::getScore));
        Collections.sort(prefList2, Comparator.reverseOrder());

        Map<School, List<Student>> schoolPreferenceMap = new HashMap<>(){{
            put(schools[0],prefList0);
            put(schools[1],prefList1);
            put(schools[2],prefList2);
        }};

        System.out.println(studentPreferenceMap.toString());
        System.out.println(schoolPreferenceMap.toString());

        List<School> listOfShools = Arrays.asList(schools[0],schools[1]);

        listOfStudents.stream()
                .filter(std -> studentPreferenceMap.get(std).containsAll(listOfShools))
                .forEach(System.out::println);

        setOfSchools.stream()
                .filter(std -> schoolPreferenceMap.get(std).contains(students[0]) && schoolPreferenceMap.get(std).get(0) == students[0])
                .forEach(System.out::println);

        Faker faker = new Faker();

        //faker.name().name();

        listOfStudents.stream()
                .map(s -> new Student(faker.name().lastName(),s.getScore()))
                .forEach(System.out::println);

        setOfSchools.stream()
                .map(s -> new School(faker.name().name(),s.getCapacity()))
                .forEach(System.out::println);

        Problem problem = new Problem(schoolPreferenceMap,studentPreferenceMap);
        Solution solution = new Solution(problem);

    }
}