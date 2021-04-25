package mobile.solareye.superdemo.data

object MockDataSource {

    val operations: List<Operation> =
        listOf(
            Operation(
                "СберПрайм",
                "Платёж 9 июля",
                "199 ₽ в месяц",
                OperationType.PRIME
            ),
            Operation(
                "Переводы",
                "Автопродление 21 августа",
                "199 ₽ в месяц",
                OperationType.TRANSACTION
            ),
            Operation(
                "СберПрайм",
                "Платёж 9 мая",
                "Оформление подписки",
                OperationType.PRIME
            ),
            Operation(
                "Перевод",
                "Перевод маме 12 августа",
                "Description",
                OperationType.TRANSACTION
            ),
        )

    val sampleTags = listOf(
        "Python", "Git", "Security", "AndroidStudio", "VisualStudioCode", "C++",
        "AndroidArchitectureComponents", "SomeVeryVeeeeeeeeeeeLoooooooooooooooooooooongTag",
        "RxJs", "Kotlin", "Firebase", "TypeScript"
    )

    val kindsOfActivity: List<KindsOfActivityModel> =
        listOf(getItActivities(), getNotItActivities(), getItActivities(), getNotItActivities())

    private fun getItActivities() =
        KindsOfActivityModel(
            KindsOfActivityParentModel(id = 0, name = "IT"),
            mutableListOf<KindsOfActivityChildrenModel>().apply {
                for (i in 0 until 5) {
                    add(KindsOfActivityChildrenModel(id = i, name = "Программист $i"))
                }
            }
        )

    private fun getNotItActivities() =
        KindsOfActivityModel(
            KindsOfActivityParentModel(id = 1, name = "Не IT"),
            mutableListOf<KindsOfActivityChildrenModel>().apply {
                for (i in 5 until 10) {
                    add(KindsOfActivityChildrenModel(id = i, name = "Пчеловод $i"))
                }
            }
        )

}