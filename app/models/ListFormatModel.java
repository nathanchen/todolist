package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: nathanchen Date: 7/02/2014 Time: 2:40 PM Description:
 */
@Entity
@Table(name="list_format")
public class ListFormatModel extends Model
{
    @Id
    public Long id;

    // list format name
    public String list_name;

    // attributes split by ","
    public String csv_attributes;

    public ListFormatModel() {}

    public static Finder<Long, ListFormatModel> find = new Finder<Long, ListFormatModel>(
            Long.class, ListFormatModel.class
    );

    public static ListFormatModel findListFormatModelById(Long id)
    {
        return find.byId(id);
    }

    public String[] parseCsvAttributes(String csv_attributes)
    {
        return csv_attributes.split(",");
    }
}
