# MPlayer
MPlayer

Добрый день!

Представленный проект выполнялся как тестовое задание для устройства на работу.

Использованные технологии:<br />
JAVA Spring MVC 3 maven project<br />
Apache tomcat 8 - разворачивание и запуск<br />
Spring security - аутентификация пользователей<br />
Mysql server 5.6 - база данных (синтаксис запросов привязан к ней. Поэтому чтобы сразу все работало, лучше использовать ее)<br />
Mybatis - работа с базой данных<br />
Slf4j + logback - логирование, ротация логов<br />
Java-script<br />
CSS<br />
-Bootstrap<br />
-Bootstrap-slider<br />
-Bootstrap-datepicker3<br />
SWF Flowplayer для проигрывания mp3<br />

Проект представляет собой реализацию базы данных mp3 записей с возможность добавления, просмотра, прослушивания, удаления, атрибутивного поиска записей.

Предумсотрены 2 роли - ROLE_ADMIN, ROLE_USER<br />
ROLE_USER - имеет права только на чтение<br />
ROLE_ADMIN - чтение + запись<br />

Файл mplaydb_create_withtestdata.sql содержит скрипт создания базы данных с тестовым набором записей.

Настройки подключения можно выставить в файле: /src/main/resources/jdbc.properties<br />
По-умолчанию:<br />
jdbc.driverClassName=com.mysql.jdbc.Driver<br />
jdbc.url=jdbc:mysql://localhost:3306/mplayerdb?characterEncoding=utf8<br />
jdbc.username=administrator<br />
jdbc.password=Qq987321<br />

Также необходимо указать путь к файлу логов: /src/main/resources/logback.xml<br />
По-умолчанию: <br />
<file>c:/log/mplayer.log</file><br />