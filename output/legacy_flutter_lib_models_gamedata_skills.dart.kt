import androidx.compose.runtime.Immutable

@Immutable
data class CommanderSkill(
    val logicTrigger: LogicTrigger,
    val canBeLearned: Boolean,
    val isEpic: Boolean,
    val modifiers: Modifiers,
    val skillType: Int,
    val uiTreatAsTrigger: Boolean,
    val name: String,
    val description: String
) {
    val partialDescription: String = "$modifiers$logictrigger"
    val skillDescription: String? = Localisation.instance.stringOf(description)
    val fullDescriptions: String = listOfNotNull(skillDescription, partialDescription).joinToString("\n").trim()
}

@Immutable
data class LogicTrigger(
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
        val triggerTypeDescription =
            Localisation.instance.stringOf(triggerType.uppercase(), prefix = "IDS_SKILL_TRIGGER_")

        return listOfNotNull(triggerDescription, triggerTypeDescription).joinToString("\n")
    }
}