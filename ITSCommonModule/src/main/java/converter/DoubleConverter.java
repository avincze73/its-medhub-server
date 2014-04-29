/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author vincze.attila
 */
public class DoubleConverter  extends Converter<Double, String> {

    @Override
    public String convertForward(Double value) {
        System.out.println(value);
        return  value.toString();
        
    }

    @Override
    public Double convertReverse(String value) {
        return Double.parseDouble(value);
    }

}
