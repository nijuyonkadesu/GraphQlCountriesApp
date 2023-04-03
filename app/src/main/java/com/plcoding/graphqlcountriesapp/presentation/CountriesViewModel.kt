package com.plcoding.graphqlcountriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.graphqlcountriesapp.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase,
    private val getCountriesBySearchUseCase: GetCountriesBySearchUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CountryState())
    val state = _state.asStateFlow()

    val searchState = MutableStateFlow(SearchState())

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                countries = getCountriesUseCase.execute(),
                isLoading = false
            ) }
        }
    }

    fun selectCountry(code: String){
        viewModelScope.launch {
            _state.update { it.copy(
                selectedCountry = getCountryUseCase.execute(code)
            ) }
        }
    }

    fun searchCountry(searchKey: String?) {
        viewModelScope.launch {
            searchState.update {
                it.copy(
                    searchKey = searchKey
                )
            }
            _state.update {
                it.copy(
                    countries = getCountriesBySearchUseCase.execute(searchState.value.searchKey)
                )
            }
        }
    }

    fun dismissCountryDialog() {
        _state.update { it.copy(
            selectedCountry = null
        ) }
    }

    data class CountryState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null,
    )
    data class SearchState(
        var searchKey: String? = null,
    )
}