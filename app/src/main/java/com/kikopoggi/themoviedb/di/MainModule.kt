package com.kikopoggi.themoviedb.di

import com.kikopoggi.themoviedb.TheMovieDbApplication_HiltComponents
import com.kikopoggi.themoviedb.api.MoviesApi
import com.kikopoggi.themoviedb.repository.MovieRepository
import com.kikopoggi.themoviedb.repository.MovieRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainModule {


    @Provides
    @ActivityRetainedScoped
    fun provideMovieRepository(moviesApi: MoviesApi) = MovieRepository(moviesApi) as MovieRepositoryInterface

}