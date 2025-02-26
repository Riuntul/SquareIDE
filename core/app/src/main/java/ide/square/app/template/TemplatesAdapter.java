package ide.square.app.template;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import ide.square.app.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplatesAdapter.TemplatesViewHolder> {
    private List<Template> templateList;
    
    private TemplatesAdapter.OnItemClickListener listener;

    public TemplatesAdapter(List<Template> templateList, TemplatesAdapter.OnItemClickListener listener) {
        this.templateList = templateList;
        
        this.listener = listener;
    }

    @Override
    public TemplatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template, parent, false);

        return new TemplatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TemplatesViewHolder holder, final int position) {
        Template template = templateList.get(position);
        
        holder.image.setImageDrawable(template.getImage());
        holder.title.setText(template.getName());
            
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(template);
            }
        });
    }

    @Override
    public int getItemCount() {
        return templateList.size();
    }

    static class TemplatesViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        MaterialTextView title;
        
        MaterialCardView container;

        TemplatesViewHolder(View templateView) {
            super(templateView);
            
            image = templateView.findViewById(R.id.image);
            title = templateView.findViewById(R.id.title);
            
            container = templateView.findViewById(R.id.container);
        }
    }
    
    public interface OnItemClickListener {
        void onItemClick(Template template);
    }
}