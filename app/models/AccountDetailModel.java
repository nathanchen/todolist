package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: nathanchen Date: 31/01/2014 Time: 3:45 PM Description:
 */
@Entity
@Table(name="account_detail")
public class AccountDetailModel extends Model
{
    @Id
    public Long id;

    public String full_name;

    public String ip_address;

    public Date enrolled_date;

    public Date last_login_date;

    /**
     * 10: valid
     *
     * 90: invalid
     *
     * */
    public int status;

    /**
     * record why account's status changed to numbers other than 10
     *
     * */
    public String note;

    public static Finder<Long, AccountDetailModel> find = new Finder<Long, AccountDetailModel>(
            Long.class, AccountDetailModel.class
    );

    public AccountDetailModel() {}

    public static void updateAccountDetailLastLoginDate(AccountDetailModel accountDetailModel, Date login_date)
    {
        accountDetailModel.last_login_date = login_date;
        accountDetailModel.update();
    }

    public static AccountDetailModel getAccountDetailModelById(Long id)
    {
        return find.byId(id);
    }

    public static void updateAccountDetailLastLoginDateById(Long id, Date login_date)
    {
        AccountDetailModel accountDetailModel = getAccountDetailModelById(id);
        updateAccountDetailLastLoginDate(accountDetailModel, login_date);
    }
}
