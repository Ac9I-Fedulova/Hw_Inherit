import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchForMatchingTaskInSimpleTasks() { //полное соответствие текста
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Todos todos = new Todos();
        todos.add(simpleTask);



        Boolean expected = true;
        Boolean actual = simpleTask.matches("Позвонить родителям");
        Assertions.assertEquals(expected, actual);
    }

        @Test
        public void shouldSearchForMatchingTaskByFirstWordInSimpleTasks() { // поиск только по первому слову
            SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

            Todos todos = new Todos();
            todos.add(simpleTask);



            Boolean expected = true;
            Boolean actual = simpleTask.matches("Позвонить");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchForMatchingTaskBySecondWordInSimpleTasks() { // поиск только по второму слову
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Todos todos = new Todos();
        todos.add(simpleTask);



        Boolean expected = true;
        Boolean actual = simpleTask.matches("родителям");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskInSimpleTasks() { // поиск недобавленой задачи в Симпл
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        Todos todos = new Todos();
        todos.add(simpleTask);



        Boolean expected = false;
        Boolean actual = simpleTask.matches("Покормить кошку");

        Assertions.assertEquals(expected, actual);
    }

    //+ тест на регистр

    @Test
    public void shouldSearchForMatchingTaskInEpic() { //полное соответствие текста в одной из ячеек
        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(epic);

        Boolean expected = true;
        Boolean actual = epic.matches("Хлеб");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskInPhraseInEpic() { // словосочетание со словом из подзадачи
        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(epic);

        Boolean expected = false;
        Boolean actual = epic.matches("Купить Яйца");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskInEpic() { // поиск недобавленой в Эпик
        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();
        todos.add(epic);

        Boolean expected = false;
        Boolean actual = epic.matches("Огурец");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSearchForMatchingTaskQueryFoundInTopic() { // поиск запроса по теме обсуждения
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);



        Boolean expected = true;
        Boolean actual = meeting.matches("Выкатка 3й версии приложения");
        Assertions.assertEquals(expected, actual);
    }
// поиск запроса не по первому слову в названии проекта
    @Test
    public void shouldSearchForMatchingTaskNotByFirstWordQueryFoundInProject() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);



        Boolean expected = true;
        Boolean actual = meeting.matches("НетоБанка");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskQueryFoundInStart() { //поиск запроса по дате и времи старта
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);



        Boolean expected = false;
        Boolean actual = meeting.matches("Во вторник после обеда");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSearchForMatchingTaskInMeeting() { // по недобавленой задачи
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(meeting);



        Boolean expected = false;
        Boolean actual = meeting.matches("4ой");

        Assertions.assertEquals(expected, actual);
    }

}