package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: nathanchen Date: 6/02/2014 Time: 12:05 PM Description:
 */
@Entity
@Table(name="entry_status_change_log")
public class EntryStatusChangeLogModel extends Model
{
    @Id
    public Long id;

    public Long account_id;

    public Long entry_id;

    public int status_prev;

    public int status_after;

    public Date date;



}
