# SpaceX

- [X] Загрузка запусков с начала 2021 года
- [X] Сортировка в порядке убывания по дате запуска
- [X] Kotlin
- [X] Поддержка смены ориентации экрана
- [X] MVVM
- [X] Clean architecture
- [X] Single Activity с Navigation Component
- [X] Retrofit + Gson
- [X] Kotlin Coroutines + Flow
- [X] Dagger 2
- [X] Coil
- [ ] Пагинация

***Заметка:*** пыталась добавить пагинацию с помощью библиотеки Paging 3. Но получалось, что в слое domain будут зависимости из андроида, а не хотелось. 
Возможно мне стоило отказаться от реализации сlean architecture. Пробовала библиотеку PagingRecycler, не особо успешно + она устарела. 
Читала, что есть paging-common для котлина при работе Paging 3 + СА, но пока не понятно, как всё таки избавиться от зависимостей фреймворка во внутреннем слое.


![](https://github.com/Zellka/SpaceX/blob/master/app.png)

# Author: 
Ilona Zelinskaya
