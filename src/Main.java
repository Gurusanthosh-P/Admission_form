import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbFunction db = new DbFunction();
        Connection conn= db.connect_to_db("admission","postgres","S@03brimma#");
//        db.TableCreated(conn,"Student_info");
        db.row_insert(conn,"student_info","shruthika","19");
//        db.Update_row(conn,"student_info","aswin","Aswin_kumar");
//        db.search_by_id(conn,"student_info",1);
//          db.delete_by_id(conn,"student_info",5);
          db.view_table(conn,"student_info");




    }
}