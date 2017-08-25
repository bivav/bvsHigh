package np.edu.bvs.bvshigh.students;

/**
 * Created by Bivav on 8/24/2017.
 */

public class Students_Attendance_List {

    private String name;
    private boolean isSelected;

    public Students_Attendance_List(String name) {
        this.name = name;
    }

    public Students_Attendance_List(String name, Boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
