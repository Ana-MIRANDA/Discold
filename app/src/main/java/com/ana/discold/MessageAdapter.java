package com.ana.discold;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ana.discold.Beans.MessageBean;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList<MessageBean> listMsgs;


    public MessageAdapter(ArrayList<MessageBean> msgs) {
        this.listMsgs = msgs;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_message, parent, false);
        return new MessageAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        MessageBean m = listMsgs.get(position); //a msg correspondente a linha
        holder.tvPseudo.setText(m.getUser().getPseudo()); //vai buscar o pseudo
        holder.tv.setText(m.getContent());
    }

    @Override
    public int getItemCount() { return listMsgs.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvPseudo;
        TextView tv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPseudo= itemView.findViewById(R.id.tvPseudo);
            tv = itemView.findViewById(R.id.tv);

        }
    }


}//fecha a classe
