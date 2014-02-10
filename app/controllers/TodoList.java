package controllers;

import models.CategoryModel;
import models.EntryModel;
import models.ListFormatModel;
import models.viewhelper.ShoppingListBean;
import models.viewhelper.TodoListBean;
import org.codehaus.jackson.map.ObjectMapper;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.GlobalConfiguration;
import utils.Guava;
import views.html.pageNotFound;
import views.html.todolist.showalllists;
import views.html.todolist.showashoppinglist;
import views.html.todolist.showatodolist;
import views.html.todolist.newlist;

import java.util.ArrayList;
import java.util.List;

/**
 * User: nathanchen Date: 1/02/2014 Time: 11:17 AM Description:
 */
public class TodoList extends Controller
{
    public static Form<CategoryModel> newCategoryForm = form(CategoryModel.class);

    public static Result createAList()
    {
        String account_id_in_session = Http.Context.current().session().get(GlobalConfiguration.USER_ID_IN_SESSION);
        Long account_id = Guava.tryParse(account_id_in_session);
        if (account_id == null)
        {
            return redirect(routes.Login.login());
        }

        List<ListFormatModel> listFormatModelList = ListFormatModel.findAll();

        if (listFormatModelList != null && listFormatModelList.size() > 0)
        {
            return ok(newlist.render(newCategoryForm, listFormatModelList, account_id_in_session));
        }
        else
        {
            return forbidden();
        }
    }

    public static Result generateAList()
    {
        String account_id_in_session = Http.Context.current().session().get(GlobalConfiguration.USER_ID_IN_SESSION);
        Long account_id = Guava.tryParse(account_id_in_session);
        if (account_id == null)
        {
            return redirect(routes.Login.login());
        }

        Form<CategoryModel> filledForm = newCategoryForm.bindFromRequest();
        Long list_format_id_entered = Guava.tryParse(filledForm.field("list_format_id").value());
        String category_name_entered = filledForm.field("category_name").valueOr("");

        if (list_format_id_entered == null || ListFormatModel.isAValidListFormat(list_format_id_entered))
        {
            filledForm.reject("Please choose a valid list");
        }
        else if (category_name_entered.isEmpty())
        {
            Logger.info("Please give it a name");
            filledForm.reject("Please give it a name");
        }
        else
        {
            return ok("generateAList ok");
        }
        return ok();
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
            return forbidden();
        }

        else if (entryModelList.size() < 1)
        {
            return forbidden();
        }

        else if (! allowedToView(entryModelList, account_id))
        {
            return ok(pageNotFound.render());
        }

        ListFormatModel listFormatModel = ListFormatModel.findListFormatModelById(categoryModel.list_format_id);
        ObjectMapper mapper = new ObjectMapper();
        // TODO: 可不可以用一个接口来表示todolistBean和shoppinglistBean?
        // TODO: 这样在view界面中，大的框架可以复用？
        if (listFormatModel.list_name.equals("todolist"))
        {
            List<TodoListBean> todoListBeanList = new ArrayList<TodoListBean>(entryModelList.size());
            try
            {
                for (EntryModel entryModel : entryModelList)
                {
                    todoListBeanList.add(mapper.readValue(entryModel.content, TodoListBean.class));
                }
                return ok(showatodolist.render(todoListBeanList, account_id_in_session));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (listFormatModel.list_name.equals("shoppinglist"))
        {
            List<ShoppingListBean> shoppingListBeanList = new ArrayList<ShoppingListBean>(entryModelList.size());
            try
            {
                for (EntryModel entryModel : entryModelList)
                {
                    Logger.info("entryModel.content: " + entryModel.content);
                    shoppingListBeanList.add(mapper.readValue(entryModel.content, ShoppingListBean.class));
                }
                return ok(showashoppinglist.render(shoppingListBeanList, account_id_in_session));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            // list format other than shoppinglist and todolist is forbidden
        }
        return forbidden();
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
}
