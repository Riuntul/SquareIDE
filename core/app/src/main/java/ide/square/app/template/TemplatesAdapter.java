package ide.square.app.template;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import ide.square.app.R;
import java.util.ArrayList;
import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplatesAdapter.TemplatesViewHolder> {
    private List<Template> templates;

    public TemplatesAdapter(List<Template> templates) {
        this.templates = templates;
    }

    @Override
    public TemplatesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_template, parent, false);

        return new TemplatesViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(TemplatesViewHolder holder, final int position) {
        holder.bindData(templates.get(position));
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }

    static class TemplatesViewHolder extends RecyclerView.ViewHolder {
        View templatesView;

        ImageView image;
        MaterialTextView title;

        TemplatesViewHolder(View templatesView) {
            super(templatesView);
            
            this.templatesView = templatesView;
            
            image = templatesView.findViewById(R.id.image);
            title = templatesView.findViewById(R.id.title);
        }
        
        void bindData(Template templates) {
            image.setImageDrawable(templates.getImage());
            title.setText(templates.getTitle());
        }
    }
}