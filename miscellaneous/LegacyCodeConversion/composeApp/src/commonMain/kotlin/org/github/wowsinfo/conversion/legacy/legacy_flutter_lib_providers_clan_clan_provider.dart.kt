
class ClanProvider(private val clan: ClanResult) : BaseViewModel() {

    private val _service = WargamingService(
        GameServer.fromIndex(UserRepository.instance.gameServer),
    )
    private val _info = MutableLiveData<ClanInformation?>()
    val info: LiveData<ClanInformation?>
        get() = _info

    private var _loading by mutableStateOf(true)
    val loading: Boolean
        get() = _loading

    init {
        loadClanInfo(clan.clanId.toString())
    }

    fun loadClanInfo(clanId: String) {
        if (clanId.isEmpty()) return

        addDisposable(
            Completable.fromCallable { _service.getClanInformation(clanId) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loading = true }
                .doFinally { _loading = false }
                .subscribe({
                    if (it.data != null) {
                        _info.value = it.data
                    } else {
                        _logger.w("Failed to load clan information")
                    }
                }, {
                    _logger.e("Error loading clan information", it)
                })
        )
    }

    fun getTag(): String {
        return info.value?.tag ?: "-"
    }

    fun getTagDescription(): String {
        return info.value?.name ?: "-"
    }

    fun getCreatedDate(): String {
        val created = info.value?.createdAt
        if (created == null) return "-"
        return DateTimeUtils.dateTimeString(created)
    }

    fun getCreatorName(): String {
        return info.value?.creatorName ?: "-"
    }

    fun getLeaderName(): String {
        return info.value?.leaderName ?: "-"
    }

    fun getDescription(): String? {
        return info.value?.description
    }

    fun getMemberCount(): Int {
        return info.value?.membersCount ?: 0
    }
}