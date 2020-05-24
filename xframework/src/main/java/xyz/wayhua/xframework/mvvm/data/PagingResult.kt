package xyz.wayhua.xframework.mvvm.data

data class PagingResult<ID,T>(
    val count: Int,
    val previousPage: ID?,
    val nextPage: ID?,
    val results: List<T>
)