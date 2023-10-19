import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunction {
    public Connection connect_to_db(String dbname,String user,String pass){
       Connection conn = null;
       try{
           Class.forName("org.postgresql.Driver");
           conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
           if(conn!=null)
           {
               System.out.println("Connection Established");
           }
           else {
               System.out.println("Connection Failed");
           }

       }catch (Exception e)
       {
           System.out.println(e);
       }
       return conn;
    }
    public void TableCreated(Connection conn,String table_name){
        Statement statement;
        try{
            String query = "create table "+table_name+"(student_id SERIAL,name varchar(20),Age varchar(3),primary key(student_id));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void row_insert(Connection conn, String table_name,String name,String age){
        Statement statement;
        try {
            String query = String.format("insert into %s(name,age) values('%s','%s');",table_name,name,age);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void view_table(Connection conn, String table_name){
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("Select * from %s",table_name);
            statement=conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next())
            {
                System.out.print(rs.getString("student_id")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("age")+" ");
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void Update_row(Connection conn,String table_name,String Old_name,String new_name){
        Statement statement;
        try {
            String query = String.format("Update %s set name='%s' where name='%s'",table_name,new_name,Old_name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Updated");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void search_by_id(Connection conn,String table_name,int student_id){
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s where student_id=%s",table_name,student_id);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next())
            {
                System.out.print(rs.getString("student_id")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("age"));

            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete_by_id(Connection conn,String table_name,int student_id)
    {
        Statement statement;
        try {
            String query = String.format("delete from %s where student_id=%s",table_name,student_id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row deleted");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
