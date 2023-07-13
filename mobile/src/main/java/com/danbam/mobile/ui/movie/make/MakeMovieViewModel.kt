package com.danbam.mobile.ui.movie.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.domain.entity.MoviePeopleEntity
import com.danbam.domain.param.MovieCreateParam
import com.danbam.domain.param.MoviePeopleParam
import com.danbam.domain.usecase.file.SendFileUseCase
import com.danbam.domain.usecase.movie.AddMoviePeopleUseCase
import com.danbam.domain.usecase.movie.MovieCreateUseCase
import com.danbam.domain.usecase.movie.SearchMoviePeopleUseCase
import com.danbam.mobile.ui.movie.navigation.ActorType
import com.danbam.mobile.util.android.errorHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MakeMovieViewModel @Inject constructor(
    private val sendFileUseCase: SendFileUseCase,
    private val searchMoviePeopleUseCase: SearchMoviePeopleUseCase,
    private val addMoviePeopleUseCase: AddMoviePeopleUseCase,
    private val movieCreateUseCase: MovieCreateUseCase
) : ContainerHost<MakeMovieState, MakeMovieSideEffect>, ViewModel() {
    override val container = container<MakeMovieState, MakeMovieSideEffect>(MakeMovieState())

    fun uploadFile(file: File, onUploaded: (String) -> Unit) = intent {
        viewModelScope.launch {
            sendFileUseCase(file = file).onSuccess {
                onUploaded(it.file)
            }.onFailure {
                it.errorHandling(unknownAction = {})
            }
        }
    }

    fun saveIntroduce(
        thumbnailUrl: String?,
        movieUrl: String?,
        title: String,
        description: String,
        isFunding: Boolean,
        imageList: List<String>,
    ) = intent {
        if (thumbnailUrl.isNullOrBlank()) return@intent
        else if (movieUrl.isNullOrBlank()) return@intent
        else if (title.isEmpty()) return@intent
        else if (description.isEmpty()) return@intent
        else if (imageList.isEmpty()) return@intent
        else {
            reduce {
                state.copy(
                    thumbnailUrl = thumbnailUrl,
                    movieUrl = movieUrl,
                    title = title,
                    description = description,
                    isFunding = isFunding,
                    imageList = imageList
                )
            }
            postSideEffect(MakeMovieSideEffect.Next)
        }
    }

    fun searchMoviePeople(actorType: String, name: String) = intent {
        viewModelScope.launch {
            searchMoviePeopleUseCase(
                actorType = if (actorType == ActorType.ACTOR) "actor" else "director",
                name = name
            ).onSuccess {
                reduce { state.copy(searchMoviePeopleList = it) }
            }
        }
    }

    fun selectMoviePeople(actorType: String, moviePeople: MoviePeopleEntity) = intent {
        if (actorType == ActorType.ACTOR) {
            reduce {
                state.copy(actorList = state.actorList.plus(moviePeople))
            }
        } else reduce {
            state.copy(directorList = state.directorList.plus(moviePeople))
        }
        postSideEffect(MakeMovieSideEffect.Next)
    }

    fun addMoviePeople(actorType: String, name: String, profileUrl: String?) = intent {
        if (name.isEmpty()) return@intent
        else if (profileUrl.isNullOrBlank()) return@intent
        else {
            viewModelScope.launch {
                addMoviePeopleUseCase(
                    actorType = if (actorType == ActorType.ACTOR) "actor" else "director",
                    moviePeopleParam = MoviePeopleParam(name = name, profileUrl = profileUrl)
                ).onSuccess {
                    if (actorType == ActorType.ACTOR) {
                        reduce {
                            state.copy(
                                actorList = state.actorList.plus(
                                    MoviePeopleEntity(
                                        idx = it,
                                        name = name,
                                        profileUrl = profileUrl
                                    )
                                )
                            )
                        }
                    } else reduce {
                        state.copy(
                            directorList = state.directorList.plus(
                                MoviePeopleEntity(
                                    idx = it,
                                    name = name,
                                    profileUrl = profileUrl
                                )
                            )
                        )
                    }
                    postSideEffect(MakeMovieSideEffect.Next)
                }
            }
        }
    }

    fun removeMoviePeople(actorType: String, index: Int) = intent {
        if (actorType == ActorType.ACTOR) {
            reduce {
                state.copy(actorList = state.actorList.filterIndexed { i, _ -> i != index })
            }
        } else reduce {
            state.copy(directorList = state.directorList.filterIndexed { i, _ -> i != index })
        }
    }

    fun movieCreate() = intent {
        if (state.directorList.isEmpty()) return@intent
        else if (state.actorList.isEmpty()) return@intent
        else {
            viewModelScope.launch {
                movieCreateUseCase(
                    movieCreateParam = MovieCreateParam(
                        title = state.title,
                        description = state.description,
                        movieUrl = state.movieUrl!!,
                        thumbnailUrl = state.thumbnailUrl!!,
                        highlight = state.imageList,
                        director = state.directorList.map { it.idx },
                        actor = state.actorList.map { it.idx },
                        isMakeFunding = state.isFunding
                    )
                ).onSuccess {
                    postSideEffect(MakeMovieSideEffect.SuccessCreateMovie)
                }
            }
        }
    }
}