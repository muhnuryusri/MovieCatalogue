package com.application.moviecatalogue.utils

import com.application.moviecatalogue.data.source.local.entity.*
import com.application.moviecatalogue.data.source.remote.response.*

object DataDummy {
    fun getMovies(): List<DataEntity> {
        return listOf(
            DataEntity(
                567189,
                "Tom Clancy's Without Remorse",
                7.3,
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg"
            ),
            DataEntity(
                804435,
                "Vanquish",
                6.4,
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg"
            ),
            DataEntity(
                460465,
                "Mortal Kombat",
                7.7,
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"
            )
        )
    }

    fun getDetailMovie(): MovieDetailEntity {
        return MovieDetailEntity(
            567189,
            "Tom Clancy's Without Remorse",
            "2021-04-29",
            109,
            7.3,
            listOf("Action", "Adventure", "Thriller", "War"),
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg"
        )
    }

    fun getTvShows(): List<DataEntity> {
        return listOf(
            DataEntity(
                88396,
                "The Falcon and the Winter Soldier",
                7.9,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
            ),
            DataEntity(
                60735,
                "The Flash",
                7.7,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            ),
            DataEntity(
                71712,
                "The Good Doctor",
                8.6,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
            )
        )
    }

    fun getDetailTvShow(): TvShowDetailEntity {
        return TvShowDetailEntity(
            88396,
            "The Falcon and the Winter Soldier",
            "2021-04-23",
            "6",
            7.9,
            listOf("Sci-Fi & Fantasy", "Action & Adventure", "Drama", "War & Politics"),
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg"
        )
    }

    fun getMovieCast(): CastDataEntity {
        return CastDataEntity(
            567189,
            arrayListOf(
                CastEntity(
                    135651,
                    "Michael B. Jordan",
                    "John Kelly",
                    "/kfcn0yyEdN2aJfVaxW0NIoKVF4J.jpg"
                ),
                CastEntity(
                    478,
                    "Jamie Bell",
                    "Robert Ritter",
                    "/xAfNYOJYOy5ee9PHbBF8Y6xzZ9I.jpg"
                ),
                CastEntity(
                    98772,
                    "Lauren London",
                    "Pam Kelly",
                    "/vfUJtwfux3nwEGWIbrC20LyH8fn.jpg"
                )
            )
        )
    }

    fun getTvShowCast(): List<CastEntity> {
        return listOf(
            CastEntity(
                53650,
                "Anthony Mickie",
                "Sam Wilson / Falcon",
                "/eZSIDrtTzhvabyjrmIITQLsjx8h.jpg"
            ),
            CastEntity(
                60898,
                "Sebastian Stan",
                "James 'Bucky' Barnes / Winter Soldier",
                "/nKZgixTbHFXpkzzIpMFdLX98GYh.jpg"
            ),
            CastEntity(
                98772,
                "Lauren London",
                "Pam Kelly",
                "/vfUJtwfux3nwEGWIbrC20LyH8fn.jpg"
            )
        )
    }

    fun getMoviesResponse(): List<MovieResultsItem> {
        return listOf(
            MovieResultsItem(
                id = 567189,
                title = "Tom Clancy's Without Remorse",
                posterPath = "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                voteAverage = 7.3
            ),
            MovieResultsItem(
                id = 804435,
                title = "Vanquish",
                posterPath = "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                voteAverage = 6.4
            ),
            MovieResultsItem(
                id = 460465,
                title =  "Mortal Kombat",
                posterPath = "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                voteAverage =  7.7
            )
        )
    }

    fun getMovieDetailResponse(): MovieDetailResponse {
        return MovieDetailResponse(
            backdropPath = "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
            overview = "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            releaseDate = "2021-04-29",
            genres = listOf(),
            voteAverage = 7.3,
            runtime = 109,
            id = 567189,
            title = "Tom Clancy's Without Remorse",
            posterPath = "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
        )
    }

    fun getTvShowResponse(): List<TvShowResultsItem> {
        return listOf(
            TvShowResultsItem(
                id = 88396,
                title = "The Falcon and the Winter Soldier",
                posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                voteAverage = 7.9
            ),
            TvShowResultsItem(
                id = 60735,
                title = "The Flash",
                posterPath =  "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                voteAverage = 7.7
            ),
            TvShowResultsItem(
                id = 71712,
                title =  "The Good Doctor",
                posterPath = "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                voteAverage =  8.6
            )
        )
    }

    fun getTvShowDetailResponse(): TvShowDetailResponse {
        return TvShowDetailResponse(
            backdropPath = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
            overview = "Following the events of “Avengers: Endgame, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            firsAirDate = "2021-04-23",
            genres = listOf(),
            voteAverage = 7.9,
            numberOfSeasons = "6",
            id =  88396,
            name = "The Falcon and the Winter Soldier",
            posterPath = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg"
        )
    }

    fun getMovieCastResponse(): CastResponse {
        return CastResponse(
            id = 567189,
            cast = arrayListOf(
                CastItem(
                    id = 135651,
                    name = "Michael B. Jordan",
                    character = "John Kelly",
                    profilePath = "/kfcn0yyEdN2aJfVaxW0NIoKVF4J.jpg"
                ),
                CastItem(
                    id = 478,
                    name = "Jamie Bell",
                    character = "Robert Ritter",
                    profilePath = "/xAfNYOJYOy5ee9PHbBF8Y6xzZ9I.jpg"
                ),
                CastItem(
                    id = 98772,
                    name = "Lauren London",
                    character = "Pam Kelly",
                    profilePath = "/vfUJtwfux3nwEGWIbrC20LyH8fn.jpg"
                )
            )
        )
    }

    fun getTvShowCastResponse(): CastResponse {
        return CastResponse(
            id = 88396,
            cast = arrayListOf(
                CastItem(
                    id = 53650,
                    name = "Anthony Mickie",
                    character = "Sam Wilson / Falcon",
                    profilePath = "/eZSIDrtTzhvabyjrmIITQLsjx8h.jpg"
                ),
                CastItem(
                    id = 60898,
                    name = "Sebastian Stan",
                    character = "James 'Bucky' Barnes / Winter Soldier",
                    profilePath = "/nKZgixTbHFXpkzzIpMFdLX98GYh.jpg"
                ),
                CastItem(
                    id = 84247,
                    name = "Emily Vancamp",
                    character = "Sharon Carter",
                    profilePath = "/zTl2KVglr7D7jnBLjbqe1BbbJiV.jpg"
                )
            )
        )
    }
}