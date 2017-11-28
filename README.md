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
Пользователи могут проголосовать, в каком ресторане они хотят пообедать
Только один голос подсчитывается для каждого пользователя
Если пользователь снова проголосовал в тот же день:
    * Если до 11:00 мы предполагаем, что он передумал.
    * Если это после 11:00, то уже слишком поздно, голосование не может быть изменено
Каждый ресторан предлагает новое меню каждый день.

В результате
, укажите ссылку на репозиторий github. Он должен содержать код README.md с документацией API и пару команд curl для его проверки.

10 лучших практик для лучшего API RESTful

P.S .: Убедитесь, что все работает с последней версией, которая находится на github :)

P.P.S.
: Предположите, что ваш API будет использоваться сторонним разработчиком для создания интерфейса поверх этого.

Рекомендации

Сделайте новый проект и добавляйте туда из Topjava только то что нужно! Локализация, типы ошибок, BeanMatcher, Json View, излишние делегирования и наследования - не нужны!

API продумывайте с точки зрения не программиста и объектов, а с точки зрения того, кто им будет пользоваться

1: читаем ТЗ ОЧЕНЬ внимательно, НЕ надо ничего своего туда домысливать и творчески изменять
2: тщательно считайте количество обращений в базу на каждый запрос. 
   Особенно при запросах от юзеров, которых очень много! 
   Также на сложность запросов от них, чтобы не положить базу
3: тщательно считайте количество запросов в вашем API для отображения нужной информации
4: учитывайте, что пользователей может быть ооочень много, а админов- мало
5: в проекте (и тестовом задании на работу) в отличие от нашего учебного topjava оставляйте 
    только необходимый для работы приложения код, ничего лишнего:
5.1 НЕ надо делать разные профили базы и работы с ней.
5.2 НЕ надо делать абстрактных контроллеров на всякий случай.
5.3 НЕ надо делать классов репозиториев, если там нет ничего, кроме делегирования.
5.4 Из потребностей приложения (которую надо самим придумать) реализовывать только очевидные сценарии. 
    Те.- НИЧЕГО ЛИШНЕГО.
5.5 НЕ надо все бездумно кэшировать
6: базу лучше взять без установки (H2 или HSQLDB)
7: по возможности сделать JUnit тесты
8: уделяйте внимание обработке ошибок
9: далаем REST API в соответствии с концепцией REST, с учетом иерархии принадлежности объектов
10: не смешивайте TO и Entity вместе. Лучше всего, если они будут независимыми друг от друга.
11: если приложению в объекте требуется только его id, используйте reference 
    (как мы при сохранении еды вставляем туда юзера)
12: Use for money in java app
13: Историю еды и голосований лучше сделать. Нужно различать базовые вещи, которые закладываются в
    архитектуру приложения и неочевидные доработки к ТЗ, которых лучше не делать.
14: Еще раз про hashCode/equals в Entity: не делайте сравнение по всем полям!
15: Название пакетов, имен классов для model/to/web достаточно стандартные (например model/domain). 
     НЕ надо придумывать своих собственных правил.
16: Предпочтительно использовать DATA-JPA (можно без лишней делегации, напрямую из сервиса/контроллера дергать Repository).
    В случае JPA позаботьтесь о своем собственном generic DAO.
17: Вместо ModelMatcher (который нуждается в javadoc) в тестах контроллеров можно использовать json-path и xxx.json("{...}"), 
      патч 11_04_HW10_duplicate_datetime
18: На topjava мы смотрели разные варианты использования, тут делаем максимально просто. С TO многие вещи упрощаются.
19: Проверьте, не торчат ли из кода учебные уши topjava, типа ProfileRestController.testUTF(), AbstractServiceTest.printResult() 
      или закомментированные JdbcTemplate. Назначение этого проекта совсем другое.
20: ORM работает с объектами. В простейших случаях fk_id как поля допустимы, но при этом JPA их уже никак не обрабатывает 
      и не используйте их вместе с объектами. Ссылка на stackoverwrflow в коде обязательна!
21: Проверьте, станет ли код проще с @AuthenticationPrincipal (урок 11, Доступ к AuthorizedUser). 
     С ним можно убрать из AuthorizedUser все статические методы.

