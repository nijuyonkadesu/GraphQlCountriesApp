package com.plcoding.graphqlcountriesapp.data

import com.apollographql.apollo3.ApolloClient
import com.plcoding.CountriesQuery
import com.plcoding.CountryQuery
import com.plcoding.graphqlcountriesapp.domain.CountriesClient
import com.plcoding.graphqlcountriesapp.domain.DetailedCountry
import com.plcoding.graphqlcountriesapp.domain.SimpleCountry

class ApolloCountryClient(
    val apolloClient: ApolloClient
): CountriesClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}