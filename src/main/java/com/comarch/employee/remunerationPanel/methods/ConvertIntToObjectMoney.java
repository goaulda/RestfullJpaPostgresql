package com.comarch.employee.remunerationPanel.methods;

import com.comarch.employee.remunerationPanel.model.ObjectMoney;
import org.springframework.stereotype.Service;

@Service
public class ConvertIntToObjectMoney {

    public ObjectMoney covertIntToDouble(int average) {

        String averageString = String.valueOf(average);
        final int lengthString = averageString.length();
        final String gr = averageString.substring(0, lengthString-2);
        final String zl = averageString.substring(lengthString-2, lengthString);

        ObjectMoney objectMoney = new ObjectMoney();
        objectMoney.setZl(zl);
        objectMoney.setGr(gr);
        return objectMoney;
    }
}
