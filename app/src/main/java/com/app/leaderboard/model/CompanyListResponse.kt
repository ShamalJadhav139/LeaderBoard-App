package com.app.leaderboard.model

import java.io.Serializable

data class CompanyListResponse(
    val apps: List<App>
): Serializable {
    data class App(
        val currency: String,
        val `data`: Data,
        val money_format: String,
        val name: String
    ):Serializable {
        data class Data(
            val add_to_cart: AddToCart,
            val downloads: Downloads,
            val sessions: Sessions,
            val total_sale: TotalSale
        ):Serializable {
            data class AddToCart(
                val month_wise: MonthWise,
                val total: Long
            ):Serializable {
                data class MonthWise(
                    val apr: Int,
                    val feb: Int,
                    val jan: Int,
                    val jun: Int,
                    val mar: Int,
                    val may: Int
                ):Serializable
            }

            data class Downloads(
                val month_wise: MonthWise,
                val total: Long
            ):Serializable {
                data class MonthWise(
                    val apr: Int,
                    val feb: Int,
                    val jan: Int,
                    val jun: Int,
                    val mar: Int,
                    val may: Int
                ):Serializable
            }

            data class Sessions(
                val month_wise: MonthWise,
                val total: Long
            ):Serializable {
                data class MonthWise(
                    val apr: Int,
                    val feb: Int,
                    val jan: Int,
                    val jun: Int,
                    val mar: Int,
                    val may: Int
                ):Serializable
            }

            data class TotalSale(
                val month_wise: MonthWise,
                val total: Long
            ):Serializable {
                data class MonthWise(
                    val apr: Int,
                    val feb: Int,
                    val jan: Int,
                    val jun: Int,
                    val mar: Int,
                    val may: Int
                ):Serializable
            }
        }
    }
}



class TotalSalesComparator: Comparator<CompanyListResponse.App> {
    override fun compare(o1: CompanyListResponse.App?, o2: CompanyListResponse.App?): Int {
        if (o1!!.data.total_sale.total > o2!!.data.total_sale.total)
            return 1;
        else if (o1!!.data.total_sale.total < o2!!.data.total_sale.total)
            return -1;
        else
            return 0;
    }
}

    class AddToCartComparator: Comparator<CompanyListResponse.App> {
        override fun compare(o1: CompanyListResponse.App?, o2: CompanyListResponse.App?): Int {
            if (o1!!.data.add_to_cart.total > o2!!.data.add_to_cart.total)
                return 1;
            else if (o1!!.data.add_to_cart.total < o2!!.data.add_to_cart.total)
                return -1;
            else
                return 0;
        }
    }

class DownloadComparator: Comparator<CompanyListResponse.App> {
    override fun compare(o1: CompanyListResponse.App?, o2: CompanyListResponse.App?): Int {
        if (o1!!.data.downloads.total > o2!!.data.downloads.total)
            return 1;
        else if (o1!!.data.downloads.total < o2!!.data.downloads.total)
            return -1;
        else
            return 0;
    }
}

class SessionsComparator: Comparator<CompanyListResponse.App> {
    override fun compare(o1: CompanyListResponse.App?, o2: CompanyListResponse.App?): Int {
        if (o1!!.data.sessions.total > o2!!.data.sessions.total)
            return 1;
        else if (o1!!.data.sessions.total < o2!!.data.sessions.total)
            return -1;
        else
            return 0;
    }
}


