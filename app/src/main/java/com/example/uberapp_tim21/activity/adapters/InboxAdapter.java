package com.example.uberapp_tim21.activity.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.model.MessageType;
import com.example.uberapp_tim21.activity.model.PassengerInbox;
import com.example.uberapp_tim21.activity.tools.PassengerInboxMockup;

import java.text.ParseException;

public class InboxAdapter extends BaseAdapter {

    private Activity activity;

    public InboxAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        try {
            return PassengerInboxMockup.getMessages().size();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        try {
            return PassengerInboxMockup.getMessages().get(position);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        try {
            PassengerInbox message = PassengerInboxMockup.getMessages().get(position);
            if(convertView==null)
                vi = activity.getLayoutInflater().inflate(R.layout.message_list, null);

            TextView sender = (TextView)vi.findViewById(R.id.sender);
            TextView content = (TextView)vi.findViewById(R.id.content);
            ImageView image = (ImageView)vi.findViewById(R.id.item_icon);

            sender.setText(message.getSender());
            content.setText(message.getContent());

            if (message.getAvatar() != -1){
                image.setImageResource(message.getAvatar());
            }

            if (message.getType().equals(MessageType.VOZNJA)){
                vi.setBackgroundColor(Color.parseColor("#203354"));
            }else if (message.getType().equals(MessageType.PANIK)) {
                vi.setBackgroundColor(Color.parseColor("#F47174"));
            }else {
                vi.setBackgroundColor(Color.parseColor("#abd7eb"));
            }

            return  vi;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vi;
    }
}
