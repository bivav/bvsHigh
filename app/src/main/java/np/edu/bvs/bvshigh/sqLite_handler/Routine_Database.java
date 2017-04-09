package np.edu.bvs.bvshigh.sqLite_handler;


public class Routine_Database {

    private String _id;
    private String _start_time;
    private String _end_time;
    private String _subject;
    private String _teacher;

    public Routine_Database(){

    }

    public Routine_Database(String start_time, String end_time, String subject, String teacher) {
        this._start_time = start_time;
        this._end_time = end_time;
        this._subject = subject;
        this._teacher = teacher;
    }


    // SETTER
    public void set_start_time(String _start_time) {
        this._start_time = _start_time;
    }

    public void set_end_time(String _end_time) {
        this._end_time = _end_time;
    }

    public void set_subject(String _subject) {
        this._subject = _subject;
    }

    public void set_teacher(String _teacher) {
        this._teacher = _teacher;
    }


    // GETTER


    public String get_start_time() {
        return _start_time;
    }

    public String get_end_time() {
        return _end_time;
    }

    public String get_subject() {
        return _subject;
    }

    public String get_teacher() {
        return _teacher;
    }
}
