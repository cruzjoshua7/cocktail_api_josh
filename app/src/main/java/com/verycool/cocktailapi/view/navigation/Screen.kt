package com.verycool.cocktailapi.view.navigation

sealed class Screen(val route:String) {

    object LoginScreen : Screen("login_screen")
    object CockTailScreen : Screen("cocktail_screen")
    object CockTailListScreen : Screen("cocktail_list_screen")

    fun withArgs(vararg args: String ): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}