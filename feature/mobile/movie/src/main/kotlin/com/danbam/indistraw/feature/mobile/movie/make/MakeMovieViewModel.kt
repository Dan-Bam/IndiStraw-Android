package com.danbam.indistraw.feature.mobile.movie.make

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danbam.indistraw.core.ui.handling.errorHandling
import com.danbam.indistraw.core.domain.entity.movie.MoviePeopleEntity
import com.danbam.indistraw.core.domain.param.movie.MovieCreateParam
import com.danbam.indistraw.core.domain.param.movie.MoviePeopleParam
import com.danbam.indistraw.core.domain.usecase.account.EnrollMoviePeopleUseCase
import com.danbam.indistraw.core.domain.usecase.file.SendFileUseCase
import com.danbam.indistraw.core.domain.usecase.movie.AddMoviePeopleUseCase
import com.danbam.indistraw.core.domain.usecase.movie.MovieCreateUseCase
import com.danbam.indistraw.core.domain.usecase.movie.SearchMoviePeopleUseCase
import com.danbam.indistraw.feature.mobile.navigation.movie.PeopleType
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
    private val movieCreateUseCase: MovieCreateUseCase,
    private val enrollMoviePeopleUseCase: EnrollMoviePeopleUseCase
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

    fun searchMoviePeople(peopleType: String, name: String) = intent {
        viewModelScope.launch {
            searchMoviePeopleUseCase(
                peopleType = peopleType,
                name = name
            ).onSuccess {
                reduce { state.copy(searchMoviePeopleList = it) }
            }
        }
    }

    fun selectMoviePeople(peopleType: String, moviePeople: MoviePeopleEntity, isEnroll: Boolean) =
        intent {
            if (isEnroll) {
                enrollMoviePeople(peopleType = peopleType, actorIdx = moviePeople.actorIdx)
            } else {
                if (peopleType == PeopleType.ACTOR.route) {
                    reduce {
                        state.copy(actorList = state.actorList.plus(moviePeople))
                    }
                } else reduce {
                    state.copy(directorList = state.directorList.plus(moviePeople))
                }
                postSideEffect(MakeMovieSideEffect.Next)
            }
        }

    fun addMoviePeople(peopleType: String, name: String, profileUrl: String?, isEnroll: Boolean) =
        intent {
            if (name.isEmpty()) return@intent
            else if (profileUrl.isNullOrBlank()) return@intent
            else {
                viewModelScope.launch {
                    addMoviePeopleUseCase(
                        peopleType = peopleType,
                        moviePeopleParam = MoviePeopleParam(name = name, profileUrl = profileUrl)
                    ).onSuccess {
                        if (isEnroll) {
                            enrollMoviePeople(peopleType = peopleType, actorIdx = it)
                        } else {
                            if (peopleType == PeopleType.ACTOR.route) {
                                reduce {
                                    state.copy(
                                        actorList = state.actorList.plus(
                                            MoviePeopleEntity(
                                                actorIdx = it,
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
                                            actorIdx = it,
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
        }

    fun removeMoviePeople(peopleType: PeopleType, index: Int) = intent {
        if (peopleType == PeopleType.ACTOR) {
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
                        director = state.directorList.map { it.actorIdx },
                        actor = state.actorList.map { it.actorIdx },
                        isMakeFunding = state.isFunding
                    )
                ).onSuccess {
                    postSideEffect(MakeMovieSideEffect.SuccessCreateMovie)
                }
            }
        }
    }

    fun enrollMoviePeople(peopleType: String, actorIdx: Long) = intent {
        viewModelScope.launch {
            enrollMoviePeopleUseCase(peopleType = peopleType, actorIdx = actorIdx).onSuccess {
                postSideEffect(MakeMovieSideEffect.SuccessEnroll)
            }
        }
    }
}