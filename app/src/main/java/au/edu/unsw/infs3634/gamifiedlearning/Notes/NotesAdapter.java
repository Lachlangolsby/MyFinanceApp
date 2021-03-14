package au.edu.unsw.infs3634.gamifiedlearning.Notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.BeanHolder> {
    // declaring variables to be used throughout class
    private List<Note> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnNoteItemClick onNoteItemClick;

    // creating constructor
    public NotesAdapter(List<Note> list, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.onNoteItemClick = (OnNoteItemClick) context;
    }

    @NonNull
    @Override
    // onCreate view holder for recycler view
    public BeanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item, parent, false);
        return new BeanHolder(view);
    }

    // loading elements to recycler view
    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.BeanHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvContent.setText(list.get(position).getContent());
    }

    // retrieving size of recycler view
    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnNoteItemClick {
        void onNoteClick(int pos);
    }

    // initialising elements for onbind view holder
    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvContent;
        TextView tvTitle;

        // connecting elements to xml elements
        public BeanHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvContent = itemView.findViewById(R.id.item_text);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        // retrieve which not was clicked
        @Override
        public void onClick(View view) {
            onNoteItemClick.onNoteClick(getAdapterPosition());

        }
    }
}
