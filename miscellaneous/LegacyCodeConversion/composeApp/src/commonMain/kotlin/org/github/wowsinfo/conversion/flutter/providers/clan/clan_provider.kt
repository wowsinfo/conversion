
class ClanProvider(private val clan: ClanResult) : ViewModel() {
    private val logger = LoggerFactory.getLogger(ClanProvider::class.java)
    private var info: ClanInformation? = null
    var loading = mutableStateOf(true)

    init {
        logger.info("Started loading clan info")
        val clanId = clan.clanId
        if (clanId != null) {
            CoroutineScope(Dispatchers.IO).launch {
                val clanData = WargamingService.getClanInformation(clanId.toString()).data
                withContext(Dispatchers.Main) {
                    if (clanData != null) info = clanData
                    loading.value = false
                }
            }
        } else {
            loading.value = false
        }
    }
}

    private val service = WargamingService(GameServer.fromIndex(UserRepository.instance.gameServer))

    private var info: ClanInformation? = null
    private var loading = true
    val isLoading: Boolean get() = loading

    val tag: String get() = info?.tag ?: "-"
    val tagDescription: String get() = info?.name ?: "-"
    val createdDate: String
        get() {
            val created = info?.createdAt
            return created?.dateTimeString ?: "-"
        }
}

    val creatorName: String
        get() = info?.creatorName ?: "-"

    val leaderName: String
        get() = info?.leaderName ?: "-"

    val description: String?
        get() = info?.description

    val memberCount: Int
        get() = info?.membersCount ?: 0

    val members: Iterable<ClanMember>?
        get() = info?.members?.values
}