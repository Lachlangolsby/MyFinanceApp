package au.edu.unsw.infs3634.gamifiedlearning.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.Note;
import au.edu.unsw.infs3634.gamifiedlearning.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.BeanHolder> {

    private List<Note> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnNoteItemClick onNoteItemClick;

    public NotesAdapter(List<Note> list, Context context){
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.onNoteItemClick = (OnNoteItemClick) context;
    }

    public interface  OnNoteItemClick {
        void onNoteClick (int pos);
    }

    @NonNull
    @Override
    public BeanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item,parent,false);
        return new BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.BeanHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvContent.setText(list.get(position).getContent());
    }
    @Override
    public int getItemCount(){
        return list.size();
    }
// Bean holder class (View Holder)
    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvContent;
        TextView tvTitle;

        public BeanHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvContent = itemView.findViewById(R.id.item_text);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View view) {
            onNoteItemClick.onNoteClick(getAdapterPosition());

        }
    }
}
