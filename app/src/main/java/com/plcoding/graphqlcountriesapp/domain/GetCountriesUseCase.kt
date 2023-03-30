package com.plcoding.graphqlcountriesapp.domain

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}
// It is not wrong if you directly access countryClient in viewmodel.
// UseCases only makes sense with large project, which is not true in our case
// Clean Architecture bro - Single Responsibility Principle