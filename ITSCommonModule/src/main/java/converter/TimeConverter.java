/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author vincze.attila
 */
public class TimeConverter extends Converter<Date, String> {

    @Override
    public String convertForward(Date value) {
        Format formatter = new SimpleDateFormat("hh:mm");
        return formatter.format(value);
    }

    @Override
    public Date convertReverse(String value) {
        Date ret = new Date();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        try {
            ret = dateFormat.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(TimeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}