package np.edu.bvs.bvshigh.general;

public class Constants {

    private static final String ROOT_URL = "http://mitochanet.000webhostapp.com/android/v1/";

    //private static final String ROOT_URL = "http://172.16.11.247/bvs_high/login/";

    public static final String URL_REGISTER = ROOT_URL+"registerUser.php";
    public static final String URL_LOGIN = ROOT_URL+"userLogin.php";
    public static final String URL_STUDENT_NAME = "http://mitochanet.000webhostapp.com/android/pull_detail/student_names.php";

    public static final String URL_TEACHER_NAME = "http://mitochanet.000webhostapp.com/android/pull_detail/teachers_names.php";

    //Token Registering
    public static final String URL_TOKEN_REGISTRATION = "http://mitocha.com/bvs_high/fcm_bvs/register.php";

    // URL for all SCIENCE BIO STUDENTS
    private static final String ROUTINE_URL = "http://mitocha.com/bvs_high/";

    public static final String URL_Routine_Sci_Bio_11_SUN = ROUTINE_URL+"routine_sci_bio_11/sun.php";
    public static final String URL_Routine_Sci_Bio_11_MON = ROUTINE_URL+"routine_sci_bio_11/mon.php";
    public static final String URL_Routine_Sci_Bio_11_TUE = ROUTINE_URL+"routine_sci_bio_11/tue.php";
    public static final String URL_Routine_Sci_Bio_11_WED = ROUTINE_URL+"routine_sci_bio_11/wed.php";
    public static final String URL_Routine_Sci_Bio_11_THUR = ROUTINE_URL+"routine_sci_bio_11/thur.php";
    public static final String URL_Routine_Sci_Bio_11_FRI = ROUTINE_URL+"routine_sci_bio_11/fri.php";

    //URL for pulling Notifications from Server
    public static final String URL_NOTIFICATIONS = "http://mitocha.com/bvs_high/notification_details/pullNotification.php";
    public static final String URL_SAVE_NOTIFICATIONS = "http://mitocha.com/bvs_high/notification_details/saveNotification.php";

    //Teacher Logins
    public static final String URL_LOGIN_TEACHERS = ROOT_URL + "userLogin_teachers.php";

    //Assignment Save and Pull
    public static final String URL_ASSIGNMENT_SAVE = "http://mitocha.com/bvs_high/assignment/save_assignment.php";
    public static final String URL_ASSIGNMENT_PULL = "http://mitocha.com/bvs_high/assignment/pull_assignment.php";

}
