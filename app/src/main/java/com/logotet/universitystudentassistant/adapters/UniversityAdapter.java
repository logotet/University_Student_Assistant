package com.logotet.universitystudentassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.logotet.universitystudentassistant.R;
import com.logotet.universitystudentassistant.data.entities.UniversityEntity;

import java.util.ArrayList;
import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityHolder> {

    private List<UniversityEntity> universityEntityList = new ArrayList<>();
    private UniversityHolder.OnItemPressedListener listener;

    public UniversityAdapter(UniversityHolder.OnItemPressedListener listener) {
        this.listener = listener;
    }

    public void updateData(List<UniversityEntity> data) {
        universityEntityList.clear();
        universityEntityList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UniversityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new UniversityHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityHolder holder, int position) {
        UniversityEntity universityEntity = universityEntityList.get(position);
        holder.name.setText(universityEntity.getName());
        holder.setUnversity(universityEntity);
    }

    @Override
    public int getItemCount() {
        return universityEntityList.size();
    }

    public static class UniversityHolder extends RecyclerView.ViewHolder {
        private UniversityEntity universityEntity;
        private TextView name;
        private ImageButton imageButton;

        public UniversityHolder(@NonNull View itemView, OnItemPressedListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_uni_name);
            imageButton = itemView.findViewById(R.id.img_favourite);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), name.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onFavButtonClicked(universityEntity);
                }
            });
        }

        public void setUnversity(UniversityEntity unversity){
            this.universityEntity = unversity;
        }

        public interface OnItemPressedListener{
            void onFavButtonClicked(UniversityEntity entity);
        }


    }
}
