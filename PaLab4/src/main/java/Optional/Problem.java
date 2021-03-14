package Optional;

import java.util.List;
import java.util.Map;

public class Problem {
    private Map<School, List<Student>> schoolPreferenceMap;
    private Map<Student, List<School>> studentPreferenceMap;

    public Problem(Map<School, List<Student>> schoolPreferenceMap,Map<Student, List<School>> studentPreferenceMap) {
        this.schoolPreferenceMap = schoolPreferenceMap;
        this.studentPreferenceMap = studentPreferenceMap;
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