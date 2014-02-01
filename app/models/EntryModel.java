package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * User: nathanchen Date: 31/01/2014 Time: 9:25 PM Description:
 */
@Entity
@Table(name="todolist")
public class EntryModel
{
    @Id
    public Long id;

    public String content;

    public int status;

    public String note;

    public Long account_id;

    public String category;

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
}
