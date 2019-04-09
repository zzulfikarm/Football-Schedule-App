package com.id.zul.submission3kade.presenter.match

import com.google.gson.Gson
import com.id.zul.submission3kade.api.Get
import com.id.zul.submission3kade.api.Request
import com.id.zul.submission3kade.coroutine.ProviderContext
import com.id.zul.submission3kade.model.match.SearchMatch
import com.id.zul.submission3kade.view.match.SearchMatchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter(
    private val view: SearchMatchView,
    private val apiRepository: Request,
    private val gson: Gson,
    private val context: ProviderContext = ProviderContext()
) {

    fun getNextMatch(query: String) {
        view.setLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.getRequestAsync(Get.getSearchMatch(query)).await(),
                SearchMatch::class.java
            )
            view.setInItData(data.match)
            view.unSetLoading()
        }
    }

}