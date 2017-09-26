package com.jundat95.contactandroid.ui.contact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jundat95.contactandroid.R;
import com.jundat95.contactandroid.repository.local.model.ContactModel;

import java.util.List;

/**
 * Created by tinhngo on 26/09/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private Context context;
    private List<ContactModel> contactModels;

    public ContactAdapter(Context context, List<ContactModel> contactModels) {
        this.context = context;
        this.contactModels = contactModels;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_contact, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        ContactModel contactModel = contactModels.get(position);
        holder.tvName.setText(contactModel.getName());
        holder.tvPhoneNumber.setText(contactModel.getPhoneNumber());
        if(contactModel.getAvatar() != null) {
            holder.imgAvatar.setImageBitmap(contactModel.getAvatar());
        }
    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }

    public static class ContactHolder extends RecyclerView.ViewHolder{

        private ImageView imgAvatar;
        private TextView tvName, tvPhoneNumber;

        public ContactHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
        }
    }
}
