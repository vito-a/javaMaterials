package ua.training.model.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Date time tag.
 */
public class DateTimeTag extends TagSupport {
    public int doStartTag() throws JspException {
        // Returns the instance of JspWriter
        JspWriter out=pageContext.getOut();
        try{
            // Printing date and time using JspWriter
            LocalDateTime ldt = LocalDateTime.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH.mm", Locale.ENGLISH);
            String formatter = dateFormat.format(ldt);
            out.print(formatter);
        } catch(Exception e) {
            System.out.println(e);
        }
        // Will not evaluate the body content of the tag
        return SKIP_BODY;
    }
}