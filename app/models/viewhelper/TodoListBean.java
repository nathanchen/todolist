package models.viewhelper;

import java.util.Date;

/**
 * User: nathanchen Date: 7/02/2014 Time: 1:42 PM Description:
 */
public class TodoListBean
{
    // whether purchased or not
    private int status;
    // who create this entry
    private Long creator_id;
    // when it was created
    private Date create_date;
    // who modify this entry
    private Long modifier_id;
    // when it was modified
    private Date modify_date;
    // other notes
    private String note;
    // start time
    private Date start_date;
    // end time
    private Date end_date;
    // where this happen
    private String location;
    // what is this entry about
    private String content;
    // with whom
    private String buddies_names;

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

    public String getNote ()
    {
        return note;
    }

    public void setNote (String note)
    {
        this.note = note;
    }

    public Date getStart_date ()
    {
        return start_date;
    }

    public void setStart_date (Date start_date)
    {
        this.start_date = start_date;
    }

    public Date getEnd_date ()
    {
        return end_date;
    }

    public void setEnd_date (Date end_date)
    {
        this.end_date = end_date;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
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
