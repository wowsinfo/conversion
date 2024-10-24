@Serializable
data class WargamingAPIError(
    val field: String? = null,
    val message: String? = null,
    val code: Int? = null,
    val value: String? = null
) {

}

@Serializable
data class WargamingAPIMeta(
    val count: Int? = null,
    val pageTotal: Int? = null,
    val total: Int? = null,
    val limit: Int? = 100,
    val page: Int? = null
) {
    fun requirePagination(): Boolean {
        return pageTotal != null && pageTotal!! > 1
    }

    fun hasMorePages(): Boolean {
        return pageTotal != null && page != null && page!! < pageTotal!!
    }
}