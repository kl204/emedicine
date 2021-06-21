package com.example.emedicine;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHoder> {

    private List<Person> personList;
    private  ListItemClickListener listItemClickListener;

    public MyAdapter(List<Person> personList, ListItemClickListener listItemClickListener) {
        this.personList = personList;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MyViewHoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MyAdapter.MyViewHoder myViewHoder, int i) {

        myViewHoder.nameText.setText(personList.get(i).getCode());
        myViewHoder.ageText.setText(personList.get(i).getMed_name());
        myViewHoder.codeText.setText(personList.get(i).getEnt_name());
        myViewHoder.medimg.setImageBitmap(personList.get(i).getImg());

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class MyViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameText, ageText, codeText;
        private ImageView medimg;

        public MyViewHoder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.text_code);
            ageText = itemView.findViewById(R.id.text_med_name);
            codeText = itemView.findViewById(R.id.text_ent_name);
            medimg = itemView.findViewById(R.id.med_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            listItemClickListener.listItemClick(getAdapterPosition());
        }
    }

    public  interface ListItemClickListener {
        void listItemClick(int position);
    }
}
