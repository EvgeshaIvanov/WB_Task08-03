package com.example.favoritecats.data.room

import com.example.favoritecats.data.network.FavouriteCatData
import com.example.favoritecats.data.room.entities.FavouriteCatDataDb

class RoomCatsRepository(private val catsDao: CatsDao) {

    fun getAllCats(): List<FavouriteCatData> {
        return catsDao.getFavouriteListOfCatsDatabase().map { cats -> cats.toCat() }
    }

    fun deleteCatFromDb(uid: Long) {
        catsDao.deleteCatFromList(FavouriteCatDataDb(uid))
    }

    fun insertAll(favCats: List<FavouriteCatData>) {
        catsDao.insertAll(favCats.map { cat -> cat.toCatDb() })
    }

}