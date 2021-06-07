package com.ioinsiders.marvelheroes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioinsiders.marvelheroes.models.Character
import com.ioinsiders.marvelheroes.models.DataStateEvent
import com.ioinsiders.marvelheroes.models.Result
import com.ioinsiders.marvelheroes.repositories.HeroesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(repository: HeroesRepository):ViewModel()  {

    val tabTitles = listOf("Popular", "A-Z", "Last Viewed")

    private val _charactersStateFlow: MutableStateFlow<DataStateEvent<List<Character>>> = MutableStateFlow(DataStateEvent.Initial)
    val charactersStateFlow : StateFlow<DataStateEvent<List<Character>>> = _charactersStateFlow


    init {
        viewModelScope.launch {
            _charactersStateFlow.value = DataStateEvent.Loading
            when( val result =  repository.getCharacters(0, 20)) {
                is Result.Success -> {
                    result.data?.let { _charactersStateFlow.value = DataStateEvent.Success(it) }
                }
                is Result.Error -> {
                    result.message?.let { _charactersStateFlow.value = DataStateEvent.Failure(it) }
                }

            }
        }
    }

    //We will create a sealed class to hold the current filter here
    fun loadMoreCharacters(currentFilters: List<Any>) {

    }

}