package xyz.wayhua.kivyv103.data.repository.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.wayhua.kivyv103.data.repository.remote.res.EventRes
import xyz.wayhua.kivyv103.data.repository.remote.res.EventsRes

interface IService {
    @GET("events/")
    suspend fun getEvents(
        @Query("fields") fields: String,
        @Query("page_size") pageSize: Int,
        @Query("page") page: String,
        @Query("text_format") textFormat: String,
        @Query("expand") expandedFields: String,
        @Query("location") citySlug: String
    ): EventsRes

    @GET("events/{event_id}/")
    suspend fun getEventDetails(
        @Path("event_id") eventId: Long,
        @Query("lang") language: String,
        @Query("fields") fields: String,
        @Query("expand") expandedFields: String,
        @Query("text_format") textFormat: String
    ): EventRes
}