package xyz.wayhua.kivyv103.data.repository.remote.res

import kotlinx.serialization.Serializable
import xyz.wayhua.kivy102.utils.DateUtil
import xyz.wayhua.kivyv103.data.domain.Event
import xyz.wayhua.xframework.mvvm.data.PagingResult


@Serializable
data class SourceRes(
    val name: String,
    val link: String
)

@Serializable
data class ImageRes(
    val image: String,
    val source: SourceRes?
) {

    fun toDomainModel() = Event.Image(
        image
    )
}

@Serializable
data class PlaceRes(
    val id: Long,
    val title: String,
    val slug:String
) {

    fun toDomainModel() =Event.Place(
        id, title
    )
}

@Serializable
data class DateRes(
    val start: Long?,
    val end: Long?
) {

    fun toDomainModel() = Event.Date(
        start, end
    )
}

@Serializable
data class EventRes(
    val id: Long,
    val images: List<ImageRes>?,
    val title: String,
    val description: String,
    val place: PlaceRes?,
    val dates: List<DateRes>?,
    val price: String
) {
    fun toDomainModel() = Event(
        id = id,
        images = images?.map { it.toDomainModel() },
        title = title,
        description = description,
        place = place?.toDomainModel(),
        dates = DateUtil.handleEventDates(dates),
        price = if (price.isEmpty()) null else price
    )
}

@Serializable
data class EventsRes(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<EventRes>
) {
    fun toDomainModel() = PagingResult<String,Event>(
        count = count,
        previousPage = previous,
        nextPage = next,
        results = results.map { it.toDomainModel() }
    )
}
