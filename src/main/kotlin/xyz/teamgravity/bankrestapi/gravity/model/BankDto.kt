package xyz.teamgravity.bankrestapi.gravity.model

data class BankDto(
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Double
)