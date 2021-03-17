package Optional;

import java.util.List;
import java.util.Map;

public class Problem {
    private Map<School, List<Student>> schoolPreferenceMap;
    private Map<Student, List<School>> studentPreferenceMap;
    private List<Student> listOfStudents;
    private List<School> listOfSchools;

    public Problem(Map<School, List<Student>> schoolPreferenceMap,Map<Student, List<School>> studentPreferenceMap,List<Student> listOfStudents,List<School> listOfSchools) {
        this.schoolPreferenceMap = schoolPreferenceMap;
        this.studentPreferenceMap = studentPreferenceMap;
        this.listOfStudents = listOfStudents;
        this.listOfSchools = listOfSchools;
    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }
    public List<School> getListOfSchools() {
        return listOfSchools;
    }
    public Map<School, List<Student>> getSchoolPreferenceMap() {
        return schoolPreferenceMap;
    }
    public void setSchoolPreferenceMap(Map<School, List<Student>> schoolPreferenceMap) {
        this.schoolPreferenceMap = schoolPreferenceMap;
    }
    public Map<Student, List<School>> getStudentPreferenceMap() {
        return studentPreferenceMap;
    }
    public void setStudentPreferenceMap(Map<Student, List<School>> studentPreferenceMap) {
        this.studentPreferenceMap = studentPreferenceMap;
    }
}