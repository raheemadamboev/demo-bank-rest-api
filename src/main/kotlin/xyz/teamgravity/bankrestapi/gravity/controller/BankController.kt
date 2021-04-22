package xyz.teamgravity.bankrestapi.gravity.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.teamgravity.bankrestapi.gravity.model.BankDto
import xyz.teamgravity.bankrestapi.gravity.service.BankService

@RestController
@RequestMapping("api/banks")
class BankController(private val bankService: BankService) {

    @GetMapping
    fun getBanks(): Collection<BankDto> = bankService.getBanks()
}