package com.plcoding.graphqlcountriesapp.domain

data class DetailedCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val languages: List<String>,
    val continent: String,
)
// The reason for creating a class even inspite of having apollo generate class for us is,
// If someday, you decide to swap REST with apollo,
// there is no need to change random lines in the whole project, instead,
// only changes ApolloCountryClient to RetrofitClient is necessary.