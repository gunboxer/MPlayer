# MPlayer
MPlayer

Добрый день!

Представленный проект выполнялся как тестовое задание для устройства на работу.

Использованные технологии:
JAVA Spring MVC 3 maven project
Apache tomcat 8 - разворачивание и запуск
Spring security - аутентификация пользователей
Mysql server 5.6 - база данных (синтаксис запросов привязан к ней. Поэтому чтобы сразу все работало, лучше использовать ее)
Mybatis - работа с базой данных
Slf4j + logback - логирование, ротация логов
Java-script
CSS
-Bootstrap
-Bootstrap-slider
-Bootstrap-datepicker3
SWF Flowplayer для проигрывания mp3

Проект представляет собой реализацию базы данных mp3 записей с возможность добавления, просмотра, прослушивания, удаления, атрибутивного поиска записей.

Предумсотрены 2 роли - ROLE_ADMIN, ROLE_USER
ROLE_USER - имеет права только на чтение
ROLE_ADMIN - чтение + запись

Файл mplaydb_create_withtestdata.sql содержит скрипт создания базы данных с тестовым набором записей.

Настройки подключения можно выставить в файле: /src/main/resources/jdbc.properties
По-умолчанию:
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mplayerdb?characterEncoding=utf8
jdbc.username=administrator
jdbc.password=Qq987321

Также необходимо указать путь к файлу логов: /src/main/resources/logback.xml
По-умолчанию: 
<file>c:/log/mplayer.log</file>