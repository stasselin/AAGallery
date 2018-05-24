package com.duskrunner.aagallery.UI

import android.support.v4.app.FragmentPagerAdapter
import com.duskrunner.aagallery.model.Collection
import java.util.ArrayList
import java.util.HashMap

class CollectionsRepository {
        private val storage: MutableMap<Int, Collection>

        fun getAll(): List<Collection> {
            return ArrayList<Collection>(storage.values)
        }

        init {
            this.storage = HashMap<Int, Collection>()
        }

        fun getById(id: Int): Collection? {
            return storage[id]
        }

        fun save(collection: Collection) {
            storage[collection.id] = collection
        }

        companion object {

            val instance = CollectionsRepository()
        }

}