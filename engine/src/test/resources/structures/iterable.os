Коллекция = Новый Структура;
Коллекция.Вставить("Ключ1", "Значение 1");
Коллекция.Вставить("Ключ2", "Значение 2");
Коллекция.Вставить("Ключ3", "Значение 3");

Для Каждого КлючИЗначение Из Коллекция Цикл
	Сообщить("Ключ: " + КлючИЗначение.Ключ + ", Значение: " + КлючИЗначение.Значение);
	Сообщить("Ключ: " + КлючИЗначение["Ключ"] + ", Значение: " + КлючИЗначение["Значение"]);
КонецЦикла;