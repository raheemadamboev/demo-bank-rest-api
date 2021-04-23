package xyz.teamgravity.bankrestapi.gravity.datasource.mock

import org.springframework.stereotype.Repository
import xyz.teamgravity.bankrestapi.gravity.datasource.BankDataSource
import xyz.teamgravity.bankrestapi.gravity.model.BankDto

@Repository
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        BankDto("abc", 0.1, 1.0),
        BankDto("Raheem", 0.1, 0.0),
        BankDto("Yeah", 0.1, 1.0),
    )

    override fun retrieveBanks(): Collection<BankDto> = banks

    override fun retrieveBank(accountNumber: String) =
        banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not found the bank with given account number")

    override fun createBank(bank: BankDto): BankDto {
        if (banks.any { it.accountNumber == bank.accountNumber })
            throw IllegalArgumentException("Bank with ${bank.accountNumber} already exists")
        banks.add(bank)
        return bank
    }
}