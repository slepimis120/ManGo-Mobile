package com.example.uberapp_tim21.activity.tools;

import android.widget.Toast;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.model.MessageType;
import com.example.uberapp_tim21.activity.model.PassengerInbox;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PassengerInboxMockup {

    public static List<PassengerInbox> getMessages() throws ParseException {
        ArrayList<PassengerInbox> messages = new ArrayList<PassengerInbox>();

        String sDate1 = "31-Dec-1998 23:37:50";
        String sDate2 = "02-Jan-2005 23:31:50";
        String sDate3 = "27-Feb-2021 22:11:00";
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.US);
        Date date1=formatter1.parse(sDate1);
        Date date2=formatter1.parse(sDate2);
        Date date3=formatter1.parse(sDate3);

        PassengerInbox message1 = new PassengerInbox("Mika", date1, MessageType.VOZNJA, 1, "Pozdrav! Nadam se da ste uzivali u nasoj voznji", R.drawable.user_icon);
        PassengerInbox message2 = new PassengerInbox("Marko", date2, MessageType.PODRSKA, 2, "Zdravo! Drago nam je da smo uspeli da pomognemo vasem slucaju", R.drawable.user_icon);
        PassengerInbox message3 = new PassengerInbox("Milos", date3, MessageType.PANIK, 3, "Pomoc stize za 5min!", R.drawable.user_icon);

        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        return messages;
    }
}
