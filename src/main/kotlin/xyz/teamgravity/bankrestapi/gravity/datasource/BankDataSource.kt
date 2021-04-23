package xyz.teamgravity.bankrestapi.gravity.datasource

import xyz.teamgravity.bankrestapi.gravity.model.BankDto

interface BankDataSource {

    fun retrieveBanks(): Collection<BankDto>

    fun retrieveBank(accountNumber: String): BankDto

    fun createBank(bank: BankDto): BankDto

    fun updateBank(bank: BankDto): BankDto
}