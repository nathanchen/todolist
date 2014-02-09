package models;

import play.db.ebean.Model;
import utils.GlobalConfiguration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * User: nathanchen Date: 4/02/2014 Time: 12:26 PM Description:
 */
@Entity
@Table(name="category")
public class CategoryModel extends Model
{
    @Id
    public Long id;

    public Long account_id;

    public String category_name;

    public Long list_format_id;

    public int status;

    public Date create_date;

    public CategoryModel() {}

    public static Finder<Long, CategoryModel> find = new Finder<Long, CategoryModel>(
            Long.class, CategoryModel.class
    );

    public static List<CategoryModel> findCategoryNameListByIdList(List<Long> idList)
    {
        return find.where().in("id", idList).findList();
    }

    public static CategoryModel findCategoryModelById(Long id)
    {
        return find.byId(id);
    }

    public static CategoryModel saveANewCategoryModel(CategoryModel categoryModel, Long account_id)
    {
        categoryModel.account_id = account_id;
        categoryModel.status = GlobalConfiguration.INITIAL_CATEGORY_STATUS_OK;
        categoryModel.create_date = new Date();
        categoryModel.save();
        return categoryModel;
    }
}
