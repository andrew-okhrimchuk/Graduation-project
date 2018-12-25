# Graduation-project
RU:
Создайте и внедрите REST API, используя Hibernate / Spring / SpringMVC (или Spring-Boot) без интерфейса.

Задача:
Создайте систему голосования, чтобы решить, где взять обед.

2 типа пользователей: администратор и постоянные пользователи
Администратор может ввести ресторан и это обеденное меню дня (обычно 2-5 предметов, только название и цена)
Меню меняется каждый день (администраторы делают обновления)
Пользователи могут проголосовать, в каком ресторане они хотят пообедать
Только один голос подсчитывается для каждого пользователя
Если пользователь снова проголосовал в тот же день:
    * Если до 11:00 мы предполагаем, что он передумал.
    * Если это после 11:00, то уже слишком поздно, голосование не может быть изменено
Каждый ресторан предлагает новое меню каждый день.

В результате, укажите ссылку на репозиторий github. Он должен содержать код README.md с документацией API и пару команд curl для его проверки.

1. Документация API
https://documenter.getpostman.com/view/5638841/Rzn9rfwT

2. Сам проект на Heroku https://my-graduation-project.herokuapp.com/rest/profile/
(для запуска немного подождите, проект безплатный, 
логин: user-1@ukr.net и пасс user-1 или 
логин: admin@ukr.net и пасс admin)

3. Источник задания
https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md

4. Kому лень читать доки, просто клик сюда:
https://my-graduation-project.herokuapp.com/rest/profile

https://my-graduation-project.herokuapp.com/rest/menu

https://my-graduation-project.herokuapp.com/rest/profile/meals

*********************************************************************************************** 

EN:
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

10 Best Practices for Better RESTful API
P.S.: Make sure everything works with latest version that is on github :)
P.P.S.: Asume that your API will be used by a frontend developer to build frontend on top of that.

1. API documentation
https://documenter.getpostman.com/view/5638841/Rzn9rfwT

2. The project itself on Heroku https://my-graduation-project.herokuapp.com/rest/profile/
(wait a little to start, the project is free, 
login: user-1@ukr.net and user-1 pass
login: admin@ukr.net и admin pass )


3. Quest Source
https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md

4. Who is too lazy to read docks, just click here
https://my-graduation-project.herokuapp.com/rest/profile

https://my-graduation-project.herokuapp.com/rest/menu

https://my-graduation-project.herokuapp.com/rest/profile/meals
*************************************************************************************************** 

