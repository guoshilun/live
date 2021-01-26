package com.newer.library.adapter.base.provider

import com.newer.library.adapter.base.BaseNodeAdapter
import com.newer.library.adapter.base.entity.node.BaseNode

abstract class BaseNodeProvider : BaseItemProvider<BaseNode>() {

    override fun getAdapter(): BaseNodeAdapter? {
        return super.getAdapter() as? BaseNodeAdapter
    }

}