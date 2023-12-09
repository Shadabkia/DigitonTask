package com.digitoon.task.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.digitoon.task.data.network.dto.response.Rating

@Entity(tableName = "movie")
data class MovieLocal(
    @PrimaryKey(autoGenerate = false)
    val id : Long = 0,
    @ColumnInfo("title")
    var title: String,
//    @ColumnInfo("Actors")
//    val actors: String,
//    @ColumnInfo("Awards")
//    val awards: String,
//    @ColumnInfo("BoxOffice")
//    val boxOffice: String,
//    @ColumnInfo("Country")
//    val country: String,
//    @ColumnInfo("DVD")
//    val dVD: String,
//    @ColumnInfo("Director")
//    val director: String,
//    @ColumnInfo("Genre")
//    val genre: String,
    @ColumnInfo("imdbID")
    val imdbID: String,
//    @ColumnInfo("imdbRating")
//    val imdbRating: String,
//    @ColumnInfo("imdbVotes")
//    val imdbVotes: String,
//    @ColumnInfo("Language")
//    val language: String,
//    @ColumnInfo("Metascore")
//    val metascore: String,
//    @ColumnInfo("Plot")
//    val plot: String,
    @ColumnInfo("Poster")
    val poster: String,
//    @ColumnInfo("Production")
//    val production: String,
//    @ColumnInfo("Rated")
//    val rated: String,
//    @ColumnInfo("Ratings")
//    val ratings: List<Rating>,
//    @ColumnInfo("Released")
//    val released: String,
//    @ColumnInfo("Response")
//    val response: String,
//    @ColumnInfo("Runtime")
//    val runtime: String,
    @ColumnInfo("Type")
    val type: String,
//    @ColumnInfo("Website")
//    val website: String,
//    @ColumnInfo("Writer")
//    val writer: String,
    @ColumnInfo("Year")
    val year: String
)
