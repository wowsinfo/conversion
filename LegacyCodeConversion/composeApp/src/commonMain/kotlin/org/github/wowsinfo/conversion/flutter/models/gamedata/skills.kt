
@Serializable
data class CommanderSkill(
    val logicTrigger: String,
    val canBeLearned: Boolean,
    val isEpic: Boolean,
    val modifiers: List<Modifier>,
    val skillType: String,
    val uiTreatAsTrigger: Boolean,
    val name: String,
    val description: String
)

    val logicTrigger: LogicTrigger,
    val canBeLearned: Boolean,
    val isEpic: Boolean,
    val modifiers: Modifiers,
    val skillType: Int,
    val uiTreatAsTrigger: Boolean,
    val name: String,
    val description: String
) {
    val partialDescription: String
        get() = "$modifiers$logicTrigger"

    val skillDescription: String?
        get() = Localisation.instance.stringOf(description)

    val fullDescriptions: String
        get() {
            val list = mutableListOf<String?>(
                skillDescription,
                partialDescription
            ).filterNotNull()
            return list.joinToString("\n").trim()
        }
}

    val logicTrigger: LogicTrigger,
    val canBeLearned: Boolean,
    val isEpic: Boolean,
    val modifiers: Modifiers,
    val skillType: String,
    val uiTreatAsTrigger: Boolean,
    val name: String,
    val description: String
) {
    companion object {
        fun fromJson(json: Map<String, Any>): CommanderSkill {
            return CommanderSkill(
                logicTrigger = LogicTrigger.fromJson(json["LogicTrigger"] as Map<String, Any>),
                canBeLearned = json["canBeLearned"] as Boolean,
                isEpic = json["isEpic"] as Boolean,
                modifiers = Modifiers.fromJson(json["modifiers"] as Map<String, Any>),
                skillType = json["skillType"] as String,
                uiTreatAsTrigger = json["uiTreatAsTrigger"] as Boolean,
                name = json["name"] as String,
                description = json["description"] as String
            )
        }
    }
}

data class LogicTrigger(
    val burnCount: Int,
    val changePriorityTargetPenalty: Int,
    val consumableType: String,
    val coolingDelay: Int,
    val coolingInterpolator: String,
    val dividerType: String,
    val dividerValue: Int,
    val duration: Int,
    val energyCoeff: Double,
    val floodCount: Int,
    val heatInterpolator: String,
    val modifiers: Modifiers,
    val triggerDescIds: List<String>,
    val triggerType: String
) {
    companion object {
        fun fromJson(json: Map<String, Any>): LogicTrigger {
            return LogicTrigger(
                burnCount = json["burnCount"] as Int,
                changePriorityTargetPenalty = json["changePriorityTargetPenalty"] as Int,
                consumableType = json["consumableType"] as String,
                coolingDelay = json["coolingDelay"] as Int,
                coolingInterpolator = json["coolingInterpolator"] as String,
                dividerType = json["dividerType"] as String,
                dividerValue = json["dividerValue"] as Int,
                duration = json["duration"] as Int,
                energyCoeff = json["energyCoeff"] as Double,
                floodCount = json["floodCount"] as Int,
                heatInterpolator = json["heatInterpolator"] as String,
                modifiers = Modifiers.fromJson(json["modifiers"] as Map<String, Any>),
                triggerDescIds = json["triggerDescIds"] as List<String>,
                triggerType = json["triggerType"] as String
            )
        }
    }
}

data class Modifiers(
    // Define properties here
) {
    companion object {
        fun fromJson(json: Map<String, Any>): Modifiers {
            // Implement parsing logic here
            return Modifiers()
        }
    }
}

    val burnCount: Double,
    val changePriorityTargetPenalty: Double,
    val consumableType: String,
    val coolingDelay: Double,
    val coolingInterpolator: List<List<Double>>,
    val dividerType: String,
    val dividerValue: Double,
    val duration: Double,
    val energyCoeff: Double,
    val floodCount: Double,
    val heatInterpolator: List<List<Double>>,
    val modifiers: Modifiers,
    val triggerDescIds: String,
    val triggerType: String
) {
    override fun toString(): String {
        val triggerDescription = Localisation.instance.stringOf(triggerDescIds)
        val triggerTypeDescription = Localisation.instance.stringOf(
            triggerType.uppercase(),
            prefix = "IDS_SKILL_TRIGGER_"
        )

        val list = mutableListOf<String?>(
            triggerDescription,
            triggerTypeDescription,
            modifiers.toString()
        ).filterNotNull()
        
        return list.joinToString("\n")
    }
}

    val burnCount: Int,
    val changePriorityTargetPenalty: Int,
    val consumableType: String,
    val coolingDelay: Int,
    val coolingInterpolator: List<List<Number>>,
    val dividerType: String,
    val dividerValue: Int,
    val duration: Int,
    val energyCoeff: Double,
    val floodCount: Int,
    val heatInterpolator: List<List<Number>>,
    val modifiers: Modifiers,
    val triggerDescIds: List<String>,
    val triggerType: String
) {
    companion object {
        fun fromJson(json: Map<String, Any>): LogicTrigger {
            return LogicTrigger(
                burnCount = json["burnCount"] as Int,
                changePriorityTargetPenalty = json["changePriorityTargetPenalty"] as Int,
                consumableType = json["consumableType"] as String,
                coolingDelay = json["coolingDelay"] as Int,
                coolingInterpolator = (json["coolingInterpolator"] as List<List<Any>>).map { it.map { x -> x as Number } },
                dividerType = json["dividerType"] as String,
                dividerValue = json["dividerValue"] as Int,
                duration = json["duration"] as Int,
                energyCoeff = json["energyCoeff"] as Double,
                floodCount = json["floodCount"] as Int,
                heatInterpolator = (json["heatInterpolator"] as List<List<Any>>).map { it.map { x -> x as Number } },
                modifiers = Modifiers.fromJson(json["modifiers"] as Map<String, Any>),
                triggerDescIds = json["triggerDescIds"] as List<String>,
                triggerType = json["triggerType"] as String
            )
        }
    }
}