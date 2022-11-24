package gb.consolidation.lesson4;

import java.sql.*;

public class DbHandler {

    private static final String CON_STR = "jdbc:sqlite:products.s3db";
    private static Connection connection;
    private static Statement statement;
    private static DbHandler instance = null;
    public static ResultSet resultSet;

    public static synchronized DbHandler getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) instance = new DbHandler();
        return instance;
    }

    private DbHandler() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.connection = DriverManager.getConnection(CON_STR);
        createDB();
    }

    public void createDB() throws ClassNotFoundException, SQLException {

        statement = connection.createStatement();

        statement.execute(Queries.DROP_FILMS_TABLE);
        statement.execute(Queries.DROP_DURATION_TABLE);
        statement.execute(Queries.DROP_TICKETS_TABLE);
        statement.execute(Queries.DROP_TIMETABLE_TABLE);
        statement.execute(Queries.DROP_PRICE_TABLE);

        statement.execute(Queries.CREATE_FILM_TABLE);
        statement.execute(Queries.CREATE_DURATION_TABLE);
        statement.execute(Queries.CREATE_TICKETS_TABLE);
        statement.execute(Queries.CREATE_TIMETABLE_TABLE);
        statement.execute(Queries.CREATE_PRICE_TABLE);

    }

    public void add() throws SQLException {
        statement = connection.createStatement();
        statement.execute("INSERT INTO films(name, duration_id) VALUES" +
                "('ActionFilm', 2)," +
                "('ComedyFilm', 1)," +
                "('DramaFilm', 2)," +
                "('DocumentaryFilm', 3)," +
                "('FantasyFilm', 1)");

        statement.execute("INSERT INTO durations(duration) VALUES (60), (90), (120);");
        statement.execute("INSERT INTO price(cost) VALUES (50),(70),(30),(80),(100);");
        statement.execute("INSERT INTO timetable (film_id, start_time_h, start_time_m, cost_id) VALUES " +
                "(1, '12', '00', 4)," +
                "(2, '14', '20', 1)," +
                "(3, '13', '30', 2)," +
                "(4, '15', '00', 1)," +
                "(1, '17', '45', 4)," +
                "(2, '19', '00', 2)," +
                "(5, '21', '00', 5)");

        statement.execute("INSERT INTO tickets(session_id) VALUES (2),(2),(1),(3),(4),(5),(2),(3),(3),(2),(1),(1),(2),(3),(4)");
        statement.close();
    }

    public void showTables() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM films");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int durationId = resultSet.getInt("duration_id");
            System.out.println("ID = " + id);
            System.out.println("name = " + name);
            System.out.println("duration_id = " + durationId);
        }
    }

    public void findTimeTableError() throws SQLException {
            int endTime = 0;
            int count = 0;
        try {
            resultSet = statement.executeQuery("SELECT name, duration, start_time_h, start_time_m FROM " +
                    "films LEFT JOIN duration ON films.durations.id " +
                    "LEFT JOIN timetable ON films.id = timetable.film_id ORDER BY start_time_h, start_time_m");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Film:        " + "Duration:           " + "Start time");
            while (resultSet.next()) {
                count++;
                String name =resultSet.getString("name");
                int duration = resultSet.getInt("duration");
                String startTimeH = resultSet.getString("start_time_h");
                String startTimeM = resultSet.getString("start_time_m");
                if (count > 1) {
                    if ((Integer.parseInt(startTimeH)*60 + Integer.parseInt(startTimeM)) <= endTime) {
                        System.out.println("Schedule error");
                    }
                }
                endTime = Integer.parseInt(startTimeH)*60 + Integer.parseInt(startTimeM) + duration;

                System.out.println(name + "              " + duration + "               " + startTimeH + ":" + startTimeM);
            }
        }



        public void printFilmBreaks() throws SQLException {
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS breaks");
            statement.execute("CREATE TABLE IF NOT EXISTS breaks (id INTEGER PRIMARY KEY, film_name TEXT, " +
                    "start_time_h TEXT, start_time_m TEXT, duration INTEGER, " +
                    "next_film_start_h TEXT, next_film_start_m TEXT, break INTEGER);");

            int endTime = 0;
            int count = 0;
            String filmName = null;
            String startTimeH = null;
            String startTimeM= null;
            String nextFilmStartH;
            String nextFilmStartM;
            int breakFilm;
            int duration = 0;
            resultSet = statement.executeQuery("SELECT name, duration, start_time_h, start_time_m FROM " +
                    "films LEFT JOIN durations ON film.duration_id = durations.id " +
                    "LEFT JOIN timetable ON films.id = timetable.film_id ORDER BY next_film_start_h, next_film_start_m");

            while (resultSet.next()) {
                count++;
                if (count == 1) {
                    filmName = resultSet.getString("name");
                    duration = resultSet.getInt("duration");
                    startTimeH = resultSet.getString("start_time_h");
                    startTimeM = resultSet.getString("start_time_m");
                    endTime = Integer.parseInt(startTimeH)*60 + Integer.parseInt(startTimeM) + duration;
                } else {
                    nextFilmStartH = resultSet.getString("start_time_h");
                    nextFilmStartM = resultSet.getString("start_time_m");
                    breakFilm = Integer.parseInt(startTimeH)*60 + Integer.parseInt(startTimeM) - endTime;

                    String prepIns = "INSERT INTO breaks(film_name, start_time_h, start_time_m, duration, next_film_start_h, next_film_start_m, break) VALUES (?, ?, ?, ?, ?, ?, ?);";
                    PreparedStatement ps = connection.prepareStatement(prepIns);
                    ps.setString(1, filmName);
                    ps.setString(2, startTimeH);
                    ps.setString(3, startTimeM);
                    ps.setInt(4, duration);
                    ps.setString(5, nextFilmStartH);
                    ps.setString(6, nextFilmStartM);
                    ps.setInt(7, breakFilm);
                    ps.execute();

                    filmName = resultSet.getString("name");
                    duration = resultSet.getInt("duration");
                    startTimeH = resultSet.getString("start_time_h");
                    startTimeM = resultSet.getString("start_time_m");
                    endTime = Integer.parseInt(startTimeH)*60 + Integer.parseInt(startTimeM) + duration;
                }
        }

            resultSet = statement.executeQuery("SELECT * FROM breaks ORDER BY break");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3) + "  " + resultSet.getString(4) +
                        "      " + resultSet.getInt(5) + "       " + resultSet.getString(6) + ":" + resultSet.getString(7) +
                        resultSet.getInt(8));
            }
    }

    public void closeConnection() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Connection is closed");
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection is closed");
                e.printStackTrace();
            }
        }
    }

}
