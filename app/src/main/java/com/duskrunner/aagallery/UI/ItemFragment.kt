package com.duskrunner.aagallery.UI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.duskrunner.aagallery.R
import com.duskrunner.aagallery.model.Collection
import com.squareup.picasso.Picasso

class ItemFragment: Fragment() {

    val ARG_COLLECTION_ID = "arg:collection_id"
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var collectionImageView: ImageView
    private var collection: Collection? = null

    interface ClickListener {
        fun onPreviewCollection(collectionId: Int)
    }

    private var listener: ClickListener? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val collectionId: Int = arguments!!.getInt(ARG_COLLECTION_ID)
        collection = CollectionsRepository.instance.getById(collectionId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.pager_item, container, false)

        titleTextView = view.findViewById(R.id.item_name_text)
        descriptionTextView = view.findViewById(R.id.pager_item_description_text)
        collectionImageView = view.findViewById(R.id.pager_item_imageView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTextView.text = collection?.title
        descriptionTextView.text = collection?.description
        val imageURL = collection?.coverPhoto?.urls?.small
        Picasso.get().load(imageURL).into(collectionImageView)

        collectionImageView.setOnClickListener(View.OnClickListener {
            if (listener != null) {
                listener!!.onPreviewCollection(collection!!.id)
            }
        })
    }


    fun newInstance(collectionId: Int): ItemFragment {
        val fragment = ItemFragment()
        val bundle = Bundle()
        bundle.putInt(ARG_COLLECTION_ID, collectionId)
        fragment.arguments = bundle
        return fragment
    }
}