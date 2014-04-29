/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author vincze.attila
 */
public class LocaleDateConverter extends Converter<Date, String> {

    @Override
    public String convertForward(Date value) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(value);
    }

    @Override
    public Date convertReverse(String value) {
        Date ret = new Date();
        try {
            ret = DateFormat.getDateInstance(
                    DateFormat.MEDIUM, Locale.getDefault()).parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(DateConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
