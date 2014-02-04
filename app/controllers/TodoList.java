package controllers;

import models.EntryModel;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.GlobalConfiguration;
import utils.Guava;
import views.html.todolist.showalllists;

import java.util.List;

/**
 * User: nathanchen Date: 1/02/2014 Time: 11:17 AM Description:
 */
public class TodoList extends Controller
{
    public static Result showAList()
    {
        return null;
    }

    public static Result showAllLists()
    {
        String account_id_in_session = Http.Context.current().session().get(GlobalConfiguration.USER_ID_IN_SESSION);
        Long account_id = Guava.tryParse(account_id_in_session);
        if (account_id == null)
        {
            return redirect(routes.Login.login());
        }
        List<String> categories_for_an_account = EntryModel.findDistinctCategoryByAccountId(account_id);
        return ok(showalllists.render(categories_for_an_account, account_id_in_session));
    }
}
