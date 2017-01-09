/*
 * Copyright (c) 2016.
 *  Nirav Tukadiya
 *  Programmer Analyst ( Android)
 *  Meditab Software Inc.
 */

package com.meditab.commonutils.widgets.easyrecyclerview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meditab.commonutils.R;
import com.meditab.commonutils.parent.ParentAdapter;
import com.meditab.commonutils.exceptions.ImplementationException;

/**
 * Author
 * Nirav Tukadiya
 * Meditab Software Inc.
 * niravt@meditab.com
 * Created on 6/23/16 8:22 PM
 */
public class EasyRecyclerView<T extends ParentAdapter> extends LinearLayout implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    /**
     * Layout Ids
     */
    private FrameLayout cu_id_fl_main;
    private RelativeLayout cu_id_rl_no_data;
    private TextView cu_id_tv_msg;
    private ImageView cu_id_img_retry;
    private TextView cu_id_tv_retry;
    private LinearLayout cu_id_ll;
    private SwipeRefreshLayout cu_id_srl;
    private RecyclerView cu_id_rv_list;
    private ProgressBar cu_id_pbar_bottom;
    private RelativeLayout cu_id_rl_loading;
    private TextView cu_id_tv_loading_msg;
    private ProgressBar cu_id_pbar_center;

    /**
     * Default values of Properties if not set.
     */
    interface Defaults {
        String RETRY = "Retry";
        String LOADING = "Loading";
        String NO_RECORDS = "No records available";
        int RETRY_ICON = R.drawable.cu_ic_refresh_24_dp;
    }

    /**
     * Properties to be set to {@link EasyRecyclerView}.
     */

    //Text to be shown while loading data.
    String strLoadingText = null;

    //Message to be shown for empty view.
    String strNodataMsg = Defaults.NO_RECORDS;

    //Text to be shown for empty view 'Retry' label
    String strRetryText = Defaults.RETRY;

    // Image Resource Id to be shown for empty view 'Retry' Icon
    int retryIcon = R.drawable.cu_ic_refresh_24_dp;

    //Flag if SwipeRefresh is enabled or not
    boolean enableSwipeRefresh;

    //Flag if Load More is enabled or not
    boolean enableLoadMore;

    //Flag if it should set DefaultItemAnimator or not
    boolean setDefaultItemAnimator;

    //Flag if it should set DefaultItemDecoration or not
    boolean setDefaultItemDecoration;


    /**
     * different loading states of {@link EasyRecyclerView}
     * <p>
     * LOADING_STARTED - starts showing loading (shows Progressbar in the center of layout.), Hides RecyclerView and EmptyView.
     * LOADING_STARTED_LOAD_MORE - starts showing loading (shows Progressbar at the bottom of RecyclerView's content.)
     * NO_DATA - shows EmptyView with 'Message' , 'Icon' and 'Label', Hides Loading Progressbar and RecyclerView.
     * LOADING_COMPLETE - shows RecyclerView, hides Loading ProgressBar and EmptyView
     */
    public enum LoadingState {
        LOADING_STARTED, LOADING_STARTED_LOAD_MORE, NO_DATA, LOADING_COMPLETE
    }

    //Saves current loading state
    LoadingState currentLoadingState;

    //Layout manager instance which has been set to {@link RecyclerView}.
    LinearLayoutManager layoutManager;

    //Adapter instance to be set to {@link RecyclerView}
    T listAdapter;

    //ItemDecoration to be set to {@link RecyclerView}.
    RecyclerView.ItemDecoration itemDecoration;

    //ItemAnimator to be set to {@link RecyclerView}.
    RecyclerView.ItemAnimator itemAnimator;

    //LoadMore Listener
    ERVOnLoadMoreClickListener mERVOnLoadMoreClickListener;

    //SwipeRefresh Listener
    ERVOnRefreshListener mERVOnRefreshListener;


    //Default constructor
    public EasyRecyclerView(Context context) throws ImplementationException {
        super(context);
        initView();
    }

    //Default constructor
    public EasyRecyclerView(Context context, AttributeSet attrs) throws ImplementationException {
        super(context, attrs);
        loadAttrs(context, attrs);
        initView();
    }

    //Default constructor
    public EasyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) throws ImplementationException {
        super(context, attrs, defStyleAttr);
        loadAttrs(context, attrs);
        initView();
    }

    //Default constructor
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EasyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) throws ImplementationException {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadAttrs(context, attrs);
        initView();
    }

    /**
     * Load Default Properties values if set from xml file.
     *
     * @param context context
     * @param attrs   AttributeSet
     */
    private void loadAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EasyRecyclerView, 0, 0);

        enableSwipeRefresh = a.getBoolean(R.styleable.EasyRecyclerView_enableSwipeToRefresh, false);
        enableLoadMore = a.getBoolean(R.styleable.EasyRecyclerView_enableLoadMore, false);
        strLoadingText = a.getString(R.styleable.EasyRecyclerView_loadingText);
        strNodataMsg = a.getString(R.styleable.EasyRecyclerView_noDataMessage);
        strRetryText = a.getString(R.styleable.EasyRecyclerView_retryText);
        retryIcon = a.getResourceId(R.styleable.EasyRecyclerView_retryIcon, R.drawable.cu_ic_refresh_24_dp);
        setDefaultItemAnimator = a.getBoolean(R.styleable.EasyRecyclerView_setDefaultItemAnimator, false);
        setDefaultItemDecoration = a.getBoolean(R.styleable.EasyRecyclerView_setDefaultItemDecoration, false);

        a.recycle();
    }


    /**
     * Initialize Views and set up event listeners
     */
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.cu_listcomponent, this, true);
        cu_id_fl_main = (FrameLayout) view.findViewById(R.id.cu_id_fl_main);
        cu_id_rl_no_data = (RelativeLayout) view.findViewById(R.id.cu_id_rl_no_data);
        cu_id_tv_msg = (TextView) view.findViewById(R.id.cu_id_tv_msg);
        cu_id_img_retry = (ImageView) view.findViewById(R.id.cu_id_img_retry);
        cu_id_tv_retry = (TextView) view.findViewById(R.id.cu_id_tv_retry);
        cu_id_ll = (LinearLayout) view.findViewById(R.id.cu_id_ll);
        cu_id_srl = (SwipeRefreshLayout) view.findViewById(R.id.cu_id_srl);
        cu_id_rv_list = (RecyclerView) view.findViewById(R.id.cu_id_rv_list);
        cu_id_pbar_bottom = (ProgressBar) view.findViewById(R.id.cu_id_pbar_bottom);
        cu_id_pbar_center = (ProgressBar) view.findViewById(R.id.cu_id_pbar_center);
        cu_id_rl_loading = (RelativeLayout) view.findViewById(R.id.cu_id_rl_loading);
        cu_id_tv_loading_msg = (TextView) view.findViewById(R.id.cu_id_tv_loading_msg);

        cu_id_img_retry.setOnClickListener(this);

        loadDefaultConfig();
    }

    /**
     * Load Default configurations
     */
    private void loadDefaultConfig() {
        setUpSwipeToRefresh();
    }

    /**
     * Enable of disable Swipe Refresh feature based on {@link EasyRecyclerView#enableSwipeRefresh}
     */
    private void setUpSwipeToRefresh() {
        if (enableSwipeRefresh) {
            cu_id_srl.setEnabled(true);
            cu_id_srl.setOnRefreshListener(this);
        } else {
            cu_id_srl.setEnabled(false);
        }
    }

    /**
     * Enable or disable Load More feature based on {@link EasyRecyclerView#enableLoadMore}
     * <p>
     * NOTE : Load More setup cannot be done while initializing the component because It require {@link LinearLayoutManager} instance.
     *
     * @param layoutManager LinearLayoutManager
     */
    private void setUpLoadMore(LinearLayoutManager layoutManager) {
        //Clears all previous scroll listeners
        cu_id_rv_list.clearOnScrollListeners();
        if (enableLoadMore) {
            cu_id_rv_list.addOnScrollListener(getEndlessScrollListener(layoutManager));
        }
    }

    /**
     * Get {@link ERVScrollListener } instance
     *
     * @param layoutManager LinearLayoutManager
     * @return
     */
    private RecyclerView.OnScrollListener getEndlessScrollListener(LinearLayoutManager layoutManager) {
        return new ERVScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                /**
                 * Calls {@link ERVOnLoadMoreClickListener#onLoadMore(int)} if instance has been set.
                 */
                if (mERVOnLoadMoreClickListener != null) {
                    mERVOnLoadMoreClickListener.onLoadMore(current_page);
                }
            }
        };
    }


    /**
     * @return ItemAnimator instance if set, otherwise returns default {@link DefaultItemAnimator} instance If {@link EasyRecyclerView#setDefaultItemAnimator is set to true}
     */
    @NonNull
    private RecyclerView.ItemAnimator getItemAnimator() {
        return itemAnimator == null ? (setDefaultItemAnimator ? new DefaultItemAnimator() : null) : itemAnimator;
    }

    /**
     * @return ItemDecoration instance if set, otherwise returns default {@link DefaultItemDecoration} instance If {@link EasyRecyclerView#setDefaultItemDecoration} is set to true
     */
    @NonNull
    private RecyclerView.ItemDecoration getItemDecoration() {
        return itemDecoration == null ? (setDefaultItemDecoration ? new DefaultItemDecoration(getContext()) : null) : itemDecoration;
    }


    /**
     * Set an Adapter to {@link RecyclerView}
     *
     * @param adapter       adapter to be set to {@link RecyclerView}
     * @param layoutManager layout manager to be set to {@link RecyclerView}
     */
    public void setAdapter(T adapter, LinearLayoutManager layoutManager) {
        setUpLoadMore(layoutManager);
        this.listAdapter = adapter;
        this.layoutManager = layoutManager;
        cu_id_rv_list.setLayoutManager(layoutManager);
        cu_id_rv_list.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = getItemDecoration();
        RecyclerView.ItemAnimator itemAnimator = getItemAnimator();

        if (itemDecoration != null) {
            cu_id_rv_list.addItemDecoration(itemDecoration);
        }

        if (itemAnimator != null) {
            cu_id_rv_list.setItemAnimator(itemAnimator);
        }

    }


    /**
     * Shows Loading
     *
     * @param loadingMore If set to true, shows ProgressBar at the end of {@link RecyclerView } contents, else shows ProgressBar in center of layout.
     */
    public void startLoading(boolean loadingMore) {
        changeState(loadingMore ? LoadingState.LOADING_STARTED_LOAD_MORE : LoadingState.LOADING_STARTED);
    }


    /**
     * Hides Loading
     *
     * @param hasData If set to true, shows {@link RecyclerView } contents, else shows EmptyView with 'Message', 'Icon' and 'Label'.
     */
    public void stopLoading(boolean hasData) {
        changeState(hasData ? LoadingState.LOADING_COMPLETE : LoadingState.NO_DATA);
    }


    /**
     * show/hides layouts based on {@link EasyRecyclerView#currentLoadingState}
     *
     * @param loadingState
     */
    private void changeState(LoadingState loadingState) {
        currentLoadingState = loadingState;
        switch (loadingState) {
            case LOADING_STARTED:
                cu_id_rl_no_data.setVisibility(GONE);
                cu_id_ll.setVisibility(GONE);
                cu_id_pbar_bottom.setVisibility(GONE);
                cu_id_rl_loading.setVisibility(VISIBLE);
                break;
            case LOADING_STARTED_LOAD_MORE:
                cu_id_rl_no_data.setVisibility(GONE);
                cu_id_ll.setVisibility(VISIBLE);
                cu_id_pbar_bottom.setVisibility(VISIBLE);
                cu_id_rl_loading.setVisibility(GONE);
                break;
            case NO_DATA:
                cu_id_rl_no_data.setVisibility(VISIBLE);
                cu_id_ll.setVisibility(GONE);
                cu_id_pbar_bottom.setVisibility(GONE);
                cu_id_rl_loading.setVisibility(GONE);
                break;
            case LOADING_COMPLETE:
                cu_id_rl_no_data.setVisibility(GONE);
                cu_id_ll.setVisibility(VISIBLE);
                cu_id_pbar_bottom.setVisibility(GONE);
                cu_id_rl_loading.setVisibility(GONE);
                break;
        }


        loadLoadingProperties(loadingState);

    }

    /**
     * Show/hide/sets Properties of {@link EasyRecyclerView}
     *
     * @param loadingState
     */
    private void loadLoadingProperties(LoadingState loadingState) {
        switch (loadingState) {
            case LOADING_STARTED:
                //hides loading message if not set.
                cu_id_tv_loading_msg.setVisibility(((strLoadingText == null) ? GONE : VISIBLE));
                //sets Loading text if set, else blank.
                cu_id_tv_loading_msg.setText(((strLoadingText == null) ? "" : strLoadingText));
                break;
            case NO_DATA:

                //Sets No Data message if set, else sets default message
                if (strNodataMsg != null) {
                    cu_id_tv_msg.setText(strNodataMsg);
                } else {
                    cu_id_tv_msg.setText(Defaults.NO_RECORDS);
                }

                //Sets Retry Label  if set, else sets default label
                if (strRetryText != null) {
                    cu_id_tv_retry.setText(strRetryText);
                } else {
                    cu_id_tv_retry.setText(Defaults.RETRY);
                }

                //Sets Retry Icon  if set, else sets default icon
                if (retryIcon != 0) {
                    cu_id_img_retry.setImageResource(retryIcon);
                } else {
                    cu_id_img_retry.setImageResource(Defaults.RETRY_ICON);
                }

                break;

        }
    }

    @Override
    public void onRefresh() {
        cu_id_srl.setRefreshing(false);
        /**
         * Calls {@link ERVOnRefreshListener#onRefresh()} if instance has been set.
         */
        if (mERVOnRefreshListener != null) {
            mERVOnRefreshListener.onRefresh();
        }
    }

    /**
     * Reset Current loading page to 1st.
     * <p>
     * NOTE : Must call if You are clearing all items or setting new adapter.
     */
    public void resetLoadMore() {
        setUpLoadMore(layoutManager);
    }


    /**
     * Setter Method for {@link EasyRecyclerView} Properties
     */


    /**
     * @param enableSwipeRefresh enable or disable SwipeRefresh
     */
    public void enableSwipeRefresh(boolean enableSwipeRefresh) {
        this.enableSwipeRefresh = enableSwipeRefresh;
        setUpSwipeToRefresh();
    }

    /**
     * @param enableLoadMore enable or disable LoadMore
     */
    public void enableLoadMore(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
        setUpLoadMore(layoutManager);
    }

    /**
     * Notify {@link android.support.v7.widget.RecyclerView.Adapter content has changed}
     */
    public void notifyDataSetChanged() {
        if (listAdapter != null) {
            listAdapter.notifyDataSetChanged();
        }
    }

    /**
     * OnClick Event of Retry button
     */
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cu_id_img_retry) {
            if (mERVOnRefreshListener != null) {
                mERVOnRefreshListener.onRefresh();
            }
        }
    }


    /**
     * @param mERVOnLoadMoreClickListener ERVOnLoadMoreClickListener
     */
    public void setOnLoadMoreClickListener(ERVOnLoadMoreClickListener mERVOnLoadMoreClickListener) {
        this.mERVOnLoadMoreClickListener = mERVOnLoadMoreClickListener;
    }

    /**
     * @param ERVOnRefreshListener ERVOnRefreshListener
     */
    public void setOnRefreshListener(ERVOnRefreshListener ERVOnRefreshListener) {
        this.mERVOnRefreshListener = ERVOnRefreshListener;
    }

    /**
     * @param strLoadingText text to be shown while loading
     */
    public void setStrLoadingText(String strLoadingText) {
        this.strLoadingText = strLoadingText;
    }

    /**
     * @param strNodataMsg message to be shown if {@link RecyclerView}  contents are empty
     */
    public void setStrNodataMsg(String strNodataMsg) {
        this.strNodataMsg = strNodataMsg;
        if (strNodataMsg != null) {
            cu_id_tv_msg.setText(strNodataMsg);
        }
    }

    /**
     * @param strRetryText Text to be shown to EmptyView's Label
     */
    public void setStrRetryText(String strRetryText) {
        this.strRetryText = strRetryText;
        if (strRetryText != null) {
            cu_id_tv_retry.setText(strRetryText);
        }
    }

    /**
     * @param retryIcon ImameResource to be set to EmptyView
     */
    public void setRetryIcon(int retryIcon) {
        this.retryIcon = retryIcon;
    }

    /**
     * @param itemDecoration ItemDecoration
     */
    public void setItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        this.itemDecoration = itemDecoration;
    }

    /**
     * @param itemAnimator ItemAnimator
     */
    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
    }

    public void setSetDefaultItemAnimator(boolean setDefaultItemAnimator) {
        this.setDefaultItemAnimator = setDefaultItemAnimator;
    }

    public void setSetDefaultItemDecoration(boolean setDefaultItemDecoration) {
        this.setDefaultItemDecoration = setDefaultItemDecoration;
    }

    public RecyclerView getCu_id_rv_list() {
        return cu_id_rv_list;
    }
}
