Условие = Истина;
Условие2 = Ложь;
Если Условие Тогда
    Сообщить("1.1"); // +
ИначеЕсли Условие2 Тогда
    Сообщить("1.2");
Иначе
    Сообщить("1.3");
КонецЕсли;

Условие = Ложь;
Условие2 = Истина;
Если Условие Тогда
    Сообщить("2.1");
ИначеЕсли Условие2 Тогда
    Сообщить("2.2"); // +
Иначе
    Сообщить("2.3");
КонецЕсли;

Условие = Ложь;
Условие2 = Ложь;
Если Условие Тогда
    Сообщить("3.1");
ИначеЕсли Условие2 Тогда
    Сообщить("3.2");
Иначе
    Сообщить("3.3"); // +
КонецЕсли;