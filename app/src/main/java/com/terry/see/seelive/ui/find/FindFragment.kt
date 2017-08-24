package com.terry.see.seelive.ui.find

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.terry.see.seelive.R
import com.terry.see.seelive.ui.MainActivity
import javax.inject.Inject

/**
 * Created by terry on 2017/8/22.
 */
class FindFragment : Fragment() {

    private var mColumnCount = 1

    lateinit var adapter: FindAdapter

    lateinit var presenterR:PresenterRecommend
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
        }

        (activity as MainActivity).appComponent.recomendFragmentComponent().build().inject(this)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
            } else {
                view.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            adapter =  FindAdapter(activity)
            var itemDecoration = DividerItemDecoration(context,LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(resources.getDrawable(R.drawable.bg_item_decoration))
            view.addItemDecoration(itemDecoration)
            view.adapter =adapter
        }

        presenterR.requestRecommend(adapter)

        return view
    }


    companion object {
        private val ARG_COLUMN_COUNT = "column-count"

        fun newInstance(columnCount: Int): FindFragment {
            val fragment = FindFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}
