#   Веб-приложение

### About

Дипломная работа. Курс  "Автоматизатор тестирвоание на Java".       
Задание 3: UI
19_qa-java

### Structure

Проект состоит из одного модуля и содержит код и UI тесты для сайта https://stellarburgers.nomoreparties.site
Также в проекте присутсвует API методы для генерации пользователя для тестов и очискти тестовых данных

Стандартная структура модуля:

в src:

* main/java/site/stellaburgers/api - дериктория с  API для тестовых данных
    * utils - базовые значения для тестов
    * enums - различные enum, которые могут понадобиться в рамках разработки автотестов
    * dto - DTO классы для работы с сериализации и десериализации данных
    * specs - спецификации апи
    * factory - классы для генерации объектов с тестовыми данными
    * steps - классы, содержащие специфичные для данного сервиса методы-степы
* main/java/site/stellaburgers/UI - дериктория для UI данных для тестов
  * page- тестовые страницы

* test/java/site/stellaburgers/test
    * BaseUiTest - базовый класс для общей логики
    * пакеты\классы с тестовыми сценариями
### Stack
В проекте используется Java 11, RestAssured  5.3, Lombok 1.18.26, JUnit 4.13.2,Maven 4.0.0

### Installation
В проекте есть возможность получить  Allure отчет  о прохождении тестов. Для получения отчета о тестировании в терминале необходимо выполнить команду из папки проекта
mvn clean test
mvn allure:serve
Для парралельного запуска тестов в POM необходимо добавить зависимость:
<build>
  <plugins>
    <plugin>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>2.22.1</version>
      <configuration>
        <properties>
          <configurationParameters>
          junit.jupiter.execution.parallel.enabled=true
          junit.jupiter.execution.parallel.mode.default=concurrent
          </configurationParameters>
        </properties>
      </configuration>
    </plugin>
  </plugins>
</build>

### Contributors
email: innamalysheva803@gmail.com
tg:@innushca
