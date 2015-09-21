/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package test2;
import java.sql.Time;
import java.sql.Date;

// line 3 "../umptest2.ump"
public class Event
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Event Attributes
  private Time startTime;
  private Time endTime;
  private String name;
  private Date eventDate;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Event(Time aStartTime, Time aEndTime, String aName, Date aEventDate)
  {
    startTime = aStartTime;
    endTime = aEndTime;
    name = aName;
    eventDate = aEventDate;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setEventDate(Date aEventDate)
  {
    boolean wasSet = false;
    eventDate = aEventDate;
    wasSet = true;
    return wasSet;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public String getName()
  {
    return name;
  }

  public Date getEventDate()
  {
    return eventDate;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startTime" + "=" + (getStartTime() != null ? !getStartTime().equals(this)  ? getStartTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endTime" + "=" + (getEndTime() != null ? !getEndTime().equals(this)  ? getEndTime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "eventDate" + "=" + (getEventDate() != null ? !getEventDate().equals(this)  ? getEventDate().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}