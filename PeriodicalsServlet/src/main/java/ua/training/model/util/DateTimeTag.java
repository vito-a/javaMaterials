package ua.training.model.util;

import java.util.Calendar;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTimeTag extends TagSupport{
    public int doStartTag() throws JspException {
        // Returns the instance of JspWriter
        JspWriter out=pageContext.getOut();
        try{
            // Printing date and time using JspWriter
            out.print(Calendar.getInstance().getTime());
        }catch(Exception e){
            System.out.println(e);
        }
        // Will not evaluate the body content of the tag
        return SKIP_BODY;
    }
}