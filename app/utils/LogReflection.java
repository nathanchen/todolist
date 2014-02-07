package utils;

import models.viewhelper.ShoppingListBean;
import models.viewhelper.TodoListBean;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * User: nathanchen
 * Date: 29/01/2014
 * Time: 10:12 PM
 * Description:
 */
public class LogReflection
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        TodoListBean todoListBean = new TodoListBean(10, 1L, new Date(), null, null, "", new Date("2014/02/01"), new Date("2014/03/01"), "", "work", "");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(todoListBean, JsonNode.class);

        ShoppingListBean shoppingListBean = new ShoppingListBean(10, 1L, new Date(), null, null, "cabbage", 2, new BigDecimal(20.25), new BigDecimal(40.50), "Stallar", "66# zhongyang Road", "Safeway", "", new Date("2014/02/24"), "", "");
        node = mapper.convertValue(shoppingListBean, JsonNode.class);
        System.out.println(node.toString());
    }

    public static Long tryParse(String string) {
        if (checkNotNull(string).isEmpty()) {
            return null;
        }
        boolean negative = string.charAt(0) == '-';
        int index = negative ? 1 : 0;
        if (index == string.length()) {
            return null;
        }
        int digit = string.charAt(index++) - '0';
        if (digit < 0 || digit > 9) {
            return null;
        }
        long accum = -digit;
        while (index < string.length()) {
            digit = string.charAt(index++) - '0';
            if (digit < 0 || digit > 9 || accum < Long.MIN_VALUE / 10) {
                return null;
            }
            accum *= 10;
            if (accum < Long.MIN_VALUE + digit) {
                return null;
            }
            accum -= digit;
        }

        if (negative) {
            return accum;
        } else if (accum == Long.MIN_VALUE) {
            return null;
        } else {
            return -accum;
        }
    }
}
