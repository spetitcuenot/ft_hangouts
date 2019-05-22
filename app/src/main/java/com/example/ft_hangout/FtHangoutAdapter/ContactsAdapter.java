package com.example.ft_hangout.FtHangoutAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.ft_hangout.Entity.Contacts;
import com.example.ft_hangout.R;
import com.example.ft_hangout.interfaces.OnContactListener;

import java.util.ArrayList;
import java.util.List;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {

    public List<Contacts> _contact = new ArrayList<>();
    public Context _context;
    public OnContactListener listener;
    public int lastIndexContactSelected = -1;

    public ContactsAdapter(Context context, List<Contacts> contactsList, OnContactListener listener) {
        this._context = context;
        this._contact = contactsList;
        this.listener = listener;
        if(this._contact == null)
            this._contact = new ArrayList<>();
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        ContactsHolder contactsHolder = new ContactsHolder(itemView);
        return contactsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder holder, final int position) {
        int imageResource;

        Contacts currentContact = _contact.get(position);
        String avatar = currentContact.getAvatar();
        imageResource = this._context.getResources().getIdentifier(avatar, null, "com.example.ft_hangout");
        Drawable res = ContextCompat.getDrawable(this._context, imageResource);
        holder.imageView.setImageDrawable(res);
        holder.textFulltName.setText(currentContact.getLastname() + " " + currentContact.getFirstname());
        boolean expanded = currentContact.isExpanded();
        holder.item_hide.setVisibility(expanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return _contact.size();
    }

    public Contacts getContactAt(int position) {
      return _contact.get(position);

    }

    public void setContacts(List<Contacts> contacts) {
        this._contact = contacts;
        notifyDataSetChanged();
    }


    public class ContactsHolder extends RecyclerView.ViewHolder {

        private LinearLayout item_hide, item_visible;
        private ImageView imageView;
        private TextView textFulltName;

        public ContactsHolder(View itemView) {
            super(itemView);

            item_hide = itemView.findViewById(R.id.linearlayout2);
            item_visible = itemView.findViewById(R.id.linearlayout1);
            imageView = itemView.findViewById(R.id.avatar);
            textFulltName = itemView.findViewById(R.id.textViewFullname);

            item_visible.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (lastIndexContactSelected != -1)
                    {
                        _contact.get(lastIndexContactSelected).setExpanded(false);
                        //notifyDataSetChanged();

                    }
                    listener.onContactSelected(_contact.get(getAdapterPosition()));
                    notifyItemChanged(lastIndexContactSelected);
                    lastIndexContactSelected = getAdapterPosition();
                    notifyItemChanged(getAdapterPosition());

                }
            });

        }

    }
}

