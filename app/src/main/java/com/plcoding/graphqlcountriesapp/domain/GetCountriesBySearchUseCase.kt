package com.plcoding.graphqlcountriesapp.domain

class GetCountriesBySearchUseCase(
    private val countryClient: CountryClient,
) {
    suspend fun execute(searchKey: String?): List<SimpleCountry> {
        if (searchKey != null) {
            return countryClient
                .getCountries()
                .filter { it.name.trim().lowercase().contains(searchKey.lowercase()) }
        }
        return countryClient
            .getCountries()
    }
}