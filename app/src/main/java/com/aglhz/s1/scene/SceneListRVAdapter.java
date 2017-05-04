package com.aglhz.s1.scene;

import com.aglhz.s1.R;
import com.aglhz.s1.bean.SceneBean;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daimajia.swipe.SwipeLayout;

import java.util.List;

import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseRecyclerViewAdapter;

/**
 * Author: LiuJia on 2017/4/27 0027 15:29.
 * Email: liujia95me@126.com
 */

public class SceneListRVAdapter extends BaseRecyclerViewAdapter<SceneBean, BaseViewHolder> {

    public SceneListRVAdapter(List<SceneBean> data) {
        super(R.layout.item_scene, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SceneBean item) {
        helper.setText(R.id.tv_scene_item_scene, item.scene)
                .setText(R.id.tv_toggle_item_scene, item.open ? "启动场景" : "关闭场景")
                .addOnClickListener(R.id.tv_delete_item_scene)
                .setBackgroundColor(R.id.tv_toggle_item_scene, item.open ? 0xFF0083FF : 0xFFFF0000);
    }
}
