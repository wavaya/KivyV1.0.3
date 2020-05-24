package xyz.wayhua.kivyv103.data.repository

import com.android.kudago_kotlin.model.data.storage.Prefs
import xyz.wayhua.kivyv103.data.domain.Event
import xyz.wayhua.kivyv103.data.repository.remote.IService

import xyz.wayhua.xframework.mvvm.repository.IPagingRepository


class EventRepository(
    private val api: IService,
    private val prefs: Prefs
) : IPagingRepository<String, Event> {
    override suspend fun getEvents(page: String, size: Int) =
        api.getEvents(
            LIST_FIELDS_TO_RETRIEVE,
            size,
            page,
            TextFormat.TEXT.toString().toLowerCase(),
            EXPANDED_FIELDS,
            prefs.searchCitySlug
        ).toDomainModel()


    enum class TextFormat {
        HTML, PLAIN, TEXT
    }

}

private const val LIST_FIELDS_TO_RETRIEVE = "id,dates,title,place,price,description,images"
private const val DETAILS_FIELDS_TO_RETRIEVE = "id,dates,title,place,price,body_text,images"
private const val EXPANDED_FIELDS = "place"
private const val CONTENT_LANGUAGE_RU = "ru"
private const val CONTENT_LANGUAGE_EN = "en"
