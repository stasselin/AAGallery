package com.duskrunner.aagallery.UI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duskrunner.aagallery.R
import com.duskrunner.aagallery.network.UnsplashApi
import com.duskrunner.aagallery.secret.Keys
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList


class PagerFragment: Fragment() {

    private val apiKey = Keys().apiKey
    private val api: UnsplashApi
    lateinit var viewPager: ViewPager

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit.create(UnsplashApi::class.java)
    }


    fun newInstance(text: String): PagerFragment {
        val pagerFragment = PagerFragment()
        val bundle = Bundle()
        pagerFragment.arguments = bundle
        return pagerFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = view.findViewById(R.id.fragment_main_view_pager)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getApiCuratedCollections()
    }

    fun getApiCuratedCollections() {
        launch(CommonPool) {
            val response = api.getCuratedCollections(apiKey).execute().body()
            Log.i("apiKey", "$apiKey")
            Log.i("resopnce", "$response")
            response?.let {
                for(collection in it) {
                    CollectionsRepository.instance.save(collection)
                }
                kotlinx.coroutines.experimental.run(UI) {
                    val collections = CollectionsRepository.instance.getAll()
                    val fragments = ArrayList<ItemFragment>()
                    for (collection in collections) {
                        fragments.add(ItemFragment().newInstance(collection.id))
                    }
                    val adapter = FragmentStatePagerAdapter(childFragmentManager, fragments)
                    viewPager.adapter = adapter
                }

            }
        }

    }

    fun newInstance(): PagerFragment {
        return PagerFragment()
    }
}