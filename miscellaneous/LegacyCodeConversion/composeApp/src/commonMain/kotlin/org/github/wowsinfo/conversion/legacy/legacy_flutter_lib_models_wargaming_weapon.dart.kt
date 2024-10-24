
sealed interface Weapon : Parcelable {
    val maxFragsBattle: Int?
    val frags: Int?
    val hits: Int?
    val maxFragsShipId: Int?
    val shots: Int?

    class MainBattery(
        override val maxFragsBattle: Int?,
        override val frags: Int?,
        override val hits: Int?,
        override val maxFragsShipId: Int?,
        override val shots: Int?
    ) : Weapon {
        companion object {
            @JvmField
            val CREATOR = parcelableCreator { MainBattery(it) }
        }

        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        )

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(maxFragsBattle ?: 0)
            dest.writeInt(frags ?: 0)
            dest.writeInt(hits ?: 0)
            dest.writeInt(maxFragsShipId ?: 0)
            dest.writeInt(shots ?: 0)
        }

        override fun describeContents(): Int = 0
    }

    class SecondaryBattery(
        override val maxFragsBattle: Int?,
        override val frags: Int?,
        override val hits: Int?,
        override val maxFragsShipId: Int?,
        override val shots: Int?
    ) : Weapon {
        companion object {
            @JvmField
            val CREATOR = parcelableCreator { SecondaryBattery(it) }
        }

        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        )

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(maxFragsBattle ?: 0)
            dest.writeInt(frags ?: 0)
            dest.writeInt(hits ?: 0)
            dest.writeInt(maxFragsShipId ?: 0)
            dest.writeInt(shots ?: 0)
        }

        override fun describeContents(): Int = 0
    }

    class RamAttack(
        override val maxFragsBattle: Int?,
        override val frags: Int?,
        override val hits: Int?,
        override val maxFragsShipId: Int?,
        override val shots: Int?
    ) : Weapon {
        companion object {
            @JvmField
            val CREATOR = parcelableCreator { RamAttack(it) }
        }

        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        )

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(maxFragsBattle ?: 0)
            dest.writeInt(frags ?: 0)
            dest.writeInt(hits ?: 0)
            dest.writeInt(maxFragsShipId ?: 0)
            dest.writeInt(shots ?: 0)
        }

        override fun describeContents(): Int = 0
    }

    class Torpedoe(
        override val maxFragsBattle: Int?,
        override val frags: Int?,
        override val hits: Int?,
        override val maxFragsShipId: Int?,
        override val shots: Int?
    ) : Weapon {
        companion object {
            @JvmField
            val CREATOR = parcelableCreator { Torpedoe(it) }
        }

        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        )

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(maxFragsBattle ?: 0)
            dest.writeInt(frags ?: 0)
            dest.writeInt(hits ?: 0)
            dest.writeInt(maxFragsShipId ?: 0)
            dest.writeInt(shots ?: 0)
        }

        override fun describeContents(): Int = 0
    }

    class AttackAircraft(
        override val maxFragsBattle: Int?,
        override val frags: Int?,
        override val hits: Int?,
        override val maxFragsShipId: Int?,
        override val shots: Int?
    ) : Weapon {
        companion object {
            @JvmField
            val CREATOR = parcelableCreator { AttackAircraft(it) }
        }

        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()
        )

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeInt(maxFragsBattle ?: 0)
            dest.writeInt(frags ?: 0)
            dest.writeInt(hits ?: 0)
            dest.writeInt(maxFragsShipId ?: 0)
            dest.writeInt(shots ?: 0)
        }

        override fun describeContents(): Int = 0
    }
}