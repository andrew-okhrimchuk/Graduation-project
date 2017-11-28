# Graduation-project

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

https://github.com/JavaWebinar/topjava/blob/doc/doc/graduation.md

*************************************************************************************************** 

Создайте и внедрите REST API, используя Hibernate / Spring / SpringMVC (или Spring-Boot) без интерфейса.

Задача:

Создайте систему голосования, чтобы решить, где взять обед.

2 типа пользователей: администратор и постоянные пользователи
Администратор может ввести ресторан и это обеденное меню дня (обычно 2-5 предметов, только название и цена)
Меню меняется каждый день (администраторы делают обновления)
Пользователи могут проголосовать, в каком ресторане они хотят пообедать в
Только один голос подсчитывается для каждого пользователя
Если пользователь снова проголосовал в тот же день:
Если до 11:00 мы предполагаем, что он передумал.
Если это после 11:00, то уже слишком поздно, голосование не может быть изменено
Каждый ресторан предлагает новое меню каждый день.

В результате
, укажите ссылку на репозиторий github. Он должен содержать код README.md с документацией API и пару команд curl для его проверки.

10 лучших практик для лучшего API RESTful

P.S .: Убедитесь, что все работает с последней версией, которая находится на github :)

P.P.S.
: Предположите, что ваш API будет использоваться сторонним разработчиком для создания интерфейса поверх этого.

