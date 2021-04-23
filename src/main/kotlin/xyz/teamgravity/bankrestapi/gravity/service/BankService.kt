package xyz.teamgravity.bankrestapi.gravity.service

import org.springframework.stereotype.Service
import xyz.teamgravity.bankrestapi.gravity.datasource.BankDataSource
import xyz.teamgravity.bankrestapi.gravity.model.BankDto

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks(): Collection<BankDto> = dataSource.retrieveBanks()

    fun getBank(accountNumber: String) = dataSource.retrieveBank(accountNumber)

    fun addBank(bank: BankDto) = dataSource.createBank(bank)

    fun updateBank(bank: BankDto) = dataSource.updateBank(bank)

    fun deleteBank(accountNumber: String) = dataSource.deleteBank(accountNumber)
}