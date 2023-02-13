package racingcar.entity

data class Car(val name: Name) {
    private var position: Position = Position(0)

    fun forward(number: Int) {
        if (number >= FORWARD_BOUND) {
            position.addPosition(1)
        }
    }

    fun compareTo(car: Car): Boolean {
        return position.compareTo(car.position)
    }

    fun getPosition() = position

    companion object {
        const val FORWARD_BOUND = 4
    }
}
