package com.example.favoritecats.data.room

import androidx.room.*
import com.example.favoritecats.data.room.entities.FavouriteCatDataDb

@Dao
interface CatsDao {

    @Query("SELECT * FROM favourite_cats")
    fun getFavouriteListOfCatsDatabase(): List<FavouriteCatDataDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(favCats: List<FavouriteCatDataDb>)

    @Delete
    fun deleteCatFromList(favouriteCatDataDb: FavouriteCatDataDb)

}