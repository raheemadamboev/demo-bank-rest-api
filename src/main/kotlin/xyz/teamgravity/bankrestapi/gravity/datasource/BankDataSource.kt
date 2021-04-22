package xyz.teamgravity.bankrestapi.gravity.datasource

import xyz.teamgravity.bankrestapi.gravity.model.BankDto

interface BankDataSource {

    fun retrieveBanks(): Collection<BankDto>
}