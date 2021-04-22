package xyz.teamgravity.bankrestapi.gravity.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import xyz.teamgravity.bankrestapi.gravity.datasource.BankDataSource

internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk(relaxed = true)

    private val bankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        // given
        // every { dataSource.retrieveBanks() } returns emptyList()

        // when
        val banks = bankService.getBanks()

        // then
        verify(exactly = 1) { dataSource.retrieveBanks() }
    }
}