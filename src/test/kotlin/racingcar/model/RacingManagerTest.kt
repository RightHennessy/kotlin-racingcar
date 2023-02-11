package racingcar.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import racingcar.entity.Name

class RacingManagerTest {
    @Test
    fun `0개의 자동차로 초기화 시 예외 테스트`() {
        val racingManager = RacingManager()

        assertThrows<IllegalArgumentException> {
            racingManager.initCars(listOf())
        }
    }

    @Test
    fun `시도횟수 0으로 설정 시 예외 테스트`() {
        val racingManager = RacingManager()

        assertThrows<IllegalArgumentException> {
            racingManager.setAttemptCount(0)
        }
    }

    @Test
    fun `잘못된 숫자 입력 시 전진 예외 테스트`() {
        val racingManager = RacingManager()
        racingManager.initCars(listOf(Name("test1"), Name("test2")))

        assertThrows<IllegalArgumentException> {
            racingManager.step(0, -1)
            racingManager.step(1, 10)
        }
    }

    @Test
    fun `racing 1회 시도 테스트`() {
        val racingManager = RacingManager()
        racingManager.initCars(listOf(Name("test1"), Name("test2")))

        racingManager.step(0, 2)
        racingManager.step(1, 7)

        assertThat(racingManager.run()).isEqualTo("test1 : \ntest2 : -")
    }

    @Test
    fun `racing 전체 테스트`() {
        val racingManager = RacingManager()
        racingManager.initCars(listOf(Name("test1"), Name("test2")))

        val result = mutableListOf<String>()

        racingManager.step(0, 2)
        racingManager.step(1, 7)

        result.add(racingManager.run())

        racingManager.step(0, 9)
        racingManager.step(1, 5)

        result.add(racingManager.run())

        assertThat(racingManager.makeRunLog(result)).isEqualTo("test1 : \ntest2 : -\n\ntest1 : -\ntest2 : --")
    }
}
