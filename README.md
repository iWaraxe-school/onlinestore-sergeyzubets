# 5. Patterns

## Materials

[Patterns](https://refactoring.guru/design-patterns)

## VideoLectures
- [Patterns(part 1)](https://youtu.be/q5U92-p-a0s)
- [Patterns(part 2)](https://youtu.be/bR7M\_lv52S4)

## Task #5
Read all materials, try to find a `proper` place to your newly learned patterns in our app. There are a lot of design patterns, but we advise you to pay attention to the following ones:
- Singleton;
- ChainOfResponsibility;
- Fabric.

The application of patterns consists not only in their implementation, but also in knowing their weaknesses and strengths. Therefore, in addition to realising the selected design patterns in the code, you must write the following justification for each pattern (you can send it to me in the messenger, or you can add text to README.md): 
- What is the Design Pattern? 
- Where did you apply it? 
- Justify why you chose this one and not another. What do you gain by using chosen Design Pattern?
## Hints
Rethink your application from SOLID point of view. Keep in mind that in addition to implementing multithreading, we will also work with the database and http. In many ways, we will repeat what we did for the console application for both the database and the http layers. It might be worth coming up with some common interfaces that different versions will implement.
# Justification 
В приложении было имплементировано 3 вида паттернов проектирования:
- Singleton
- Chain of Responsibilities
- Fabric
## Singleton
Объект магазина и каждой категории магазина существует только в одном экземпляре. Создание объектов класса предусмотрено только через статик метод getInstance().

Преимущества:
- Исключены проблемы потенциального появления второго экземпляра одного из классов, что может привести к неправильной работе приложения.
- К объекту, созданного через паттер есть глобальная точка доступа - можно "разгрузить" часть методов с точки зрения входных параметров.  

Недостатки:
- Нарушен принцип единственной ответственности классов.
- Проблемы мультипоточности. На данном этапе "решена" повторной проверкой (if в if), чтобы проверить, не был ли объект создан другим потоком, пока текущий ждал освобождения блокировки.

## Chain of Responsibilities

Преимущества:
- Уменьшена зависимость между клиентом и обработчиками.
- За обработку каждой команды отвечает отдельный класс, это облегчает дальнейший маштабирование модуля.

Недостатки:
- Была вероятность, что запрос может остаться не обработанным ни одним их обработчиков. Для митигирования этого все комманды были сведены в Enum и добавлен обработчик команды, не содержающейся в Enum-е.

## Fabric
Паттерн реализован для класса Category и отвечает за создание объектов дочерних классво-наследников через фабрику. На данном этапе реализации кардинальных преимуществ не дает. При имплеминтации взаимодействия с БД - возможно.

Преимущества:
- Можно повторно использовать уже созданные объекты, вместо порождения новых, что несомненно плюс.
- Код производства новых категорий сведен в одно место, что должно упростить поддержку кода.
- Простота добавления новых категорий в программу.

Недостатки:
- Не нашел для текущей реализации.