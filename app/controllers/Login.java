package controllers;

import models.AccountDetailModel;
import models.AccountModel;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.GlobalConfiguration;
import utils.Guava;
import views.html.login.login;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * User: nathanchen Date: 30/01/2014 Time: 9:02 PM Description:
 */
public class Login extends Controller
{
    final static Form<AccountModel> loginForm = form(AccountModel.class);

    /**
     * Display a blank form.
     */
    public static Result login ()
    {
        Http.Context.current().response().setHeader("REQUIRES_AUTH", "1");
        String user_id_string = Http.Context.current().session().get(GlobalConfiguration.USER_ID_IN_SESSION);
        Long user_id = Guava.tryParse(user_id_string);
        return ok(login.render(loginForm, null));
    }

    public static Result authenticate()
    {
        Form<AccountModel> filledForm = loginForm.bindFromRequest();
        String email_entered = filledForm.field("email").valueOr("");
        String password_entered = filledForm.field("password").valueOr("");

        if (email_entered.isEmpty() || password_entered.isEmpty())
        {
            filledForm.reject("email or password is incorrect");
        }
        try
        {
            AccountModel accountModel = AccountModel.validate(email_entered, password_entered);

            if (accountModel == null || accountModel.id == null || accountModel.user_name == null)
            {
                return badRequestBackToLoginPage(filledForm);
            }

            AccountDetailModel accountDetailModel = AccountDetailModel.getAccountDetailModelById(accountModel.id);

            if (accountDetailModel.status != 10)
            {
                return badRequestBackToLoginPage(filledForm);
            }

            AccountDetailModel.updateAccountDetailLastLoginDate(accountDetailModel, new Date());

            session().clear();
            session(GlobalConfiguration.USER_NAME_IN_SESSION, accountModel.user_name);
            session(GlobalConfiguration.USER_ID_IN_SESSION, String.valueOf(accountModel.id));
            return redirect(routes.TodoList.showAllLists());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return badRequestBackToLoginPage(filledForm);
        }
    }

    private static Result badRequestBackToLoginPage (Form<AccountModel> filledForm)
    {
        filledForm.reject("email or password is incorrect");
        return badRequest(login.render(filledForm, null));
    }

    public static Result logout()
    {
        session().clear();
        return redirect(routes.Login.login());
    }
}
