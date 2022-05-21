package com.capstone.everykid.View.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.capstone.everykid.Model.regionCode;
import com.capstone.everykid.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;
    public static final int CHILD_1 = 2;
    private int dataCount = 0; //data에 저장된 CHILD_1의 개수
    private int invisibleDataCount = 0; //숨겨진 CHILD_1의 개수
    private List<Item> data;
    private List<regionCode> codeList;
    private RecyclerView recyclerview;
    private List<ListHeaderViewHolder> headerList = new ArrayList<>();
    public ExpandableListAdapter(List<Item> data, List<regionCode> regionCode, RecyclerView recyclerview) {
        this.data = data;
        this.codeList = regionCode;
        this.recyclerview = recyclerview;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = null;
        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;
        int subItemPaddingLeft = (int) (18 * dp);
        int subItemPaddingTopAndBottom = (int) (5 * dp);
        switch (type) {
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.activity_findkindergarten_listheader, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                headerList.add(header);
                System.out.println("11111");
                return header;
            case CHILD:
            case CHILD_1:
                TextView itemTextView = new TextView(context);
                itemTextView.setPadding(subItemPaddingLeft, subItemPaddingTopAndBottom, 0, subItemPaddingTopAndBottom);
                itemTextView.setTextColor(0x88000000);
                itemTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                return new RecyclerView.ViewHolder(itemTextView) {
                };
        }
        return null;
    }
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        final ListHeaderViewHolder itemController;
        switch (item.type) {
            case HEADER:
                itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;
                itemController.header_title.setText(item.text);
                if (item.invisibleChildren == null) {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.circle_minus);
                } else {
                    itemController.btn_expand_toggle.setImageResource(R.drawable.circle_plus);
                }
                itemController.btn_expand_toggle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);
                            while (data.size() > pos + 1 && (data.get(pos + 1).type == CHILD || data.get(pos + 1).type == CHILD_1)) {
                                if(data.get(pos + 1).type == CHILD_1)
                                    invisibleDataCount++;
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            System.out.println(invisibleDataCount + "개 숨김");
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.circle_plus);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                if(i.type == CHILD_1)
                                    invisibleDataCount--;
                                data.add(index, i);
                                index++;
                            }
                            System.out.println(invisibleDataCount + "개 숨김");
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.btn_expand_toggle.setImageResource(R.drawable.circle_minus);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case CHILD:
                TextView itemTextView = (TextView) holder.itemView;
                itemTextView.setText(data.get(position).text);
                itemTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.set(0, new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, itemTextView.getText().toString()));
                        notifyItemChanged(0);
                        int dataCountTmp = dataCount;
                        if(invisibleDataCount > 0) { //HEADER Item에 숨겨진 데이터가 있다면 전부 삭제한다.
                            data.set(data.size()-1, new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "시군구명"));
                            invisibleDataCount = 0;
                            dataCount = 0;
                            notifyItemChanged(data.size()-1);
                            System.out.println("111");
                        } else {
                            for(int i = 0; i < dataCountTmp; i++) { //Item이 숨겨진 상태가 아니면 모두 제거
                                data.remove(data.size()-1);
                                dataCount--;
                                System.out.println(dataCount + "제거");
                                notifyItemRemoved(data.size()-1);
                            }
                            data.set(data.size()-1, new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "시군구명"));
                            notifyItemChanged(data.size()-1);
                        }
                        for(int i = 0; i < codeList.size(); i++) {
                            if(codeList.get(i).getSiDoName().equals(data.get(0).getData())) {
                                data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD_1, codeList.get(i).getSiGunGuName()));
                                dataCount++;
                                System.out.println(dataCount + "추가");
                                notifyItemInserted(data.size());
                            }
                        }
                        recyclerview.invalidate();
                    }
                });
                break;
            case CHILD_1:
                TextView itemTextView_1 = (TextView) holder.itemView;
                itemTextView_1.setText(data.get(position).text);
                itemTextView_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.set(data.size()-dataCount-1, new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, itemTextView_1.getText().toString()));
                        notifyItemChanged(data.size()-dataCount-1);
                        recyclerview.invalidate();
                    }
                });
        }
    }
    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public List<String> getRegionName() {
        List<String> regionAndName = new ArrayList<>();
        regionAndName.add(data.get(0).getData());
        if(invisibleDataCount > 0)
            regionAndName.add(data.get(data.size()-1).getData());
        else
            regionAndName.add(data.get(data.size()-dataCount-1).getData());
        System.out.println(headerList.size());
        return regionAndName;
    }
    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header_title;
        public ImageView btn_expand_toggle;
        public Item refferalItem;
        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            header_title = (TextView) itemView.findViewById(R.id.header_title);
            btn_expand_toggle = (ImageView) itemView.findViewById(R.id.btn_expand_toggle);
        }
    }
    public static class Item {
        public int type;
        public String text;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }

        public String getData() {
            return text;
        }
    }
}
