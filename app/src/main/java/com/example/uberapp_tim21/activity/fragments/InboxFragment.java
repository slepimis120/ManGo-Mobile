package com.example.uberapp_tim21.activity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import com.example.uberapp_tim21.R;
import com.example.uberapp_tim21.activity.adapters.InboxAdapter;
import com.example.uberapp_tim21.activity.model.PassengerInbox;
import com.example.uberapp_tim21.activity.passenger.PassengerInboxActivity;
import com.example.uberapp_tim21.activity.tools.PassengerInboxMockup;

import java.text.ParseException;

public class InboxFragment extends ListFragment {

    public static InboxFragment newInstance() {
        return new InboxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        View rootView=inflater.inflate(R.layout.inbox_layout, vg, false);
        InboxAdapter adapter = new InboxAdapter(getActivity());
        ListView lv = (ListView) rootView.findViewById(android.R.id.list);
        lv.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        PassengerInbox inbox = null;
        try {
            inbox = PassengerInboxMockup.getMessages().get(position);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getActivity(), PassengerInboxActivity.class);
        intent.putExtra("sender", inbox.getSender());
        intent.putExtra("content", inbox.getContent());
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
