package xyz.teamgravity.bankrestapi.gravity.datasource.network.dto

import com.fasterxml.jackson.annotation.JsonProperty
import xyz.teamgravity.bankrestapi.gravity.model.BankDto

data class BankListDto(
    @JsonProperty("data")
    val data: Collection<BankDto>
)
