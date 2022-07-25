package com.renatojobal.rocketEngine.ui

import com.renatojobal.rocketEngine.R

sealed class BottomNavigationItem(var route: String, var icon: Int, var title: String){
    object Home : BottomNavigationItem("home", R.drawable.ic_check, "Inicio")
    object Detail : BottomNavigationItem("detail", R.drawable.ic_money, "Detalle")


}
