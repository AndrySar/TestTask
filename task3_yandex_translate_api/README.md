# Task_3 Yandex translate API

# Описание

У Яндекс переводчика (translate.yandex.ru) имеется REST API. Необходимо реализовать консольное приложение на одном из языков Java 8,
Groovy или Kotlin, которое переводит введенную английскую фразу на русский язык, пользуясь предложенным API.

## Сборка

Выполните переход переход в директорию проекта

```sh
$ cd task3_yandex_translate_api
```

Выполните сборку с помощью maven

```
$ mvn clean install
```

В папке target должен появиться файл .jar

## Запуск

После Сборки проекта, выполните запуск

```sh
java -jar target/*.jar
```

## Тесты

`YandexTranslateTest` - тест корректной работы пакета YandexTranslate