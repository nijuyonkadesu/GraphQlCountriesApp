package com.plcoding.graphqlcountriesapp.domain

interface CountriesClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}
// This is independent on how we get the countries
// Be it REST or Apollo, it doesn't matter to this interface file