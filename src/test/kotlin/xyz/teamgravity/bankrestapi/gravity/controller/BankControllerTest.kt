package xyz.teamgravity.bankrestapi.gravity.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.*
import xyz.teamgravity.bankrestapi.gravity.model.BankDto

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/banks"

    /*
    // spring boot dependency injection
    @Autowired
    // allows to make requests without http request (faster)
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper
     */

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks`() {
            // when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") {
                        value("abc")
                    }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = "Raheem"

            // when/then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value(0.1) }
                    jsonPath("$.transactionFee") { value(0.0) }
                }
        }

        @Test
        fun `should return NOT FOUND if account number does not exist`() {
            // given
            val accountNumber = "doesNotExist"

            // when/then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should add new bank`() {
            // given
            val newBank = BankDto("ac123", 12.14, 2.0)

            // when/then
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }.andDo {
                print()
            }.andExpect {
                status { isCreated() }
                content { MediaType.APPLICATION_JSON }
                jsonPath("$.accountNumber") { value("ac123") }
                jsonPath("$.trust") { value(12.14) }
                jsonPath("$.transactionFee") { value(2.0) }
            }
        }


        @Test
        fun `should return BAD REQUEST if bank with given account number already exists`() {
            // given
            val invalidBank = BankDto("Raheem", 15.0, 15.0)

            // when/then
            mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }.andDo {
                print()
            }.andExpect {
                status { isBadRequest() }
            }
        }
    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingBank {

        @Test
        fun `should update an existing bank`() {
            // given
            val updateBank = BankDto("Raheem", 10.0, 0.0)

            // when/then
            mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBank)
            }.andDo {
                print()
            }.andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(objectMapper.writeValueAsString(updateBank))
                }
            }

            mockMvc.get("$baseUrl/${updateBank.accountNumber}")
                .andExpect {
                    content { json(objectMapper.writeValueAsString(updateBank)) }
                }
        }

        @Test
        fun `should return BAD REQUEST if no bank with given account number`() {
            // given
            val invalidBank = BankDto("Raheema", 10.0, 0.0)

            // when/then
            mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }.andDo {
                print()
            }.andExpect {
                status { isNotFound() }
            }
        }
    }

    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingBank {

        @Test
        @DirtiesContext
        fun `should delete the bank with the given account number`() {
            // given
            val deleteAccountNumber = "Raheem"

            // when/then
            mockMvc.delete("$baseUrl/$deleteAccountNumber")
                .andDo {
                    print()
                }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$baseUrl/$deleteAccountNumber")
                .andDo {
                    print()
                }
                .andExpect {
                    status { isNotFound() }
                }
        }

        @Test
        fun `should return NOT_FOUND if there is no bank with given account number`() {
            // given
            val invalidAccountNumber = "Raheema"

            // when/then
            mockMvc.delete("$baseUrl/$invalidAccountNumber")
                .andDo {
                    print()
                }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}