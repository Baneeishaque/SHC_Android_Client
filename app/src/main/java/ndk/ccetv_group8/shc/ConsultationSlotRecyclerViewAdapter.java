package ndk.ccetv_group8.shc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ndk.ccetv_group8.shc.models.ConsultationSlot;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class ConsultationSlotRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private String mHeaderTitle;

    private OnHeaderClickListener mHeaderClickListener;

    private Context mContext;
    private ArrayList<ConsultationSlot> modelList;

    private OnItemClickListener mItemClickListener;


    public ConsultationSlotRecyclerViewAdapter(Context context, ArrayList<ConsultationSlot> modelList, String headerTitle) {
        this.mContext = context;
        this.modelList = modelList;
        this.mHeaderTitle = headerTitle;
    }

    public void updateList(ArrayList<ConsultationSlot> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_header, parent, false);
            return new HeaderViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_item_recycler_list, parent, false);
            return new ViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.txtTitleHeader.setText(mHeaderTitle);
        } else if (holder instanceof ViewHolder) {
            final ConsultationSlot model = getItem(position - 1);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.itemTxtTitle.setText(model.getSlotStart() + " to " + model.getSlotEnd());
//            genericViewHolder.itemTxtMessage.setText(model.getDesignation());
//            genericViewHolder.textViewWorkingClinic.setText("At " + model.getWorkingClinic());
//            genericViewHolder.textViewWorkingTime.setText("On " + model.getAvailableTimeStart() + " to " + model.getAvailableTimeEnd());
//            genericViewHolder.textViewConsultationFee.setText("Rs. " + model.getConsultationFee());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        return modelList.size() + 1;
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void SetOnHeaderClickListener(final OnHeaderClickListener headerClickListener) {
        this.mHeaderClickListener = headerClickListener;
    }

    private ConsultationSlot getItem(int position) {
        return modelList.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, ConsultationSlot model);
    }

    public interface OnHeaderClickListener {
        void onHeaderClick(View view, String headerTitle);
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleHeader;

        public HeaderViewHolder(final View itemView) {
            super(itemView);
            this.txtTitleHeader = itemView.findViewById(R.id.textViewHeader);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHeaderClickListener.onHeaderClick(itemView, mHeaderTitle);
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //        private ImageView imgUser;
        private TextView itemTxtTitle;
//        private TextView itemTxtMessage, textViewWorkingClinic, textViewWorkingTime, textViewConsultationFee;

        public ViewHolder(final View itemView) {
            super(itemView);

//            this.imgUser = itemView.findViewById(R.id.img_user);
            this.itemTxtTitle = itemView.findViewById(R.id.item_txt_title);
//            this.itemTxtMessage = itemView.findViewById(R.id.item_txt_message);
//            this.textViewWorkingClinic = itemView.findViewById(R.id.textViewWorkingClinic);
//            this.textViewWorkingTime = itemView.findViewById(R.id.textViewWorkingTime);
//            this.textViewConsultationFee = itemView.findViewById(R.id.textViewConsultationFee);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition() - 1));
                }
            });
        }
    }
}

