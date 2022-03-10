package com.example.moviesapp.adapters.tvShowsAdapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviesapp.models.showsModels.ShowsResultModel
import com.example.moviesapp.repository.TvShowsRepository

class TvShowsDataSource(
    private val tvShowsRepository: TvShowsRepository,
    private val showType: String? = null,
    private val tvId: Int? = null
) :
    PagingSource<Int, ShowsResultModel>() {

    override fun getRefreshKey(state: PagingState<Int, ShowsResultModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowsResultModel> {
        return try {

            val currentPage = params.key ?: 1
            val response = if (showType != null) {
                tvShowsRepository.getShows(showType = showType, currentPage)
            } else {
                tvShowsRepository.getSimilarShows(tvId = tvId, currentPage)
            }
            val responseData = mutableListOf<ShowsResultModel>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}