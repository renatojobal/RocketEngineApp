package com.renatojobal.rocketEngine.ui

import com.renatojobal.rocketEngine.R

sealed class BottomNavigationItem(var route: String, var icon: Int, var title: String){
    object Home : BottomNavigationItem("home", R.drawable.ic_check, "Home")
    object Detail : BottomNavigationItem("detail", R.drawable.ic_money, "Detail")
    object RawView : BottomNavigationItem("rawview", R.drawable.ic_search, "RawView")


}
