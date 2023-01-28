package proj.CSVtoMySQL;

import java.sql.*;

public abstract class HSfile {
    public String sql;
    public String fileLink;
    public abstract PreparedStatement InsertValues(String[] data, PreparedStatement pst);
}
