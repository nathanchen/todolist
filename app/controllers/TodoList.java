package controllers;

import models.CategoryModel;
import models.EntryModel;
import models.ListFormatModel;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.GlobalConfiguration;
import utils.Guava;
import views.html.pageNotFound;
import views.html.todolist.showalist;
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
        List<CategoryModel> categories_names_for_an_account = findDistinctCategoryNamesByAccountId(account_id);
        return ok(showalllists.render(categories_names_for_an_account, account_id_in_session));
    }

    public static Result showAList(Long category_id)
    {
        String account_id_in_session = Http.Context.current().session().get(GlobalConfiguration.USER_ID_IN_SESSION);
        Long account_id = Guava.tryParse(account_id_in_session);
        if (account_id == null)
        {
            return redirect(routes.Login.login());
        }

        CategoryModel categoryModel = CategoryModel.findCategoryModelById(category_id);
        List<EntryModel> entryModelList = EntryModel.findEntryListByCategroyId(category_id);
        if (entryModelList == null)
        {
            // forbidden
        }

        else if (entryModelList.size() < 1)
        {
            // empty list
        }

        else if (! allowedToView(entryModelList, account_id))
        {
            return ok(pageNotFound.render());
        }

        ListFormatModel listFormatModel = ListFormatModel.findListFormatModelById(categoryModel.list_format_id);
        if (listFormatModel.list_name.equals(""))
        {
            // 可不可以用一个接口来表示todolistBean和shoppinglistBean?
            // 这样在view界面中，大的框架可以复用？
        }
        else if (listFormatModel.list_name.equals(""))
        {

        }
        else
        {
            // forbidden
        }

        return ok(showalist.render(entryModelList, account_id_in_session));
    }

    /**
     *
     * @param entryModelList is not nullable or size less than 1
     * @param account_id
     * @return
     */
    private static boolean allowedToView (List<EntryModel> entryModelList, Long account_id)
    {
        return entryModelList.get(0).account_id.equals(account_id);
    }

    private static List<CategoryModel> findDistinctCategoryNamesByAccountId(Long account_id)
    {
        List<Long> idList = EntryModel.findDistinctCategoryByAccountId(account_id);
        return CategoryModel.findCategoryNameListByIdList(idList);
    }

//    private static JsonNode parseEntryContent(String content, String category_name)
//    {
//
//    }
}
