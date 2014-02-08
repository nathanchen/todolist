package models.viewhelper;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: nathanchen Date: 7/02/2014 Time: 1:42 PM Description:
 */
public class ShoppingListBean
{
    // whether purchased or not
    public int status;
    // who create this entry
    public Long creator_id;
    // when it was created
    public Date create_date;
    // who modify this entry
    public Long modifier_id;
    // when it was modified
    public Date modify_date;
    // what is the item's name
    public String item_name;
    // how many items
    public int quantity;
    // price for one
    public BigDecimal item_price;
    // price for one * quantity
    public BigDecimal total_price;
    // purchase this item for whom
    public String toWhom;
    // exact location
    public String purchase_location;
    // shopper's name
    public String purchase_place;
    // GPRS
    public String gprs_data;
    // when to purchase
    public Date purchase_date;
    // other notes
    public String note;
    // with whom
    public String buddies_names;

    public ShoppingListBean() {}

    public ShoppingListBean (int status, Long creator_id, Date create_date, Long modifier_id, Date modify_date, String item_name, int quantity, BigDecimal item_price, BigDecimal total_price, String toWhom, String purchase_location, String purchase_place, String gprs_data, Date purchase_date, String note, String buddies_names)
    {
        this.status = status;
        this.creator_id = creator_id;
        this.create_date = create_date;
        this.modifier_id = modifier_id;
        this.modify_date = modify_date;
        this.item_name = item_name;
        this.quantity = quantity;
        this.item_price = item_price;
        this.total_price = total_price;
        this.toWhom = toWhom;
        this.purchase_location = purchase_location;
        this.purchase_place = purchase_place;
        this.gprs_data = gprs_data;
        this.purchase_date = purchase_date;
        this.note = note;
        this.buddies_names = buddies_names;
    }

    public int getStatus ()
    {
        return status;
    }

    public void setStatus (int status)
    {
        this.status = status;
    }

    public Long getCreator_id ()
    {
        return creator_id;
    }

    public void setCreator_id (Long creator_id)
    {
        this.creator_id = creator_id;
    }

    public Date getCreate_date ()
    {
        return create_date;
    }

    public void setCreate_date (Date create_date)
    {
        this.create_date = create_date;
    }

    public Long getModifier_id ()
    {
        return modifier_id;
    }

    public void setModifier_id (Long modifier_id)
    {
        this.modifier_id = modifier_id;
    }

    public Date getModify_date ()
    {
        return modify_date;
    }

    public void setModify_date (Date modify_date)
    {
        this.modify_date = modify_date;
    }

    public String getItem_name ()
    {
        return item_name;
    }

    public void setItem_name (String item_name)
    {
        this.item_name = item_name;
    }

    public int getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (int quantity)
    {
        this.quantity = quantity;
    }

    public BigDecimal getItem_price ()
    {
        return item_price;
    }

    public void setItem_price (BigDecimal item_price)
    {
        this.item_price = item_price;
    }

    public BigDecimal getTotal_price ()
    {
        return total_price;
    }

    public void setTotal_price (BigDecimal total_price)
    {
        this.total_price = total_price;
    }

    public String getToWhom ()
    {
        return toWhom;
    }

    public void setToWhom (String toWhom)
    {
        this.toWhom = toWhom;
    }

    public String getPurchase_location ()
    {
        return purchase_location;
    }

    public void setPurchase_location (String purchase_location)
    {
        this.purchase_location = purchase_location;
    }

    public String getPurchase_place ()
    {
        return purchase_place;
    }

    public void setPurchase_place (String purchase_place)
    {
        this.purchase_place = purchase_place;
    }

    public String getGprs_data ()
    {
        return gprs_data;
    }

    public void setGprs_data (String gprs_data)
    {
        this.gprs_data = gprs_data;
    }

    public Date getPurchase_date ()
    {
        return purchase_date;
    }

    public void setPurchase_date (Date purchase_date)
    {
        this.purchase_date = purchase_date;
    }

    public String getNote ()
    {
        return note;
    }

    public void setNote (String note)
    {
        this.note = note;
    }

    public String getBuddies_names ()
    {
        return buddies_names;
    }

    public void setBuddies_names (String buddies_names)
    {
        this.buddies_names = buddies_names;
    }
}
