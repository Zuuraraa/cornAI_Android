package com.example.moprog_finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> chatMessageList;
    private static final int CHAT_USER = 1;
    private static final int CHAT_BOT = 2;

    public ChatAdapter(List<ChatMessage> chatMessageList) {
        if (chatMessageList == null){
            this.chatMessageList = new ArrayList<>();
        }else {
            this.chatMessageList = chatMessageList;
        }
    }

    @Override
    public int getItemViewType(int position){
        if(chatMessageList.get(position).isUser()){
            return CHAT_USER;
        }else{
            return CHAT_BOT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == CHAT_USER){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_user, parent, false);
            return new UserViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_bot, parent, false);
            return new BotViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessageList.get(position);
        if (holder.getItemViewType() == CHAT_USER) {
            UserViewHolder userViewHolder = (UserViewHolder) holder;
            userViewHolder.tvMessage.setText(chatMessage.getMessage());
        }else {
            BotViewHolder botViewHolder = (BotViewHolder) holder;
            botViewHolder.tvMessage.setText(chatMessage.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        if (chatMessageList == null){
            return 0;
        }else {
            return chatMessageList.size();
        }
    }

    static class BotViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        public BotViewHolder(@NonNull View view) {
            super(view);
            tvMessage = view.findViewById(R.id.tvMessage);
        }
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        public UserViewHolder(@NonNull View view) {
            super(view);
            tvMessage = view.findViewById(R.id.tvMessage);
        }
    }
}
