package mobile.solareye.superdemo.data

data class KindsOfActivityModel(
    val parent: KindsOfActivityParentModel,
    val children: List<KindsOfActivityChildrenModel>
)

data class KindsOfActivityParentModel(
    val id: Int,
    val name: String,
    var isExpanded: Boolean = true
)

data class KindsOfActivityChildrenModel(
    val id: Int,
    val name: String,
    var isSelected: Boolean = false
)