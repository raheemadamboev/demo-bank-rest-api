package xyz.teamgravity.bankrestapi.gravity.datasource.mock

import org.springframework.stereotype.Repository
import xyz.teamgravity.bankrestapi.gravity.datasource.BankDataSource
import xyz.teamgravity.bankrestapi.gravity.model.BankDto

@Repository
class MockBankDataSource : BankDataSource {

    val banks = listOf(
        BankDto("abc", 0.1, 1.0),
        BankDto("Raheem", 0.1, 0.0),
        BankDto("Yeah", 0.1, 1.0),
    )

    override fun retrieveBanks(): Collection<BankDto> = banks
}