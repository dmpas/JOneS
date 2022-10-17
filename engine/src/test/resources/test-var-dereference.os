// При передаче параметра по значению, параметр оборачивается в Variable"
// Тест проверяет работу Variable, содержащего Variable"
Функция ПолучитьКоличествоРекурсивно(Знач Массив, Знач УровеньВложенности = 5)
	Если УровеньВложенности = 0 Тогда
		// в случае правильной работы метод `Количество()`
		// будет вызван от объекта Массив независимо от уровня оборачивания в Variable
		Возврат Массив.Количество();
	КонецЕсли;
	Возврат ПолучитьКоличествоРекурсивно(Массив, УровеньВложенности - 1);
КонецФункции

Массив = Новый Массив;
Массив.Добавить("некоторый элемент, чтобы количество было 1");
Сообщить(ПолучитьКоличествоРекурсивно(Массив));