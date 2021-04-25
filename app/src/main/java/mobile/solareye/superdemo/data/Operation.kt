package mobile.solareye.superdemo.data

data class Operation(
    val title: String,
    val subtitle: String,
    val description: String,
    val type: OperationType
)

enum class OperationType { TRANSACTION, PRIME }