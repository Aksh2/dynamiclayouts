package learning.com.dynamiclayouts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import learning.com.dynamiclayouts.R;
import learning.com.dynamiclayouts.model.ReportModel;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportItemsViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<ReportModel> reportModelList = new ArrayList<>();
    public ReportListAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ReportItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = this.layoutInflater.inflate(R.layout.item_list,viewGroup,false);
        return new ReportListAdapter.ReportItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportItemsViewHolder holder, int position) {
        if(!TextUtils.isEmpty(reportModelList.get(position).getField2()))
            holder.ageTv.setText(reportModelList.get(position).getField2());
        holder.nameTv.setText(reportModelList.get(position).getField1());
    }

    @Override
    public int getItemCount() {
        return (this.reportModelList!=null)? reportModelList.size():0;
    }

    public void setData(List<ReportModel> reportModels){
        this.reportModelList=reportModels;
        notifyDataSetChanged();
    }
    static class ReportItemsViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nameTv)
        TextView nameTv;

        @BindView(R.id.ageTv)
        TextView ageTv;

        public ReportItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
