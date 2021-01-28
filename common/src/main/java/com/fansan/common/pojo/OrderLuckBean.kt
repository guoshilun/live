package com.fansan.common.pojo

data class OrderLuckBean(
    val activityTime: String?,
    val countDown: Int?,
    val luckOrder: String?,
    val luckyGod: LuckyGod?,
    val luckyUser: LuckyUser?,
    val orders: List<Order>?,
    val state: Int?,
    val todayAward: String?
)
data class Order(
    val orderId: String?,
    val type: Int?
)

data class LuckyUser(
    val money: String?,
    val name: String?,
    val uid: String?,
    val url: String?
)

data class LuckyGod(
    val money: String?,
    val name: String?,
    val uid: String?,
    val url: String?
)