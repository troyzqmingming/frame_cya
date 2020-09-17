package com.cya.application.ft_home.main.dynamic

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.cya.application.ft_home.api.DynamicAPI
import com.cya.application.ft_home.main.dynamic.entity.DynamicItem
import com.cya.frame.base.vm.BaseRepository

class DynamicRepo(private val api: DynamicAPI) : BaseRepository() {

    /**
     * pager 设置分页属性
     */
    fun getList() =
        /**
         *pageSize :  每一页的数据量
        prefetchDistance :   预取数据的距离，也就是距离最后一个item多远时开始加载下一页数据，
        默认是一页的数据量ppagesize，也就是说你获取到N页数据之后会自动开始获取第N+1页的数据，如果设置为0那么loadmore的效果就会消失
        initialLoadSize : 初始化加载的数量，默认为pagesize * 3
         */
        Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 5),
            pagingSourceFactory = { DynamicDataSource(api) }
        ).flow
}

/**
 *
 * @Package:        com.cya.application.ft_home.main.dynamic
 * @Description:     数据源
 * @Author:         CYA
 * @CreateDate:     2020/9/17 10:03 AM
 */
class DynamicDataSource(private val api: DynamicAPI) : PagingSource<Int, DynamicItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DynamicItem> {
        val index = params.key ?: 0
        //加载数据
        return try {
            val data = api.getDynamicList(index).data
            // 如果成功加载，那么返回一个LoadResult.Page,如果失败就返回一个Error
            // Page里传进列表数据，以及上一页和下一页的页数,具体的是否最后一页或者其他逻辑就自行判断
            // 需要注意的是，如果是第一页，prevKey就传null，如果是最后一页那么nextKey也传null
            // 其他情况prevKey就是page-1，nextKey就是page+1
            LoadResult.Page(
                data = data!!.list,
                prevKey = if (index == 0) null else index - 1,
                nextKey = if (data.isLastPage()) null else index + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

}