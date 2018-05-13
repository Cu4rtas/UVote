package com.example.jham0.uvote.CandidateItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jham0.uvote.R;

import java.util.ArrayList;
public class AdapterItem extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<CandidateItem> items;
    protected Context context;
    public AdapterItem (Context context,Activity activity, ArrayList<CandidateItem> items) {
        this.context = context;
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }
    public void clear() {
        items.clear();
    }
    public void addAll(ArrayList<CandidateItem> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_structure, null);
        }
        CandidateItem dir = items.get(position);
        TextView canditate = v.findViewById(R.id.tvCandidate);
        canditate.setText(dir.getNombre());
        TextView party = v.findViewById(R.id.tvParty);
        party.setText(dir.getPartido());
        ImageView imageCandidate = v.findViewById(R.id.imageCandidate);
        Glide.with(this.context).load(dir.getUrlImagen()).into(imageCandidate);
        return v;
    }
}