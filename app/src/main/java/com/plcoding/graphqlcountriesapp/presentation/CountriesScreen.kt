package com.plcoding.graphqlcountriesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.plcoding.graphqlcountriesapp.domain.DetailedCountry
import com.plcoding.graphqlcountriesapp.domain.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountryState,
    searchState: CountriesViewModel.SearchState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit,
    onSearchCountry: (searchKey: String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    searchState = searchState,
                    onValueChange = onSearchCountry
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(state.countries) { country ->
                        CountryItem(
                            country = country,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelectCountry(country.code) }
                                .padding(16.dp)
                        )
                    }
                }
            }
            if (state.selectedCountry != null){
                CountryDialog(
                    country = state.selectedCountry,
                    onDismiss = onDismissCountryDialog,
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(16.dp)
                )
            }
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchState: CountriesViewModel.SearchState,
    onValueChange: (searchKey: String) -> Unit,
) {
//    Row(
//        modifier = modifier
//    ){
//        Icon(Icons.Outlined.Search, null)
//        Spacer(modifier = modifier.width(16.dp))
        OutlinedTextField(
            modifier = modifier,
            value = searchState.searchKey ?: "",
            placeholder = { Text(text = "Search") },
            onValueChange = onValueChange,
        )
//    }
}

@Composable
fun CountryDialog(
    country: DetailedCountry,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = country.emoji, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = country.name, fontSize = 24.sp)
            }
            Text(text = "Continent: " + country.continent)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Currency: " + country.currency)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Capital: " + country.capital)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Language(s): " + country.languages.joinToString())
        }
    }
}

@Composable
private fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = country.emoji,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = country.name,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital)
        }
    }
}