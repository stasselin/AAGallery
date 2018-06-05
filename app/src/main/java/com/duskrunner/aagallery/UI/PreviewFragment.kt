package com.duskrunner.aagallery.UI

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.duskrunner.aagallery.R
import com.duskrunner.aagallery.model.Collection
import com.squareup.picasso.Picasso

class PreviewFragment: Fragment() {
    private var collection: Collection? = null
    val ARG_COLLECTION_ID = "arg:collection_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val collectionId = arguments!!.getInt(ARG_COLLECTION_ID)
        collection = CollectionsRepository.instance.getById(collectionId)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.preview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = PreviewAdapter(Picasso.get(), collection!!.previewPhotos)
    }


    fun newInstance(collectionId: Int): PreviewFragment {
        val fragment = PreviewFragment()
        val bundle = Bundle()
        bundle.putInt(ARG_COLLECTION_ID, collectionId)
        fragment.arguments = bundle
        return fragment
    }
}