package xyz.teamgravity.bankrestapi.gravity.datasource.network

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import xyz.teamgravity.bankrestapi.gravity.datasource.BankDataSource
import xyz.teamgravity.bankrestapi.gravity.datasource.network.dto.BankListDto
import xyz.teamgravity.bankrestapi.gravity.model.BankDto
import java.io.IOException

@Repository("network")
class NetworkDataSource(
    @Autowired private val restTemplate: RestTemplate
) : BankDataSource {

    // getting data from network
    override fun retrieveBanks(): Collection<BankDto> {
        val response: ResponseEntity<BankListDto> =
            restTemplate.getForEntity("https://reqres.in/api/users?page=2")

        return response.body?.data
            ?: throw IOException("Could not fetch banks from the network")
    }

    override fun retrieveBank(accountNumber: String): BankDto {
        TODO("Not yet implemented")
    }

    override fun createBank(bank: BankDto): BankDto {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: BankDto): BankDto {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String) {
        TODO("Not yet implemented")
    }
}