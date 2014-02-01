package models;

import com.avaje.ebean.Ebean;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import utils.Encryption;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.NoSuchAlgorithmException;

/**
 * User: nathanchen
 * Date: 29/01/2014
 * Time: 9:51 PM
 * Description:
 */
@Entity
@Table(name="account")
public class AccountModel
{
    @Id
    public Long id;

    @Constraints.Required
    @Constraints.Email
    public String email;

    @Constraints.Required
    @Constraints.MinLength(6)
    public String password;

    public String user_name;

    public AccountModel (){}

    public AccountModel (Long id, String email, String password)
    {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public AccountModel (Long id, String email, String password, String user_name)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.user_name = user_name;
    }

    public static Model.Finder<Long, AccountModel> find = new Model.Finder<Long, AccountModel>(
            Long.class, AccountModel.class
    );

    /**
     *
     * @param email
     * @param password
     * @return null or accountModel object
     * @throws NoSuchAlgorithmException
     */
    public static AccountModel validate(String email, String password) throws NoSuchAlgorithmException
    {
        String passwordEncrypted = Encryption.md5Encryption(password);
        return  find.where().eq("email", email).eq("password", passwordEncrypted).findUnique();
    }

    public static boolean hasDuplicateUserByEmail(String email)
    {
        return find.where().eq("email", email).findRowCount() > 0;
    }

    public static void updatePasswordById(String password, Long id) throws NoSuchAlgorithmException
    {
        String passwordEncrypted = Encryption.md5Encryption(password);
        Ebean.createSqlUpdate("UPDATE account set password =:password where id=:id")
                .setParameter("password", passwordEncrypted)
                .setParameter("id", id).execute();
    }
}
