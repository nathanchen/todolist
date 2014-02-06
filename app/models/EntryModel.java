package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: nathanchen Date: 31/01/2014 Time: 9:25 PM Description:
 */
@Entity
@Table(name="todolist")
public class EntryModel extends Model
{
    @Id
    public Long id;

    public String content;

    public int status;

    public String note;

    public Long account_id;

    public Long category_id;

    public Date create_date;

    public EntryModel () {}

    public static Model.Finder<Long, EntryModel> find = new Model.Finder<Long, EntryModel>(
            Long.class, EntryModel.class
    );

    public static EntryModel findTodoListModelById(Long id)
    {
        return find.byId(id);
    }

    public static List<EntryModel> findTodoListModelListByAccountId(Long account_id)
    {
        return find.where().eq("account_id", account_id).findList();
    }

    public static List<Long> findDistinctCategoryByAccountId(Long account_id)
    {
        List<SqlRow> sqlRowList = Ebean.createSqlQuery("select distinct category_id from todolist where account_id=:account_id")
                .setParameter("account_id", account_id)
                .findList();
        List<Long> categoryIdList = new ArrayList<Long>(sqlRowList.size());
        for (SqlRow sqlRow : sqlRowList)
        {
            categoryIdList.add(sqlRow.getLong("category_id"));
        }
        return categoryIdList;
    }
}
