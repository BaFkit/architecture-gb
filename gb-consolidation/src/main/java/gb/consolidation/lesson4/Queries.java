package gb.consolidation.lesson4;

public class Queries {

    public static final String CREATE_FILM_TABLE =
            "CREATE TABLE if not exists films " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, duration_id INTEGER, " +
                    "FOREIGN KEY (duration_id) REFERENCE duration (id));";

    public static final String CREATE_DURATION_TABLE =
            "CREATE TABLE if not exists duration " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, duration INTEGER);";

    public static final String CREATE_TICKETS_TABLE =
            "CREATE TABLE if not exists tickets" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "session_id INTEGER FOREIGN KEY (session_id) REFERENCE timetable (id));";

    public static final String CREATE_TIMETABLE_TABLE =
            "CREATE TABLE if not exists timetable " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "film_id INTEGER, start_time_h TEXT, start_time_m TEXT, cost_id INTEGER," +
                    "FOREIGN KEY (film_id) REFERENCE film (id))," +
                    "FOREIGN KEY (cost_id) REFERENCE price (id));";

    public static final String CREATE_PRICE_TABLE =
            "CREATE TABLE if not exists price " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, cost INTEGER);";

    public static final String DROP_FILMS_TABLE = "DROP TABLE if exists films;";
    public static final String DROP_DURATION_TABLE = "DROP TABLE if exists duration;";
    public static final String DROP_TICKETS_TABLE = "DROP TABLE if exists tickets;";
    public static final String DROP_TIMETABLE_TABLE = "DROP TABLE if exists timetable;";
    public static final String DROP_PRICE_TABLE = "DROP TABLE if exists price;";
}
