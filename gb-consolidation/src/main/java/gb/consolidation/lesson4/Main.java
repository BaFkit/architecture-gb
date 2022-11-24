package gb.consolidation.lesson4;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.add();
        dbHandler.showTables();
        dbHandler.findTimeTableError();
        dbHandler.printFilmBreaks();
        dbHandler.closeConnection();
    }
}
