package xyz.teamgravity.bankrestapi.gravity.model

data class BankDto(

    // @JsonProperty("first_name")
    val accountNumber: String,

    // @JsonProperty("id")
    val trust: Double,

    // @JsonProperty("default_transaction_fee")
    val transactionFee: Double
)