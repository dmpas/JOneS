Перем ПеременнаяМодуля;

Процедура ИзменитьПеременнуюМодуля()

	ПеременнаяМодуля = 21;

КонецПроцедуры

Процедура ДобавитьЕдиницу(()
    ПеременнаяМодуля = ПеременнаяМодуля + 1;
КонецПроцедуры

ПеременнаяМодуля = 99;
ИзменитьПеременнуюМодуля();
ДобавитьЕдиницу();
ПеременнаяМодуля = ПеременнаяМодуля * 2;
Сообщить(ПеременнаяМодуля);