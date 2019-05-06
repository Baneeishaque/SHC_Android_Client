package ndk.ccetv_group8.shc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ndk.ccetv_group8.shc.models.AppointmentModel;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class AppointmentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<AppointmentModel> modelList;

    private OnItemClickListener mItemClickListener;


    public AppointmentRecyclerViewAdapter(Context context, ArrayList<AppointmentModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<AppointmentModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appointment_item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final AppointmentModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;

//            genericViewHolder.itemTxtTitle.setText(model.getTitle());
//            genericViewHolder.itemTxtMessage.setText(model.getMessage());

            genericViewHolder.textViewSlot.setText(model.getConsultationSlot());
            genericViewHolder.textViewName.setText(model.getName());
            genericViewHolder.textViewAddress.setText(model.getAddress());
            genericViewHolder.textViewContactNumber.setText(model.getContactNumber());
            genericViewHolder.textViewDisease.setText(model.getDisease());
        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private AppointmentModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, AppointmentModel model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        private ImageView imgUser;
//        private TextView itemTxtTitle;
//        private TextView itemTxtMessage;

        private TextView textViewSlot, textViewName, textViewAddress, textViewContactNumber, textViewDisease;

        // @BindView(R.id.img_user)
        // ImageView imgUser;
        // @BindView(R.id.item_txt_title)
        // TextView itemTxtTitle;
        // @BindView(R.id.item_txt_message)
        // TextView itemTxtMessage;
        // @BindView(R.id.radio_list)
        // RadioButton itemTxtMessage;
        // @BindView(R.id.check_list)
        // CheckBox itemCheckList;
        public ViewHolder(final View itemView) {
            super(itemView);

            // ButterKnife.bind(this, itemView);

//            this.imgUser = (ImageView) itemView.findViewById(R.id.img_user);
//            this.itemTxtTitle = (TextView) itemView.findViewById(R.id.item_txt_title);
//            this.itemTxtMessage = (TextView) itemView.findViewById(R.id.item_txt_message);

            this.textViewSlot = itemView.findViewById(R.id.textViewSlot);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewAddress = itemView.findViewById(R.id.textViewAddress);
            this.textViewContactNumber = itemView.findViewById(R.id.textViewContactNumber);
            this.textViewDisease = itemView.findViewById(R.id.textViewDisease);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));


                }
            });

        }
    }

}

