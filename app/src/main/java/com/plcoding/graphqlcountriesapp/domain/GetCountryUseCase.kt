package com.plcoding.graphqlcountriesapp.domain

class GetCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String): DetailedCountry? {
        return countryClient
            .getCountry(code)
    }
}
// It is not wrong if you directly access countryClient in viewmodel.
// UseCases only makes sense with large project, which is not true in our case
// Clean Architecture bro - Single Responsibility Principle