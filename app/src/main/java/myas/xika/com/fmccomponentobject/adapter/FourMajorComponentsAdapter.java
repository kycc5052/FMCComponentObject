package myas.xika.com.fmccomponentobject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import myas.xika.com.fmccomponentobject.R;
import myas.xika.com.fmccomponentobject.bean.FourMajorComponentsBean;

/**
 * Created by XikaKyc on 2016/9/15.
 */
public class FourMajorComponentsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<FourMajorComponentsBean> mFourMajorComponentsBeenDatas;
    public FourMajorComponentsAdapter(Context context,ArrayList<FourMajorComponentsBean> fourMajorComponentsBeenDatas) {
        mContext = context;
        mFourMajorComponentsBeenDatas = fourMajorComponentsBeenDatas;
    }

    @Override
    public int getCount() {
        return mFourMajorComponentsBeenDatas == null ? 0 : mFourMajorComponentsBeenDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mFourMajorComponentsBeenDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_four_major_components_list, null);
            viewHolder.iv_logo_icon = (ImageView) view.findViewById(R.id.item_components_iv_logo_icon);
            viewHolder.tv_logo_name = (TextView) view.findViewById(R.id.item_components_iv_logo_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        FourMajorComponentsBean bean = mFourMajorComponentsBeenDatas.get(i);
        viewHolder.iv_logo_icon.setImageResource(bean.logo_icon);
        viewHolder.tv_logo_name.setText(bean.logo_name);
        return view;
    }

    class ViewHolder {
        ImageView iv_logo_icon;
        TextView tv_logo_name;
    }
}
