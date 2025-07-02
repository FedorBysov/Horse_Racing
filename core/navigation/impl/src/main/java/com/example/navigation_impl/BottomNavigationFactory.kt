package com.example.navigation_impl


    /**
     * Возвращает список элементов для нижней навигации.
     *
     * Порядок элементов в списке определяет их порядок
     * отображения в нижней панели навигации.
     *
     * @return Список элементов нижней навигации
     */
    fun getBottomNavItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem.Race,
            BottomNavigationItem.Rating
        )
    }
